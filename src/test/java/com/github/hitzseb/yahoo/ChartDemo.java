package com.github.hitzseb.yahoo;

import com.github.hitzseb.yahoo.model.Chart;

import java.io.IOException;
import java.util.List;

public class ChartDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        YahooFinanceAPI yahooApi = new YahooFinanceAPI();

        Chart rangeChart = yahooApi.getChartByRange("AAPL", "1h", "1d", "America/Buenos_Aires");
        Chart periodChart = yahooApi.getChartByPeriod("AAPL", "5d", "2024-03-01", "2024-04-01");

        System.out.println("\n" + rangeChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(rangeChart));

        System.out.println("\n" + periodChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(periodChart));

        List<String> symbols = List.of("AAPL", "MSFT", "NVDA", "META", "AMZN", "GOOGL", "TSLA");

        List<Chart> charts = yahooApi.getChartsByRange(symbols, "1d", "1d");
        System.out.println(charts);
    }
}