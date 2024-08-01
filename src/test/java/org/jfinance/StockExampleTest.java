package org.jfinance;

import org.jfinance.model.Stock;

import java.io.IOException;

public class StockExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Stock apple = YahooFinanceAPI.getStock("AAPL");
        System.out.println(apple);

    }

}