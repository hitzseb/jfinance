package com.github.hitzseb.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hitzseb.model.Stock;

import java.io.IOException;

/**
 * Mapper class for converting JSON strings into Stock objects.
 */
public class StockMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Builds a Stock object from JSON strings representing options data, search data, and quote data.
     *
     * @param quoteJson the JSON string representing the quote data
     * @return a Stock object
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    public static Stock buildStockFromJson(String quoteJson) throws IOException {
        JsonNode bodyNode = getBodyNode(quoteJson);
        JsonNode resultNode = getResultNode(bodyNode);
        JsonNode defaultKeyStatisticsNode = getDefaultKeyStatisticsNode(resultNode);
        JsonNode financialDataNode = getFinancialDataNode(resultNode);
        JsonNode summaryDetailNode = getSummaryDetailNode(resultNode);
        JsonNode summaryProfileNode = getSummaryProfileNode(resultNode);
        JsonNode priceNode = getPriceNode(resultNode);

        return mapStock(defaultKeyStatisticsNode, financialDataNode, summaryDetailNode, summaryProfileNode, priceNode);
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
     * Extracts the result node from the body node.
     *
     * @param bodyNode the JSON node representing the body data
     * @return the JSON node representing the result data
     */
    private static JsonNode getResultNode(JsonNode bodyNode) {
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
    private static JsonNode getDefaultKeyStatisticsNode(JsonNode resNode) {
        return resNode.path("defaultKeyStatistics");
    }

    /**
     * Extracts the financial data node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getFinancialDataNode(JsonNode resNode) {
        return resNode.path("financialData");
    }

    /**
     * Extracts the summary detail node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getSummaryDetailNode(JsonNode resNode) {
        return resNode.path("summaryDetail");
    }

    /**
     * Extracts the summary profile node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getSummaryProfileNode(JsonNode resNode) {
        return resNode.path("summaryProfile");
    }

    /**
     * Extracts the price node from the result node.
     *
     * @param resNode the JSON node representing the result data
     * @return the JSON node representing the default key statistics data
     */
    private static JsonNode getPriceNode(JsonNode resNode) {
        return resNode.path("price");
    }

    /**
     * Maps the JSON nodes to a Stock object.
     *
     * @param defaultKeyStatisticsNode the JSON node representing the default key statistics part
     * @param financialDataNode the JSON node representing the financial data part
     * @param summaryDetailNode the JSON node representing the summary detail part
     * @param summaryProfileNode the JSON node representing the summary profile part
     * @param priceNode the JSON node representing the price part
     * @return a Stock object
     */
    private static Stock mapStock(JsonNode defaultKeyStatisticsNode, JsonNode financialDataNode, JsonNode summaryDetailNode, JsonNode summaryProfileNode, JsonNode priceNode) {
        Stock stock = new Stock();

        /* Mapping data from defaultKeyStatisticsNode */
        stock.setEnterpriseValue(defaultKeyStatisticsNode.path("enterpriseValue").path("raw").asLong());
        stock.setFloatShares(defaultKeyStatisticsNode.path("floatShares").path("raw").asLong());
        stock.setSharesOutstanding(defaultKeyStatisticsNode.path("sharesOutstanding").path("raw").asLong());
        stock.setSharesShort(defaultKeyStatisticsNode.path("sharesShort").path("raw").asLong());
        stock.setShortRatio(defaultKeyStatisticsNode.path("shortRatio").path("raw").asDouble());
        stock.setShortPercentOfFloat(defaultKeyStatisticsNode.path("shortPercentOfFloat").path("raw").asDouble());
        stock.setImpliedSharesOutstanding(defaultKeyStatisticsNode.path("impliedSharesOutstanding").path("raw").asLong());
        stock.setNetIncomeToCommon(defaultKeyStatisticsNode.path("netIncomeToCommon").path("raw").asDouble());
        stock.setPegRatio(defaultKeyStatisticsNode.path("pegRatio").path("raw").floatValue());
        stock.setEnterpriseToRevenue(defaultKeyStatisticsNode.path("enterpriseToRevenue").path("raw").floatValue());
        stock.setEnterpriseToEbitda(defaultKeyStatisticsNode.path("enterpriseToEbitda").path("raw").floatValue());
        /* This values could be empty for non equity instruments */
        stock.setBookValue(getDoubleValue(defaultKeyStatisticsNode, "bookValue"));
        stock.setPriceToBook(getDoubleValue(defaultKeyStatisticsNode, "priceToBook"));
        stock.setTrailingEps(getDoubleValue(defaultKeyStatisticsNode, "trailingEps"));
        stock.setForwardEps(getDoubleValue(defaultKeyStatisticsNode, "forwardEps"));

        /* Mapping data from financialDataNode */
        stock.setTotalCash(financialDataNode.path("totalCash").path("raw").asLong());
        stock.setTotalDebt(financialDataNode.path("totalDebt").path("raw").asLong());
        stock.setTotalRevenue(financialDataNode.path("totalRevenue").path("raw").asLong());
        stock.setEbitda(financialDataNode.path("ebitda").path("raw").asLong());
        stock.setDebtToEquity(financialDataNode.path("debtToEquity").path("raw").floatValue());
        stock.setRevenuePerShare(financialDataNode.path("revenuePerShare").path("raw").floatValue());
        stock.setReturnOnAssets(financialDataNode.path("returnOnAssets").path("raw").floatValue());
        stock.setReturnOnEquity(financialDataNode.path("returnOnEquity").path("raw").floatValue());
        stock.setFreeCashflow(financialDataNode.path("freeCashflow").path("raw").asLong());
        stock.setOperatingCashflow(financialDataNode.path("operatingCashflow").path("raw").asLong());
        stock.setEarningsGrowth(financialDataNode.path("earningsGrowth").path("raw").floatValue());
        stock.setRevenueGrowth(financialDataNode.path("revenueGrowth").path("raw").floatValue());
        stock.setGrossMargins(financialDataNode.path("grossMargins").path("raw").floatValue());
        stock.setEbitdaMargins(financialDataNode.path("ebitdaMargins").path("raw").floatValue());
        stock.setOperatingMargins(financialDataNode.path("operatingMargins").path("raw").floatValue());
        stock.setProfitMargins(financialDataNode.path("profitMargins").path("raw").floatValue());

        /* Mapping data from summaryDetailNode */
        /* This values could be empty for non equity instruments */
        stock.setTrailingPE(getDoubleValue(summaryDetailNode, "trailingPE"));
        stock.setForwardPE(getDoubleValue(summaryDetailNode, "forwardPE"));
        stock.setDividendYield(getDoubleValue(summaryDetailNode, "dividendYield"));

        /* Mapping data from summaryProfileNode */
        /* This values could be empty for non equity instruments */
        stock.setSector(getTextValue(summaryProfileNode, "sector"));
        stock.setIndustry(getTextValue(summaryProfileNode, "industry"));
        stock.setLongBusinessSummary(getTextValue(summaryProfileNode, "longBusinessSummary"));

        /* Mapping data from priceNode */
        stock.setSymbol(priceNode.get("symbol").asText());
        stock.setName(priceNode.get("longName").asText());
        stock.setType(priceNode.get("quoteType").asText());
        stock.setCurrency(priceNode.get("currency").asText());
        stock.setRegularMarketPrice(priceNode.get("regularMarketPrice").path("raw").asDouble());
        stock.setRegularMarketChangePercent(priceNode.get("regularMarketChangePercent").path("raw").asDouble());
        stock.setMarketCap(priceNode.get("marketCap").path("raw").asLong());
        stock.setExchangeName(priceNode.get("exchangeName").asText());

        return stock;
    }

    private static String getTextValue(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).asText() : null;
    }

    private static Double getDoubleValue(JsonNode node, String fieldName) {
        return node.has(fieldName) && !node.get(fieldName).isNull() ? node.get(fieldName).path("raw").asDouble() : 0.0;
    }

}