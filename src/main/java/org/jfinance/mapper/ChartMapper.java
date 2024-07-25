package org.jfinance.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jfinance.model.*;
import org.jfinance.service.TimestampConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting JSON strings into Chart objects.
 */
public class ChartMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();

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

        Chart chart = mapMeta(resultNode.at("/meta"));
        List<Long> timestampList = mapTimestampList(resultNode.at("/timestamp"));
        Indicators indicators = mapIndicators(resultNode.at("/indicators"));
        chart.setTimestamp(tsConverter.convertTimestampsToDates(timestampList));
        chart.setIndicators(indicators);

        return chart;
    }

    /**
     * Maps the JSON node to a Meta object.
     *
     * @param metaNode the JSON node representing the metadata
     * @return a Chart object
     */
    private static Chart mapMeta(JsonNode metaNode) {
        Chart chart = new Chart();
        chart.setSymbol(metaNode.get("symbol").asText());
        chart.setCurrency(metaNode.get("currency").asText());
        chart.setExchangeTimezoneName(metaNode.get("exchangeTimezoneName").asText());
        return chart;
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