package com.github.hitzseb;

import com.github.hitzseb.model.Chart;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ChartExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Chart rangeChart = YahooFinanceAPI.getChartByRange("AAPL", "1h", "1d", "America/Buenos_Aires");
        Chart periodChart = YahooFinanceAPI.getChartByPeriod("AAPL", "5d", "2024-03-01", "2024-04-01");

        System.out.println("\n" + rangeChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(rangeChart));

        System.out.println("\n" + periodChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(periodChart));

        List<String> symbols = Arrays.asList("AAPL", "MSFT", "NVDA", "META", "AMZN", "GOOGL", "TSLA");

        List<Chart> charts = YahooFinanceAPI.getChartsByRange(symbols, "1d", "1d");
        System.out.println(charts);

    }

}