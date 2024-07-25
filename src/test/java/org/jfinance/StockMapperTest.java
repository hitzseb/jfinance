package org.jfinance;

import org.jfinance.mapper.StockMapper;
import org.jfinance.model.Stock;
import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StockMapperTest {

    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();

    @Test
    public void testBuildStockfromJson() {
        String optionsJsonStr = "{ \"optionChain\": { \"result\": [ { \"quote\": { \"symbol\": \"AAPL\", \"longName\": \"Apple Inc.\", \"quoteType\": \"EQUITY\", \"currency\": \"USD\", \"regularMarketPrice\": 221.55, \"regularMarketChangePercent\": 0.0706917, \"marketCap\": 3397269848064, \"bookValue\": 4.837, \"priceToBook\": 46.79347, \"trailingPE\": 34.455677, \"forwardPE\": 30.349316, \"epsTrailingTwelveMonths\": 7.3, \"epsCurrentYear\": 6.6, \"epsForward\": 7.3, \"priceEpsCurrentYear\": 34.293938, \"dividendDate\": 1715817600, \"trailingAnnualDividendRate\": 6.43, \"trailingAnnualDividendYield\": 0.0043582874, \"earningsTimestamp\": 1714681800, \"sharesOutstanding\": 15334099968 } } ] } }";
        String searchJsonStr = "{ \"quotes\": [ { \"sector\": \"Technology\", \"industry\": \"Consumer Electronics\", \"exchDisp\": \"NASDAQ\" } ] }";

        try {
            Stock stock = StockMapper.buildStockFromJson(optionsJsonStr, searchJsonStr);
            assertNotNull(stock);

            // Assert stock fields
            assertEquals("AAPL", stock.getSymbol());
            assertEquals("Apple Inc.", stock.getName());
            assertEquals("EQUITY", stock.getType());
            assertEquals("Technology", stock.getSector());
            assertEquals("Consumer Electronics", stock.getIndustry());
            assertEquals("NASDAQ", stock.getExchange());
            assertEquals("USD", stock.getCurrency());
            assertEquals(221.55, stock.getRegularMarketPrice());
            assertEquals(0.0706917, stock.getRegularMarketChangePercent());
            assertEquals(3397269848064L, stock.getMarketCap());
            assertEquals(4.837, stock.getBookValue());
            assertEquals(46.79347, stock.getPriceToBook());
            assertEquals(34.455677, stock.getTrailingPE());
            assertEquals(30.349316, stock.getForwardPE());
            assertEquals(7.3, stock.getEpsTrailingTwelveMonths());
            assertEquals(6.6, stock.getEpsCurrentYear());
            assertEquals(7.3, stock.getEpsForward());
            assertEquals(34.293938, stock.getPriceEpsCurrentYear());
            assertEquals(tsConverter.convertTimestampToDate(1715817600L), stock.getDividendDate());
            assertEquals(6.43, stock.getTrailingAnnualDividendRate());
            assertEquals(0.0043582874, stock.getTrailingAnnualDividendYield());
            assertEquals(tsConverter.convertTimestampToDate(1714681800L), stock.getEarningsTimestamp());
            assertEquals(15334099968L, stock.getSharesOutstanding());

        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

}