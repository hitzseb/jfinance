package org.jfinance;

import org.jfinance.model.Chart;
import org.jfinance.service.TableBuilder;

import java.io.IOException;

public class ChartExampleTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        Chart rangeChart = YahooFinanceAPI.getChartByRange("AAPL", "1h", "1d");
        Chart periodChart = YahooFinanceAPI.getChartByPeriod("AAPL", "5d", "2024-03-01", "2024-04-01");

        System.out.println("\n" + rangeChart.toString() + "\n");
        System.out.println(TableBuilder.buildFromChart(rangeChart));

        System.out.println("\n" + periodChart.toString() + "\n");
        System.out.println(TableBuilder.buildFromChart(periodChart));

    }

}