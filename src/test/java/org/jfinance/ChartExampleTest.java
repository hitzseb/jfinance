package org.jfinance;

import org.jfinance.model.Chart;

import java.io.IOException;

public class ChartExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Chart rangeChart = YahooFinanceAPI.getChartByRange("AAPL", "1h", "1d", "America/Buenos_Aires");
        Chart periodChart = YahooFinanceAPI.getChartByPeriod("AAPL", "5d", "2024-03-01", "2024-04-01");

        System.out.println("\n" + rangeChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(rangeChart));

        System.out.println("\n" + periodChart + "\n");
        System.out.println(YahooFinanceAPI.buildTable(periodChart));

    }

}