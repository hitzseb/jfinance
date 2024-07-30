package org.jfinance.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfinance.model.Stock;
import org.jfinance.service.TimestampConverter;

import java.io.IOException;

/**
 * Mapper class for converting JSON strings into Stock objects.
 */
public class StockMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Builds a Stock object from JSON strings representing options data, search data, and quote data.
     *
     * @param optionsJsonStr the JSON string representing the options data
     * @param searchJsonStr the JSON string representing the search data
     * @param quoteJson the JSON string representing the quote data
     * @param format the date format to be used for timestamp conversion
     * @return a Stock object
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    public static Stock buildStockFromJson(String optionsJsonStr, String searchJsonStr, String quoteJson, String format) throws IOException {
        JsonNode optionsNode = getOptionsNode(optionsJsonStr);
        JsonNode searchNode = getSearchNode(searchJsonStr);
        JsonNode bodyNode = getBodyNode(quoteJson);
        JsonNode resNode = getResNode(bodyNode);
        JsonNode dfkNode = getDfkNode(resNode);

        return mapStock(optionsNode, searchNode, resNode, dfkNode, format);
    }

    /**
     * Extracts the options node from the JSON string.
     *
     * @param jsonStr the JSON string representing the options data
     * @return the JSON node representing the options data
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    private static JsonNode getOptionsNode(String jsonStr) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        return rootNode.at("/optionChain/result/0/quote");
    }

    /**
     * Extracts the search node from the JSON string.
     *
     * @param jsonStr the JSON string representing the search data
     * @return the JSON node representing the search data
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    private static JsonNode getSearchNode(String jsonStr) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        return rootNode.at("/quotes/0");
    }

    /**
     * Extracts the result node from the body node.
     *
     * @param bodyNode the JSON node representing the body data
     * @return the JSON node representing the result data
     */
    private static JsonNode getResNode(JsonNode bodyNode) {
        return bodyNode
                .path("quoteSummary")
                .path("result")
                .get(0);
    }

    /**
     * Extracts the default key statistics node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getDfkNode(JsonNode resNode) {
        return resNode.path("defaultKeyStatistics");
    }

    /**
     * Extracts the body node from the JSON string.
     *
     * @param jsonStr the JSON string representing the quote data
     * @return the JSON node representing the body data
     * @throws JsonProcessingException if a JSON processing exception occurs during parsing
     */
    private static JsonNode getBodyNode(String jsonStr) throws JsonProcessingException {
        // Parse the root JSON
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        String bodyJson = rootNode.path("body").asText();
        JsonNode bodyNode = objectMapper.readTree(bodyJson);
        return bodyNode;
    }

    /**
     * Maps the JSON nodes to a Stock object.
     *
     * @param optionsNode the JSON node representing the options data
     * @param searchNode the JSON node representing the search data
     * @param resNode the JSON node representing the result data
     * @param dfkNode the JSON node representing the default key statistics data
     * @param format the date format to be used for timestamp conversion
     * @return a Stock object
     */
    private static Stock mapStock(JsonNode optionsNode, JsonNode searchNode, JsonNode resNode, JsonNode dfkNode, String format) {
        Stock stock = new Stock();
        stock.setSymbol(optionsNode.get("symbol").asText());
        stock.setName(optionsNode.get("longName").asText());
        stock.setType(optionsNode.get("quoteType").asText());
        stock.setSector(searchNode.get("sector").asText());
        stock.setIndustry(searchNode.get("industry").asText());
        stock.setExchange(searchNode.get("exchDisp").asText());
        stock.setCurrency(optionsNode.get("currency").asText());
        stock.setExchangeTimezone(optionsNode.get("exchangeTimezoneName").asText());
        stock.setRegularMarketPrice(optionsNode.get("regularMarketPrice").asDouble());
        stock.setRegularMarketChangePercent(optionsNode.get("regularMarketChangePercent").asDouble());
        stock.setMarketCap(optionsNode.get("marketCap").asLong());
        stock.setBookValue(optionsNode.get("bookValue").asDouble());
        stock.setPriceToBook(optionsNode.get("priceToBook").asDouble());
        stock.setAverageDailyVolume3Month(optionsNode.get("averageDailyVolume3Month").asLong());
        stock.setAverageDailyVolume10Day(optionsNode.get("averageDailyVolume10Day").asLong());
        stock.setFiftyTwoWeekLowChange(optionsNode.get("fiftyTwoWeekLowChange").asDouble());
        stock.setFiftyTwoWeekLowChangePercent(optionsNode.get("fiftyTwoWeekLowChangePercent").asDouble());
        stock.setFiftyTwoWeekRange(optionsNode.get("fiftyTwoWeekRange").asText());
        stock.setFiftyTwoWeekHighChange(optionsNode.get("fiftyTwoWeekHighChange").asDouble());
        stock.setFiftyTwoWeekHighChangePercent(optionsNode.get("fiftyTwoWeekHighChangePercent").asDouble());
        stock.setFiftyTwoWeekLow(optionsNode.get("fiftyTwoWeekLow").asDouble());
        stock.setFiftyTwoWeekHigh(optionsNode.get("fiftyTwoWeekHigh").asDouble());
        stock.setTrailingPE(optionsNode.get("trailingPE").asDouble());
        stock.setForwardPE(optionsNode.get("forwardPE").asDouble());
        stock.setEpsTrailingTwelveMonths(optionsNode.get("epsTrailingTwelveMonths").asDouble());
        stock.setEpsCurrentYear(optionsNode.get("epsCurrentYear").asDouble());
        stock.setEpsForward(optionsNode.get("epsForward").asDouble());
        stock.setPriceEpsCurrentYear(optionsNode.get("priceEpsCurrentYear").asDouble());
        Long dividendDate = optionsNode.get("dividendDate").asLong();
        stock.setDividendDate(TimestampConverter.convertTimestampToDate(dividendDate, format));
        stock.setTrailingAnnualDividendRate(optionsNode.get("trailingAnnualDividendRate").asDouble());
        stock.setTrailingAnnualDividendYield(optionsNode.get("trailingAnnualDividendYield").asDouble());
        Long earningsTimestamp = optionsNode.get("earningsTimestamp").asLong();
        stock.setEarningsTimestamp(TimestampConverter.convertTimestampToDate(earningsTimestamp, format));
        stock.setEnterpriseValue(dfkNode.path("enterpriseValue").path("raw").asLong());
        stock.setFloatShares(dfkNode.path("floatShares").path("raw").asLong());
        stock.setSharesOutstanding(dfkNode.path("sharesOutstanding").path("raw").asLong());
        stock.setSharesShort(dfkNode.path("sharesShort").path("raw").asLong());
        stock.setShortRatio(dfkNode.path("shortRatio").path("raw").asDouble());
        stock.setShortPercentOfFloat(dfkNode.path("shortPercentOfFloat").path("raw").asDouble());
        stock.setImpliedSharesOutstanding(dfkNode.path("impliedSharesOutstanding").path("raw").asLong());
        stock.setNetIncomeToCommon(dfkNode.path("netIncomeToCommon").path("raw").asDouble());
        stock.setPegRatio(dfkNode.path("pegRatio").path("raw").floatValue());
        stock.setEnterpriseToRevenue(dfkNode.path("enterpriseToRevenue").path("raw").floatValue());
        stock.setEnterpriseToEbitda(dfkNode.path("enterpriseToEbitda").path("raw").floatValue());
        stock.setLongBusinessSummary(resNode.path("summaryProfile").path("longBusinessSummary").asText());
        stock.setTotalCash(resNode.path("financialData").path("totalCash").path("raw").asLong());
        stock.setTotalDebt(resNode.path("financialData").path("totalDebt").path("raw").asLong());
        stock.setTotalRevenue(resNode.path("financialData").path("totalRevenue").path("raw").asLong());
        stock.setEbitda(resNode.path("financialData").path("ebitda").path("raw").asLong());
        stock.setDebtToEquity(resNode.path("financialData").path("debtToEquity").path("raw").floatValue());
        stock.setRevenuePerShare(resNode.path("financialData").path("revenuePerShare").path("raw").floatValue());
        stock.setReturnOnAssets(resNode.path("financialData").path("returnOnAssets").path("raw").floatValue());
        stock.setReturnOnEquity(resNode.path("financialData").path("returnOnEquity").path("raw").floatValue());
        stock.setFreeCashflow(resNode.path("financialData").path("financialData").path("freeCashflow").path("raw").asLong());
        stock.setOperatingCashflow(resNode.path("financialData").path("operatingCashflow").path("raw").asLong());
        stock.setEarningsGrowth(resNode.path("financialData").path("earningsGrowth").path("raw").floatValue());
        stock.setRevenueGrowth(resNode.path("financialData").path("revenueGrowth").path("raw").floatValue());
        stock.setGrossMargins(resNode.path("financialData").path("grossMargins").path("raw").floatValue());
        stock.setEbitdaMargins(resNode.path("financialData").path("ebitdaMargins").path("raw").floatValue());
        stock.setOperatingMargins(resNode.path("financialData").path("operatingMargins").path("raw").floatValue());
        stock.setProfitMargins(resNode.path("financialData").path("profitMargins").path("raw").floatValue());
        return stock;
    }

}