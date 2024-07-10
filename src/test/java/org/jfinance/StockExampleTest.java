package org.jfinance;

import org.jfinance.model.Stock;
import org.jfinance.service.StockService;

import java.io.IOException;

public class StockExampleTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Stock stock = StockService.getStock("AAPL");
        System.out.println(stock.toString());
    }
}
