package org.jfinance.service;

import org.jfinance.model.Chart;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for obtaining financial chart data.
 */
public class ChartService {

    private static final ChartService instance = new ChartService();

    private ChartService() {};

    public static ChartService getInstance() {
        return instance;
    }

    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();
    private static final RequestSender sender = RequestSender.getInstance();

    /**
     * List of valid intervals for chart queries.
     */
    private static final List<String> VALID_INTERVALS = Arrays.asList(
            "1d", "5d", "1wk", "1mo", "3mo"
    );

    /**
     * List of valid ranges for chart queries.
     */
    private static final List<String> VALID_RANGES = Arrays.asList(
            "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y",
            "5y", "10y", "ytd", "max"
    );

    /**
     * Base URL for HTTP requests.
     */
    private static final String BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/";

    /**
     * Retrieves chart data based on the provided symbol, interval, and range.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public Chart getChart(String symbol, String interval, String range) throws IOException, InterruptedException {
        if (interval == null || interval.isEmpty() || !VALID_INTERVALS.contains(interval)) {
            interval = "1d";  // default interval value
        }
        if (range == null || range.isEmpty() || !VALID_RANGES.contains(range)) {
            range = "5d";  // default range value
        }

        HttpRequest request = buildRequest(symbol, interval, range);

        return sender.sendChartRequest(request);
    }

    /**
     * Retrieves chart data based on the provided symbol, interval, and specific time periods.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param period1 the start date for the chart data
     * @param period2 the end date for the chart data
     * @return a Chart object containing the chart data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public Chart getChart(String symbol, String interval, String period1, String period2) throws IOException, InterruptedException {
        if (interval == null || interval.isEmpty() || !VALID_INTERVALS.contains(interval)) {
            interval = "1d";  // default interval value
        }

        long period1Timestamp = tsConverter.convertDateToTimestamp(period1);
        long period2Timestamp = tsConverter.convertDateToTimestamp(period2);

        HttpRequest request = buildRequest(symbol, interval, period1Timestamp, period2Timestamp);

        return sender.sendChartRequest(request);
    }

    /**
     * Builds an HTTP request for the specified symbol, interval, and range.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param range the range for the chart data
     * @return the built HTTP request
     */
    private static HttpRequest buildRequest(String symbol, String interval, String range) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + symbol + "?interval=" + interval + "&range=" + range))
                .build();
    }

    /**
     * Builds an HTTP request for the specified symbol, interval, and time periods.
     *
     * @param symbol the stock symbol
     * @param interval the interval for the chart data
     * @param period1 the start timestamp for the chart data
     * @param period2 the end timestamp for the chart data
     * @return the built HTTP request
     */
    private static HttpRequest buildRequest(String symbol, String interval, long period1, long period2) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + symbol + "?interval=" + interval + "&period1=" + period1 + "&period2=" + period2))
                .build();
    }

}