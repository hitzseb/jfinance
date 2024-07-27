package org.jfinance;

import org.jfinance.mapper.ChartMapper;
import org.jfinance.model.Chart;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChartMapperTest {

    private static final ChartMapper chartMapper = ChartMapper.getInstance();

    @Test
    public void testFromJson() {
        String jsonStr = "{ \"chart\": { \"result\": [ { \"meta\": { \"currency\": \"USD\", \"symbol\": \"AAPL\", \"exchangeName\": \"NMS\", \"fullExchangeName\": \"NasdaqGS\", \"instrumentType\": \"EQUITY\", \"timezone\": \"EDT\", \"exchangeTimezoneName\": \"America/New_York\", \"regularMarketPrice\": 208.14, \"fiftyTwoWeekHigh\": 212.7, \"fiftyTwoWeekLow\": 206.59, \"regularMarketDayHigh\": 212.7, \"regularMarketDayLow\": 206.59, \"regularMarketVolume\": 78306092 }, \"timestamp\": [ 1629216000, 1629302400 ], \"indicators\": { \"quote\": [ { \"high\": [ 210.0, 211.0 ], \"close\": [ 209.0, 210.0 ], \"volume\": [ 123456, 123457 ], \"open\": [ 208.0, 209.0 ], \"low\": [ 207.0, 208.0 ] } ], \"adjclose\": [ { \"adjclose\": [ 209.0, 210.0 ] } ] } } ] } }";

        try {
            Chart chart = chartMapper.buildChartFromJson(jsonStr, "yyyy-MM-dd");
            assertNotNull(chart);

            // Assert fields
            assertEquals("AAPL", chart.getSymbol());
            assertEquals("USD", chart.getCurrency());
            assertEquals("America/New_York", chart.getExchangeTimezoneName());

            // Assert timestamp
            List<String> timestamps = chart.getTimestamp();
            assertNotNull(timestamps);
            assertEquals(2, timestamps.size());
            assertEquals("2021-08-17", timestamps.get(0));
            assertEquals("2021-08-18", timestamps.get(1));

            // Assert indicators
            assertNotNull(chart.getIndicators());
            assertNotNull(chart.getIndicators().getQuote());
            assertEquals(1, chart.getIndicators().getQuote().size());
            assertNotNull(chart.getIndicators().getAdjclose());
            assertEquals(1, chart.getIndicators().getAdjclose().size());

            // Assert quote fields
            assertEquals(2, chart.getIndicators().getQuote().get(0).getHigh().size());
            assertEquals(210.0, chart.getIndicators().getQuote().get(0).getHigh().get(0));
            assertEquals(211.0, chart.getIndicators().getQuote().get(0).getHigh().get(1));

        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

}