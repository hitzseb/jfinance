package com.github.hitzseb;

import com.github.hitzseb.model.Chart;
import com.github.hitzseb.model.Stock;
import com.github.hitzseb.service.ChartService;
import com.github.hitzseb.service.StockService;
import com.github.hitzseb.service.TableBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Provides a unified interface for interacting with Yahoo Finance API services.
 * This class offers methods to retrieve chart and stock data.
 */
public class YahooFinanceAPI {

    /**
     * Retrieves stock data based on the provided stock symbol.
     *
     * @param symbol the stock symbol
     * @return a Stock object containing the stock data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Stock getStock(String symbol) throws IOException, InterruptedException {
        return StockService.getStock(symbol);
    }

    /**
     * Retrieves a list of stocks based on the provided list of symbols.
     * This method fetches the stock data concurrently for each symbol using
     * {@link CompletableFuture} to improve performance.
     *
     * @param symbols a list of stock symbols to fetch data for (e.g., "AAPL", "MSFT", "GOOGL")
     * @return a list of {@link Stock} objects containing the stock data
     * @throws RuntimeException if an I/O exception or interruption occurs during data retrieval
     */
    public static List<Stock> getStocks(List<String> symbols) {
        return StockService.getStocks(symbols);
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
        return ChartService.getChartByPeriod(symbol, interval, period1, period2);
    }

    /**
     * Retrieves a list of chart data based on the provided list of symbols.
     * This method fetches the chart data concurrently for each symbol using
     * {@link CompletableFuture} to improve performance.
     *
     * @param symbols a list of stock symbols to fetch data for (e.g., "AAPL", "MSFT", "GOOGL")
     * @param interval the interval for the chart data
     * @param period1 the start date for the chart data
     * @param period2 the end date for the chart data
     * @return a list of {@link Chart} objects containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2) throws IOException, InterruptedException {
        return ChartService.getChartsByPeriod(symbols, interval, period1, period2);
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
        return ChartService.getChartByPeriod(symbol, interval, period1, period2, timezone);
    }

    /**
     * Retrieves a list of chart data based on the provided list of symbols.
     * This method fetches the chart data concurrently for each symbol using
     * {@link CompletableFuture} to improve performance.
     *
     * @param symbols a list of stock symbols to fetch data for (e.g., "AAPL", "MSFT", "GOOGL")
     * @param interval the interval for the chart data
     * @param period1 the start date for the chart data
     * @param period2 the end date for the chart data
     * @param timezone the timezone for the chart data
     * @return a list of {@link Chart} objects containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2, String timezone) throws IOException, InterruptedException {
        return ChartService.getChartsByPeriod(symbols, interval, period1, period2, timezone);
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
        return ChartService.getChartByRange(symbol, interval, range);
    }

    /**
     * Retrieves a list of chart data based on the provided list of symbols.
     * This method fetches the chart data concurrently for each symbol using
     * {@link CompletableFuture} to improve performance.
     *
     * @param symbols a list of stock symbols to fetch data for (e.g., "AAPL", "MSFT", "GOOGL")
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @return a list of {@link Chart} objects containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static List<Chart> getChartsByRange(List<String> symbols, String interval, String range) throws IOException, InterruptedException {
        return ChartService.getChartsByRange(symbols, interval, range);
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
        return ChartService.getChartByRange(symbol, interval, range, timezone);
    }

    /**
     * Retrieves a list of chart data based on the provided list of symbols.
     * This method fetches the chart data concurrently for each symbol using
     * {@link CompletableFuture} to improve performance.
     *
     * @param symbols a list of stock symbols to fetch data for (e.g., "AAPL", "MSFT", "GOOGL")
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @param timezone the timezone for the chart data
     * @return a list of {@link Chart} objects containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static List<Chart> getChartsByRange(List<String> symbols, String interval, String range, String timezone) throws IOException, InterruptedException {
        return ChartService.getChartsByRange(symbols, interval, range, timezone);
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
        return TableBuilder.buildFromChart(chart);
    }

}