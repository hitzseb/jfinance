package org.jfinance.mapper;

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
    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();

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

        return mapStock(optionsNode, searchNode);
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
        stock.setCurrency(optionsNode.get("currency").asText());
        stock.setRegularMarketPrice(optionsNode.get("regularMarketPrice").asDouble());
        stock.setRegularMarketChangePercent(optionsNode.get("regularMarketChangePercent").asDouble());
        stock.setMarketCap(optionsNode.get("marketCap").asLong());
        stock.setBookValue(optionsNode.get("bookValue").asDouble());
        stock.setPriceToBook(optionsNode.get("priceToBook").asDouble());
        stock.setTrailingPE(optionsNode.get("trailingPE").asDouble());
        stock.setForwardPE(optionsNode.get("forwardPE").asDouble());
        stock.setEpsTrailingTwelveMonths(optionsNode.get("epsTrailingTwelveMonths").asDouble());
        stock.setEpsCurrentYear(optionsNode.get("epsCurrentYear").asDouble());
        stock.setEpsForward(optionsNode.get("epsForward").asDouble());
        stock.setPriceEpsCurrentYear(optionsNode.get("priceEpsCurrentYear").asDouble());
        Long dividendDate = optionsNode.get("dividendDate").asLong();
        stock.setDividendDate(tsConverter.convertTimestampToDate(dividendDate));
        stock.setTrailingAnnualDividendRate(optionsNode.get("trailingAnnualDividendRate").asDouble());
        stock.setTrailingAnnualDividendYield(optionsNode.get("trailingAnnualDividendYield").asDouble());
        Long earningsTimestamp = optionsNode.get("earningsTimestamp").asLong();
        stock.setEarningsTimestamp(tsConverter.convertTimestampToDate(earningsTimestamp));
        stock.setSharesOutstanding(optionsNode.get("sharesOutstanding").asLong());
        return stock;
    }

}