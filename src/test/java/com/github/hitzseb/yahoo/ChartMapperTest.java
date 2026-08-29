package com.github.hitzseb.yahoo;

import com.github.hitzseb.yahoo.mapper.ChartMapper;
import com.github.hitzseb.yahoo.model.Chart;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChartMapperTest {

    private static final String MOCK_JSON = "{\n" +
            "  \"chart\": {\n" +
            "    \"result\": [\n" +
            "      {\n" +
            "        \"meta\": {\n" +
            "          \"currency\": \"USD\",\n" +
            "          \"symbol\": \"AAPL\",\n" +
            "          \"exchangeTimezoneName\": \"America/New_York\"\n" +
            "        },\n" +
            "        \"timestamp\": [1704121200, 1704207600],\n" +
            "        \"indicators\": {\n" +
            "          \"quote\": [\n" +
            "            {\n" +
            "              \"open\": [185.1, 184.2],\n" +
            "              \"high\": [186.0, 185.5],\n" +
            "              \"low\": [184.0, 183.1],\n" +
            "              \"close\": [185.6, 184.9],\n" +
            "              \"volume\": [50000000, 45000000]\n" +
            "            }\n" +
            "          ],\n" +
            "          \"adjclose\": [\n" +
            "            {\n" +
            "              \"adjclose\": [185.0, 184.3]\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n";

    @Test
    void testBuildChartFromJson() throws IOException {
        String format = "yyyy-MM-dd";
        Chart chart = ChartMapper.buildChartFromJson(MOCK_JSON, format);

        assertNotNull(chart);
        assertEquals("AAPL", chart.getSymbol());
        assertEquals("USD", chart.getCurrency());
        assertEquals("America/New_York", chart.getExchangeTimezoneName());

        assertNotNull(chart.getTimestamp());
        assertEquals(2, chart.getTimestamp().size());

        assertNotNull(chart.getIndicators());
        assertFalse(chart.getIndicators().getQuote().isEmpty());
        assertFalse(chart.getIndicators().getAdjclose().isEmpty());

        var quote = chart.getIndicators().getQuote().get(0);
        assertEquals(185.1, quote.getOpen().get(0));
        assertEquals(50000000L, quote.getVolume().get(0));
    }
}
