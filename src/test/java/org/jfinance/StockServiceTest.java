package org.jfinance;

import org.jfinance.model.Stock;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {

    @Test
    void testGetStock() throws IOException, InterruptedException {
        Stock stock = YahooFinanceAPI.getStock("AAPL");

        // Assertions
        assertNotNull(stock);
        assertNotNull(stock.getName());
        assertNotNull(stock.getExchange());
    }

}