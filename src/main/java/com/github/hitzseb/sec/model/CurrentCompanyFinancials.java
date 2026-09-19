package com.github.hitzseb.sec.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Snapshot of the current financial state of a company for valuation ratios (Java 11 compatible).
 */
public final class CurrentCompanyFinancials {

    private final String cik;
    private final String companyName;
    private final LocalDate periodEndDate;

    // --- Flow metrics (TTM: Trailing Twelve Months) ---
    private final BigDecimal revenuesTtm;
    private final BigDecimal netIncomeTtm;
    private final BigDecimal ebitdaTtm;
    private final BigDecimal operatingIncomeTtm;

    // --- Stock metrics (Latest Balance Sheet) ---
    private final BigDecimal totalAssets;
    private final BigDecimal totalLiabilities;
    private final BigDecimal stockholdersEquity;
    private final BigDecimal cashAndCashEquivalents;
    private final BigDecimal totalDebt;

    // --- Capital Structure ---
    private final BigDecimal sharesOutstanding;

    public CurrentCompanyFinancials(
            String cik,
            String companyName,
            LocalDate periodEndDate,
            BigDecimal revenuesTtm,
            BigDecimal netIncomeTtm,
            BigDecimal ebitdaTtm,
            BigDecimal operatingIncomeTtm,
            BigDecimal totalAssets,
            BigDecimal totalLiabilities,
            BigDecimal stockholdersEquity,
            BigDecimal cashAndCashEquivalents,
            BigDecimal totalDebt,
            BigDecimal sharesOutstanding
    ) {
        this.cik = cik;
        this.companyName = companyName;
        this.periodEndDate = periodEndDate;
        this.revenuesTtm = revenuesTtm;
        this.netIncomeTtm = netIncomeTtm;
        this.ebitdaTtm = ebitdaTtm;
        this.operatingIncomeTtm = operatingIncomeTtm;
        this.totalAssets = totalAssets;
        this.totalLiabilities = totalLiabilities;
        this.stockholdersEquity = stockholdersEquity;
        this.cashAndCashEquivalents = cashAndCashEquivalents;
        this.totalDebt = totalDebt;
        this.sharesOutstanding = sharesOutstanding;
    }

    public String getCik() {
        return cik;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getPeriodEndDate() {
        return periodEndDate;
    }

    public BigDecimal getRevenuesTtm() {
        return revenuesTtm;
    }

    public BigDecimal getNetIncomeTtm() {
        return netIncomeTtm;
    }

    public BigDecimal getEbitdaTtm() {
        return ebitdaTtm;
    }

    public BigDecimal getOperatingIncomeTtm() {
        return operatingIncomeTtm;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public BigDecimal getStockholdersEquity() {
        return stockholdersEquity;
    }

    public BigDecimal getCashAndCashEquivalents() {
        return cashAndCashEquivalents;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public BigDecimal getSharesOutstanding() {
        return sharesOutstanding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentCompanyFinancials that = (CurrentCompanyFinancials) o;
        return Objects.equals(cik, that.cik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cik);
    }

    @Override
    public String toString() {
        return "CurrentCompanyFinancials{" +
                "cik='" + cik + '\'' +
                ", companyName='" + companyName + '\'' +
                ", periodEndDate=" + periodEndDate +
                ", netIncomeTtm=" + netIncomeTtm +
                ", totalAssets=" + totalAssets +
                ", stockholdersEquity=" + stockholdersEquity +
                '}';
    }
}