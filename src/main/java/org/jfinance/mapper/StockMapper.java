package org.jfinance.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfinance.model.FinancialData;
import org.jfinance.model.Stock;

import java.io.IOException;

/**
 * Mapper class for converting JSON strings into Stock objects.
 */
public class StockMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Builds a Stock object from two JSON strings representing options and search data.
     *
     * @param optionsJsonStr the JSON string representing the options data
     * @param searchJsonStr the JSON string representing the search data
     * @return a Stock object
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    public static Stock buildStockFromJson(String optionsJsonStr, String searchJsonStr) throws IOException {
        JsonNode optionsNode = getOptionsNode(optionsJsonStr);
        JsonNode searchNode = getSearchNode(searchJsonStr);

        Stock stock = mapStock(optionsNode, searchNode);
        FinancialData financialData = mapFinancialData(optionsNode);

        stock.setFinancialData(financialData);

        return stock;
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
     * Maps the JSON nodes to a Stock object.
     *
     * @param optionsNode the JSON node representing the options data
     * @param searchNode the JSON node representing the search data
     * @return a Stock object
     */
    private static Stock mapStock(JsonNode optionsNode, JsonNode searchNode) {
        Stock stock = new Stock();
        stock.setSymbol(optionsNode.get("symbol").asText());
        stock.setName(optionsNode.get("longName").asText());
        stock.setType(optionsNode.get("quoteType").asText());
        stock.setSector(searchNode.get("sector").asText());
        stock.setIndustry(searchNode.get("industry").asText());
        stock.setExchange(searchNode.get("exchDisp").asText());
        return stock;
    }

    /**
     * Maps the JSON node to a FinancialData object.
     *
     * @param optionsNode the JSON node representing the options data
     * @return a FinancialData object
     */
    private static FinancialData mapFinancialData(JsonNode optionsNode) {
        FinancialData financialData = new FinancialData();
        financialData.setRegularMarketPrice(optionsNode.get("regularMarketPrice").asDouble());
        financialData.setRegularMarketChangePercent(optionsNode.get("regularMarketChangePercent").asDouble());
        financialData.setMarketCap(optionsNode.get("marketCap").asLong());
        financialData.setBookValue(optionsNode.get("bookValue").asDouble());
        financialData.setPriceToBook(optionsNode.get("priceToBook").asDouble());
        financialData.setTrailingPE(optionsNode.get("trailingPE").asDouble());
        financialData.setForwardPE(optionsNode.get("forwardPE").asDouble());
        financialData.setEpsTrailingTwelveMonths(optionsNode.get("epsTrailingTwelveMonths").asDouble());
        financialData.setEpsCurrentYear(optionsNode.get("epsCurrentYear").asDouble());
        financialData.setEpsForward(optionsNode.get("epsForward").asDouble());
        financialData.setPriceEpsCurrentYear(optionsNode.get("priceEpsCurrentYear").asDouble());
        financialData.setDividendDate(optionsNode.get("dividendDate").asLong());
        financialData.setTrailingAnnualDividendRate(optionsNode.get("trailingAnnualDividendRate").asDouble());
        financialData.setTrailingAnnualDividendYield(optionsNode.get("trailingAnnualDividendYield").asDouble());
        financialData.setEarningsTimestamp(optionsNode.get("earningsTimestamp").asLong());
        financialData.setSharesOutstanding(optionsNode.get("sharesOutstanding").asLong());
        return financialData;
    }

}
