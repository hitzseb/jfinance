package com.github.hitzseb.yahoo.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hitzseb.yahoo.model.AdjClose;
import com.github.hitzseb.yahoo.model.Chart;
import com.github.hitzseb.yahoo.model.Indicators;
import com.github.hitzseb.yahoo.model.Quote;
import com.github.hitzseb.yahoo.util.TimestampConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for converting JSON strings into Chart objects.
 */
public class ChartMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Chart buildChartFromJson(String jsonStr, String format) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode resultNode = rootNode.at("/chart/result/0");

        Chart chart = mapMeta(resultNode.at("/meta"));
        List<Long> timestampList = mapTimestampList(resultNode.at("/timestamp"));
        Indicators indicators = mapIndicators(resultNode.at("/indicators"));
        chart.setTimestamp(TimestampConverter.convertTimestampsToDates(timestampList, format));
        chart.setIndicators(indicators);

        return chart;
    }

    public static Chart buildChartFromJson(String jsonStr, String format, String timezone) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonStr);
        JsonNode resultNode = rootNode.at("/chart/result/0");

        Chart chart = mapMeta(resultNode.at("/meta"));
        List<Long> timestampList = mapTimestampList(resultNode.at("/timestamp"));
        Indicators indicators = mapIndicators(resultNode.at("/indicators"));
        chart.setTimestamp(TimestampConverter.convertTimestampsToDates(timestampList, format, timezone));
        chart.setIndicators(indicators);

        return chart;
    }

    private static Chart mapMeta(JsonNode metaNode) {
        Chart chart = new Chart();
        chart.setSymbol(metaNode.get("symbol").asText());
        chart.setCurrency(metaNode.get("currency").asText());
        chart.setExchangeTimezoneName(metaNode.get("exchangeTimezoneName").asText());
        return chart;
    }

    private static List<Long> mapTimestampList(JsonNode timestampNode) {
        List<Long> timestampList = new ArrayList<>();
        for (JsonNode node : timestampNode) {
            timestampList.add(node.asLong());
        }
        return timestampList;
    }

    private static Indicators mapIndicators(JsonNode indicatorsNode) {
        Indicators indicators = new Indicators();

        List<Quote> quotes = mapQuotes(indicatorsNode.at("/quote/0"));
        indicators.setQuote(quotes);

        if (indicatorsNode.has("adjclose")) {
            List<AdjClose> adjCloses = mapAdjCloses(indicatorsNode.at("/adjclose/0"));
            indicators.setAdjclose(adjCloses);
        }

        return indicators;
    }

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

    private static List<AdjClose> mapAdjCloses(JsonNode adjCloseNode) {
        List<AdjClose> adjCloses = new ArrayList<>();
        AdjClose adjClose = new AdjClose();
        adjClose.setAdjclose(JsonConverter.convertJsonNodeToList(adjCloseNode.get("adjclose")));
        adjCloses.add(adjClose);
        return adjCloses;
    }
}