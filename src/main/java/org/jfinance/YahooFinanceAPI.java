package org.jfinance;

import org.jfinance.model.Chart;
import org.jfinance.model.Stock;
import org.jfinance.service.ChartService;
import org.jfinance.service.StockService;
import org.jfinance.service.TableBuilder;

import java.io.IOException;

/**
 * Provides a unified interface for interacting with Yahoo Finance API services.
 * This class offers methods to retrieve chart and stock data.
 */
public class YahooFinanceAPI {

    private static final StockService stockService = StockService.getInstance();
    private static final ChartService chartService = ChartService.getInstance();
    private static final TableBuilder tableBuilder = TableBuilder.getInstance();

    /**
     * Retrieves stock data based on the provided stock symbol.
     *
     * @param symbol the stock symbol
     * @return a Stock object containing the stock data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Stock getStock(String symbol) throws IOException, InterruptedException {
        return stockService.getStock(symbol);
    }

    /**
     * Retrieves chart data based on the provided symbol, interval, and specific time periods.
     * The period parameters should be in "yyyy-MM-dd" format.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data (e.g., "1d", "1wk")
     * @param period1 the start date for the chart data in "yyyy-MM-dd" format
     * @param period2 the end date for the chart data in "yyyy-MM-dd" format
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart getChartByPeriod(String symbol, String interval, String period1, String period2) throws IOException, InterruptedException {
        return chartService.getChartByPeriod(symbol, interval, period1, period2);
    }

    /**
     * Retrieves chart data based on the provided symbol, interval, specific time periods, and timezone.
     * The period parameters should be in "yyyy-MM-dd" format.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data (e.g., "1d", "1wk")
     * @param period1 the start date for the chart data in "yyyy-MM-dd" format
     * @param period2 the end date for the chart data in "yyyy-MM-dd" format
     * @param timezone the time zone for the chart data
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart getChartByPeriod(String symbol, String interval, String period1, String period2, String timezone) throws IOException, InterruptedException {
        return chartService.getChartByPeriod(symbol, interval, period1, period2, timezone);
    }

    /**
     * Retrieves chart data based on the provided symbol, interval, and range.
     * The interval and range should be from the valid list of intervals and ranges.
     * Valid intervals: "1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h", "1d", "5d", "1wk", "1mo", "3mo"
     * Valid ranges: "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max"
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart getChartByRange(String symbol, String interval, String range) throws IOException, InterruptedException {
        return chartService.getChartByRange(symbol, interval, range);
    }

    /**
     * Retrieves chart data based on the provided symbol, interval, range, and timezone.
     * The interval and range should be from the valid list of intervals and ranges.
     * Valid intervals: "1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h", "1d", "5d", "1wk", "1mo", "3mo"
     * Valid ranges: "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max"
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @param timezone the time zone for the chart data
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart getChartByRange(String symbol, String interval, String range, String timezone) throws IOException, InterruptedException {
        return chartService.getChartByRange(symbol, interval, range, timezone);
    }

    /**
     * Generates a table representation of the provided chart data.
     * The table includes timestamps, opening prices, high prices, low prices, closing prices,
     * adjusted closing prices (if available), and volumes.
     *
     * @param chart the Chart object containing the data to be included in the table
     * @return a string representing the formatted table with chart data
     */
    public static String buildTable(Chart chart) {
        return tableBuilder.buildFromChart(chart);
    }

}