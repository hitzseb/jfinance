package com.github.hitzseb.yahoo;

import com.github.hitzseb.yahoo.model.Chart;
import com.github.hitzseb.yahoo.service.ChartService;
import com.github.hitzseb.yahoo.util.TableBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Provides a unified interface for interacting with Yahoo Finance API services.
 */
public class YahooFinanceAPI {

    private final ChartService chartService;

    public YahooFinanceAPI() {
        this.chartService = new ChartService();
    }

    public YahooFinanceAPI(ChartService chartService) {
        this.chartService = chartService;
    }

    public Chart getChartByPeriod(String symbol, String interval, String period1, String period2) throws IOException, InterruptedException {
        return chartService.getChartByPeriod(symbol, interval, period1, period2);
    }

    public List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2) throws IOException, InterruptedException {
        return chartService.getChartsByPeriod(symbols, interval, period1, period2);
    }

    public Chart getChartByPeriod(String symbol, String interval, String period1, String period2, String timezone) throws IOException, InterruptedException {
        return chartService.getChartByPeriod(symbol, interval, period1, period2, timezone);
    }

    public List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2, String timezone) throws IOException, InterruptedException {
        return chartService.getChartsByPeriod(symbols, interval, period1, period2, timezone);
    }

    public Chart getChartByRange(String symbol, String interval, String range) throws IOException, InterruptedException {
        return chartService.getChartByRange(symbol, interval, range);
    }

    public List<Chart> getChartsByRange(List<String> symbols, String interval, String range) throws IOException, InterruptedException {
        return chartService.getChartsByRange(symbols, interval, range);
    }

    public Chart getChartByRange(String symbol, String interval, String range, String timezone) throws IOException, InterruptedException {
        return chartService.getChartByRange(symbol, interval, range, timezone);
    }

    public List<Chart> getChartsByRange(List<String> symbols, String interval, String range, String timezone) throws IOException, InterruptedException {
        return chartService.getChartsByRange(symbols, interval, range, timezone);
    }

    /**
     * Utility method to visualize chart data
     */
    public static String buildTable(Chart chart) {
        return TableBuilder.buildFromChart(chart);
    }
}