package org.jfinance;

import org.jfinance.mapper.ChartMapper;
import org.jfinance.mapper.StockMapper;
import org.jfinance.model.Stock;
import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StockMapperTest {

    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();
    private static final StockMapper stockMapper = StockMapper.getInstance();

    @Test
    public void testBuildStockfromJson() {
        String optionsJsonStr = "{ \"optionChain\": { \"result\": [ " +
                "{ \"quote\": { \"symbol\": \"AAPL\", \"longName\": \"Apple Inc.\", " +
                "\"quoteType\": \"EQUITY\", \"currency\": \"USD\", \"exchangeTimezoneName\": \"America/New_York\", " +
                "\"regularMarketPrice\": 221.55, \"regularMarketChangePercent\": 0.0706917, " +
                "\"marketCap\": 3397269848064, \"bookValue\": 4.837, \"priceToBook\": 46.79347, " +
                "\"averageDailyVolume3Month\": 68492950, \"averageDailyVolume10Day\": 53271200, " +
                "\"fiftyTwoWeekLowChange\": 52.677597, \"fiftyTwoWeekLowChangePercent\": 0.32104826, " +
                "\"fiftyTwoWeekRange\": \"164.08 - 237.23\", \"fiftyTwoWeekHighChange\": -20.472397, " +
                "\"fiftyTwoWeekHighChangePercent\": -0.086297676, \"fiftyTwoWeekLow\": 164.08,  \"fiftyTwoWeekHigh\": 237.23," +
                "\"trailingPE\": 34.455677, \"forwardPE\": 30.349316, \"epsTrailingTwelveMonths\": 7.3, " +
                "\"epsCurrentYear\": 6.6, \"epsForward\": 7.3, \"priceEpsCurrentYear\": 34.293938, " +
                "\"dividendDate\": 1715817600, \"trailingAnnualDividendRate\": 6.43, " +
                "\"trailingAnnualDividendYield\": 0.0043582874, \"earningsTimestamp\": 1714681800, " +
                "\"sharesOutstanding\": 15334099968 } } ] } }";
        String searchJsonStr = "{ \"quotes\": [ { \"sector\": \"Technology\", " +
                "\"industry\": \"Consumer Electronics\", \"exchDisp\": \"NASDAQ\" } ] }";

        try {
            Stock stock = stockMapper.buildStockFromJson(optionsJsonStr, searchJsonStr, "yyyy-MM-dd");
            assertNotNull(stock);

            // Assert stock fields
            assertEquals("AAPL", stock.getSymbol());
            assertEquals("Apple Inc.", stock.getName());
            assertEquals("EQUITY", stock.getType());
            assertEquals("Technology", stock.getSector());
            assertEquals("Consumer Electronics", stock.getIndustry());
            assertEquals("NASDAQ", stock.getExchange());
            assertEquals("USD", stock.getCurrency());
            assertEquals("America/New_York", stock.getExchangeTimezone());
            assertEquals(68492950, stock.getAverageDailyVolume3Month());
            assertEquals(53271200, stock.getAverageDailyVolume10Day());
            assertEquals(221.55, stock.getRegularMarketPrice());
            assertEquals(0.0706917, stock.getRegularMarketChangePercent());
            assertEquals(3397269848064L, stock.getMarketCap());
            assertEquals(52.677597, stock.getFiftyTwoWeekLowChange());
            assertEquals(0.32104826, stock.getFiftyTwoWeekLowChangePercent());
            assertEquals("164.08 - 237.23", stock.getFiftyTwoWeekRange());
            assertEquals(-20.472397, stock.getFiftyTwoWeekHighChange());
            assertEquals(-0.086297676, stock.getFiftyTwoWeekHighChangePercent());
            assertEquals(164.08, stock.getFiftyTwoWeekLow());
            assertEquals(237.23, stock.getFiftyTwoWeekHigh());
            assertEquals(4.837, stock.getBookValue());
            assertEquals(46.79347, stock.getPriceToBook());
            assertEquals(34.455677, stock.getTrailingPE());
            assertEquals(30.349316, stock.getForwardPE());
            assertEquals(7.3, stock.getEpsTrailingTwelveMonths());
            assertEquals(6.6, stock.getEpsCurrentYear());
            assertEquals(7.3, stock.getEpsForward());
            assertEquals(34.293938, stock.getPriceEpsCurrentYear());
            assertEquals(tsConverter.convertTimestampToDate(1715817600L, "yyyy-MM-dd"), stock.getDividendDate());
            assertEquals(6.43, stock.getTrailingAnnualDividendRate());
            assertEquals(0.0043582874, stock.getTrailingAnnualDividendYield());
            assertEquals(tsConverter.convertTimestampToDate(1714681800L, "yyyy-MM-dd"), stock.getEarningsTimestamp());
            assertEquals(15334099968L, stock.getSharesOutstanding());

        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

}