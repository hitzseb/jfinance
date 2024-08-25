package com.github.hitzseb;

import com.github.hitzseb.model.Stock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StockExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Testing single stock
        Stock apple = YahooFinanceAPI.getStock("AAPL");
        System.out.println(apple + "\n");

        // Testing multiple stocks
        List<String> symbols = Arrays.asList("AAPL", "MSFT", "NVDA", "META", "AMZN", "GOOGL", "TSLA");
        List<Stock> stocks = YahooFinanceAPI.getStocks(symbols);
        stocks.sort(Comparator.comparingLong(Stock::getMarketCap).reversed());
        stocks.forEach(stock -> System.out.println("Stock: " + stock.getSymbol() + ", Market Cap: " + stock.getMarketCap()));
    }

}