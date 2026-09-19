package com.github.hitzseb.sec.mapper;

import com.github.hitzseb.sec.model.CurrentCompanyFinancials;
import com.github.hitzseb.sec.dto.ConceptDto;
import com.github.hitzseb.sec.dto.FactUnitItemDto;
import com.github.hitzseb.sec.dto.SecCompanyFactsDto;
import com.github.hitzseb.sec.util.CikUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Mapper responsible for parsing SEC facts DTOs and assembling a CurrentCompanyFinancials domain model
 * using the latest available reported data.
 */
public class SecFactsMapper {

    // Primary US-GAAP tags with fallback aliases
    private static final String[] REVENUE_TAGS = {"Revenues", "SalesRevenueNet", "RevenueFromContractWithCustomerExcludingAssessedTax"};
    private static final String[] NET_INCOME_TAGS = {"NetIncomeLoss", "ProfitLoss"};
    private static final String[] OPERATING_INCOME_TAGS = {"OperatingIncomeLoss"};
    private static final String[] ASSETS_TAGS = {"Assets"};
    private static final String[] LIABILITIES_TAGS = {"Liabilities"};
    private static final String[] EQUITY_TAGS = {"StockholdersEquity", "StockholdersEquityIncludingPortionAttributableToNoncontrollingInterest"};
    private static final String[] CASH_TAGS = {"CashAndCashEquivalentsAtCarryingValue", "CashCashEquivalentsRestrictedCashAndRestrictedCashEquivalents"};
    private static final String[] DEBT_TAGS = {"LongTermDebtAndCapitalLeaseObligations", "LongTermDebtNoncurrent", "DebtCurrent"};
    private static final String SHARES_TAG = "EntityCommonStockSharesOutstanding";

    public CurrentCompanyFinancials toCurrentFinancials(SecCompanyFactsDto dto) {
        if (dto == null || dto.getFacts() == null) {
            throw new IllegalArgumentException("SEC Company Facts DTO or facts container cannot be null");
        }

        String formattedCik = CikUtils.formatCik(String.valueOf(dto.getCik()));
        String companyName = dto.getEntityName();

        Map<String, ConceptDto> usGaap = dto.getFacts().getUsGaap() != null ? dto.getFacts().getUsGaap() : Collections.emptyMap();
        Map<String, ConceptDto> dei = dto.getFacts().getDei() != null ? dto.getFacts().getDei() : Collections.emptyMap();

        // Extract latest data points for Balance Sheet and Capital Structure
        Optional<FactUnitItemDto> latestAssets = findLatestFactByTags(usGaap, ASSETS_TAGS);
        Optional<FactUnitItemDto> latestLiabilities = findLatestFactByTags(usGaap, LIABILITIES_TAGS);
        Optional<FactUnitItemDto> latestEquity = findLatestFactByTags(usGaap, EQUITY_TAGS);
        Optional<FactUnitItemDto> latestCash = findLatestFactByTags(usGaap, CASH_TAGS);
        Optional<FactUnitItemDto> latestDebt = findLatestFactByTags(usGaap, DEBT_TAGS);
        Optional<FactUnitItemDto> latestShares = findLatestFact(dei, SHARES_TAG);

        // Extract TTM / Annual flow metrics
        Optional<FactUnitItemDto> latestRevenue = findLatestFactByTags(usGaap, REVENUE_TAGS);
        Optional<FactUnitItemDto> latestNetIncome = findLatestFactByTags(usGaap, NET_INCOME_TAGS);
        Optional<FactUnitItemDto> latestOpIncome = findLatestFactByTags(usGaap, OPERATING_INCOME_TAGS);

        // Determine snapshot period date from the most recent balance sheet record
        LocalDate periodEndDate = latestAssets.map(FactUnitItemDto::getEnd)
                .map(LocalDate::parse)
                .orElseGet(LocalDate::now);

        return new CurrentCompanyFinancials(
                formattedCik,
                companyName,
                periodEndDate,
                extractValue(latestRevenue),
                extractValue(latestNetIncome),
                null, // EBITDA (Will be calculated or extracted via D&A)
                extractValue(latestOpIncome),
                extractValue(latestAssets),
                extractValue(latestLiabilities),
                extractValue(latestEquity),
                extractValue(latestCash),
                extractValue(latestDebt),
                extractValue(latestShares)
        );
    }

    private Optional<FactUnitItemDto> findLatestFactByTags(Map<String, ConceptDto> concepts, String[] tags) {
        for (String tag : tags) {
            Optional<FactUnitItemDto> fact = findLatestFact(concepts, tag);
            if (fact.isPresent()) {
                return fact;
            }
        }
        return Optional.empty();
    }

    private Optional<FactUnitItemDto> findLatestFact(Map<String, ConceptDto> concepts, String tag) {
        ConceptDto concept = concepts.get(tag);
        if (concept == null || concept.getUnits() == null || concept.getUnits().isEmpty()) {
            return Optional.empty();
        }

        // Get the list of items (usually under key "USD" or "shares")
        List<FactUnitItemDto> items = concept.getUnits().values().stream()
                .findFirst()
                .orElse(Collections.emptyList());

        return items.stream()
                .filter(item -> item.getEnd() != null && item.getVal() != null)
                .max(Comparator.comparing(item -> LocalDate.parse(item.getEnd())));
    }

    private BigDecimal extractValue(Optional<FactUnitItemDto> factOpt) {
        return factOpt.map(FactUnitItemDto::getVal)
                .map(BigDecimal::new)
                .orElse(null);
    }
}