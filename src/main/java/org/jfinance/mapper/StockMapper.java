package org.jfinance.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfinance.model.Stock;

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
     * @return a Stock object
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    public static Stock buildStockFromJson(String optionsJsonStr, String searchJsonStr, String quoteJson) throws IOException {
        JsonNode optionsNode = getOptionsNode(optionsJsonStr);
        JsonNode searchNode = getSearchNode(searchJsonStr);
        JsonNode bodyNode = getBodyNode(quoteJson);
        JsonNode resNode = getResNode(bodyNode);
        JsonNode dksNode = getDksNode(resNode);
        JsonNode fdNode = getFdNode(resNode);

        return mapStock(optionsNode, searchNode, resNode, dksNode, fdNode);
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
    private static JsonNode getDksNode(JsonNode resNode) {
        return resNode.path("defaultKeyStatistics");
    }

    /**
     * Extracts the financial data node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getFdNode(JsonNode resNode) {
        return resNode.path("financialData");
    }

    /**
     * Extracts the body node from the JSON string.
     *
     * @param jsonStr the JSON string representing the quote data
     * @return the JSON node representing the body data
     * @throws JsonProcessingException if a JSON processing exception occurs during parsing
     */
    private static JsonNode getBodyNode(String jsonStr) throws JsonProcessingException {
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
     * @param dksNode the JSON node representing the default key statistics data
     * @return a Stock object
     */
    private static Stock mapStock(JsonNode optionsNode, JsonNode searchNode, JsonNode resNode, JsonNode dksNode, JsonNode fdNode) {
        Stock stock = new Stock();

        /* Mapping data from options endpoint */
        stock.setSymbol(optionsNode.get("symbol").asText());
        stock.setName(optionsNode.get("longName").asText());
        stock.setType(optionsNode.get("quoteType").asText());
        stock.setCurrency(optionsNode.get("currency").asText());
        stock.setRegularMarketPrice(optionsNode.get("regularMarketPrice").asDouble());
        stock.setRegularMarketChangePercent(optionsNode.get("regularMarketChangePercent").asDouble());
        stock.setMarketCap(optionsNode.get("marketCap").asLong());
        /* This values could be empty for non equity instruments */
        stock.setBookValue(getDoubleValue(optionsNode, "bookValue"));
        stock.setPriceToBook(getDoubleValue(optionsNode, "priceToBook"));
        stock.setPriceToBook(getDoubleValue(optionsNode, "trailingPE"));
        stock.setTrailingPE(getDoubleValue(optionsNode, "forwardPE"));
        stock.setForwardPE(getDoubleValue(optionsNode, "epsTrailingTwelveMonths"));
        stock.setEpsTrailingTwelveMonths(getDoubleValue(optionsNode, "epsCurrentYear"));
        stock.setEpsCurrentYear(getDoubleValue(optionsNode, "epsForward"));
        stock.setEpsForward(getDoubleValue(optionsNode, "priceEpsCurrentYear"));

        /* Mapping data from search endpoint */
        stock.setExchange(searchNode.get("exchDisp").asText());
        /* This values could be empty for non equity instruments */
        stock.setSector(getTextValue(searchNode, "sector"));
        stock.setIndustry(getTextValue(searchNode, "industry"));

        /* Mapping data from result node */
        stock.setLongBusinessSummary(resNode.path("summaryProfile").path("longBusinessSummary").asText());

        /* Mapping data from default key statistics node */
        stock.setEnterpriseValue(dksNode.path("enterpriseValue").path("raw").asLong());
        stock.setFloatShares(dksNode.path("floatShares").path("raw").asLong());
        stock.setSharesOutstanding(dksNode.path("sharesOutstanding").path("raw").asLong());
        stock.setSharesShort(dksNode.path("sharesShort").path("raw").asLong());
        stock.setShortRatio(dksNode.path("shortRatio").path("raw").asDouble());
        stock.setShortPercentOfFloat(dksNode.path("shortPercentOfFloat").path("raw").asDouble());
        stock.setImpliedSharesOutstanding(dksNode.path("impliedSharesOutstanding").path("raw").asLong());
        stock.setNetIncomeToCommon(dksNode.path("netIncomeToCommon").path("raw").asDouble());
        stock.setPegRatio(dksNode.path("pegRatio").path("raw").floatValue());
        stock.setEnterpriseToRevenue(dksNode.path("enterpriseToRevenue").path("raw").floatValue());
        stock.setEnterpriseToEbitda(dksNode.path("enterpriseToEbitda").path("raw").floatValue());

        /* Mapping financial data node */
        stock.setTotalCash(fdNode.path("totalCash").path("raw").asLong());
        stock.setTotalDebt(fdNode.path("totalDebt").path("raw").asLong());
        stock.setTotalRevenue(fdNode.path("totalRevenue").path("raw").asLong());
        stock.setEbitda(fdNode.path("ebitda").path("raw").asLong());
        stock.setDebtToEquity(fdNode.path("debtToEquity").path("raw").floatValue());
        stock.setRevenuePerShare(fdNode.path("revenuePerShare").path("raw").floatValue());
        stock.setReturnOnAssets(fdNode.path("returnOnAssets").path("raw").floatValue());
        stock.setReturnOnEquity(fdNode.path("returnOnEquity").path("raw").floatValue());
        stock.setFreeCashflow(fdNode.path("freeCashflow").path("raw").asLong());
        stock.setOperatingCashflow(fdNode.path("operatingCashflow").path("raw").asLong());
        stock.setEarningsGrowth(fdNode.path("earningsGrowth").path("raw").floatValue());
        stock.setRevenueGrowth(fdNode.path("revenueGrowth").path("raw").floatValue());
        stock.setGrossMargins(fdNode.path("grossMargins").path("raw").floatValue());
        stock.setEbitdaMargins(fdNode.path("ebitdaMargins").path("raw").floatValue());
        stock.setOperatingMargins(fdNode.path("operatingMargins").path("raw").floatValue());
        stock.setProfitMargins(fdNode.path("profitMargins").path("raw").floatValue());

        return stock;
    }

    private static String getTextValue(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asText() : null;
    }

    private static Double getDoubleValue(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asDouble() : 0.0;
    }

}