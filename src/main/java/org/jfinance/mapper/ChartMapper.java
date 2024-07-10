package org.jfinance.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfinance.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting JSON strings into Chart objects.
 */
public class ChartMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Builds a Chart object from a JSON string.
     *
     * @param jsonStr the JSON string representing the chart data
     * @return a Chart object
     * @throws IOException if an I/O exception occurs during JSON parsing
     */
    public static Chart buildChartfromJson(String jsonStr) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode resultNode = rootNode.at("/chart/result/0");

        Meta meta = mapMeta(resultNode.at("/meta"));
        List<Long> timestampList = mapTimestampList(resultNode.at("/timestamp"));
        Indicators indicators = mapIndicators(resultNode.at("/indicators"));

        return new Chart(meta, timestampList, indicators);
    }

    /**
     * Maps the JSON node to a Meta object.
     *
     * @param metaNode the JSON node representing the meta data
     * @return a Meta object
     */
    private static Meta mapMeta(JsonNode metaNode) {
        Meta meta = new Meta();
        meta.setSymbol(metaNode.get("symbol").asText());
        meta.setCurrency(metaNode.get("currency").asText());
        meta.setExchangeTimezoneName(metaNode.get("exchangeTimezoneName").asText());
        return meta;
    }

    /**
     * Maps the JSON node to a list of timestamps.
     *
     * @param timestampNode the JSON node representing the timestamps
     * @return a list of timestamps
     */
    private static List<Long> mapTimestampList(JsonNode timestampNode) {
        List<Long> timestampList = new ArrayList<>();
        for (JsonNode node : timestampNode) {
            timestampList.add(node.asLong());
        }
        return timestampList;
    }

    /**
     * Maps the JSON node to an Indicators object.
     *
     * @param indicatorsNode the JSON node representing the indicators
     * @return an Indicators object
     */
    private static Indicators mapIndicators(JsonNode indicatorsNode) {
        Indicators indicators = new Indicators();

        List<Quote> quotes = mapQuotes(indicatorsNode.at("/quote/0"));
        indicators.setQuote(quotes);

        List<AdjClose> adjCloses = mapAdjCloses(indicatorsNode.at("/adjclose/0"));
        indicators.setAdjclose(adjCloses);

        return indicators;
    }

    /**
     * Maps the JSON node to a list of Quote objects.
     *
     * @param quoteNode the JSON node representing the quote data
     * @return a list of Quote objects
     */
    private static List<Quote> mapQuotes(JsonNode quoteNode) {
        List<Quote> quotes = new ArrayList<>();
        Quote quote = new Quote();
        quote.setOpen(JsonConverter.convertJsonNodeToList(quoteNode.get("open")));
        quote.setHigh(JsonConverter.convertJsonNodeToList(quoteNode.get("high")));
        quote.setLow(JsonConverter.convertJsonNodeToList(quoteNode.get("low")));
        quote.setClose(JsonConverter.convertJsonNodeToList(quoteNode.get("close")));
        quote.setVolume(JsonConverter.convertJsonNodeToListLong(quoteNode.get("volume")));
        quotes.add(quote);
        return quotes;
    }

    /**
     * Maps the JSON node to a list of AdjClose objects.
     *
     * @param adjCloseNode the JSON node representing the adjusted close data
     * @return a list of AdjClose objects
     */
    private static List<AdjClose> mapAdjCloses(JsonNode adjCloseNode) {
        List<AdjClose> adjCloses = new ArrayList<>();
        AdjClose adjClose = new AdjClose();
        adjClose.setAdjclose(JsonConverter.convertJsonNodeToList(adjCloseNode.get("adjclose")));
        adjCloses.add(adjClose);
        return adjCloses;
    }

}
