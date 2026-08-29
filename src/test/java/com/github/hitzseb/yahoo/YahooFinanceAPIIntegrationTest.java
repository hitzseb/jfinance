package com.github.hitzseb.yahoo;

import com.github.hitzseb.yahoo.model.Chart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class YahooFinanceAPIIntegrationTest {

    private YahooFinanceAPI api;

    @BeforeEach
    void setUp() {
        this.api = new YahooFinanceAPI();
    }

    @Test
    void testGetChartWithRange() throws IOException, InterruptedException {
        String symbol = "AAPL";
        String interval = "1d";
        String range = "5d";

        Chart chart = api.getChartByRange(symbol, interval, range);

        assertChartItems(chart);
    }

    @Test
    void testGetChartWithPeriod() throws IOException, InterruptedException {
        String symbol = "AAPL";
        String interval = "1d";
        String period1 = "2024-01-01";
        String period2 = "2024-06-30";

        Chart chart = api.getChartByPeriod(symbol, interval, period1, period2);

        assertChartItems(chart);
    }

    private static void assertChartItems(Chart chart) {
        assertNotNull(chart);
        assertNotNull(chart.getTimestamp());
        assertNotNull(chart.getIndicators());

        assertFalse(chart.getTimestamp().isEmpty());
        assertFalse(chart.getIndicators().getQuote().isEmpty());
        assertFalse(chart.getIndicators().getAdjclose().isEmpty());

        assertEquals("AAPL", chart.getSymbol());
        assertEquals("America/New_York", chart.getExchangeTimezoneName());
    }
}