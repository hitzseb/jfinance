package org.jfinance;

import org.jfinance.model.Stock;
import org.jfinance.service.StockService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {
    @Test
    void testGetStock() throws IOException, InterruptedException {
        String symbol = "AAPL";

        Stock stock = StockService.getStock(symbol);

        // Assertions
        assertNotNull(stock);
        assertNotNull(stock.getName());
        assertNotNull(stock.getExchange());
        assertNotNull(stock.getFinancialData());
        assertNotNull(stock.getFinancialData().getRegularMarketPrice());
        assertNotNull(stock.getFinancialData().getTrailingPE());
        assertNotNull(stock.getFinancialData().getEpsTrailingTwelveMonths());
        assertNotNull(stock.getFinancialData().getMarketCap());
        assertNotNull(stock.getFinancialData().getBookValue());
    }
}