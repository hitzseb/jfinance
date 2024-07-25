package org.jfinance;

import org.jfinance.model.Chart;

import java.io.IOException;

public class ChartExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Chart rangeChart = YahooFinanceAPI.getChart("AAPL", "1d", "5d");
        Chart periodChart = YahooFinanceAPI.getChart("AAPL", "1d", "2024-03-01", "2024-04-01");

        System.out.println("\n" + rangeChart.toString() + "\n");
        System.out.println(rangeChart.buildTable());

        System.out.println("\n" + periodChart.toString() + "\n");
        System.out.println(periodChart.buildTable());

    }

}