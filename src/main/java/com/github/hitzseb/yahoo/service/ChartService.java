package com.github.hitzseb.yahoo.service;

import com.github.hitzseb.yahoo.client.YahooApiClient;
import com.github.hitzseb.yahoo.model.Chart;
import com.github.hitzseb.yahoo.util.TimestampConverter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Service class for obtaining financial chart data.
 */
public class ChartService {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    private static final String BASE_URL = "https://query1.finance.yahoo.com/v8/finance/chart/";

    private static final List<String> VALID_INTERVALS = Arrays.asList(
            "1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h",
            "1d", "5d", "1wk", "1mo", "3mo"
    );

    private static final List<String> VALID_RANGES = Arrays.asList(
            "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y",
            "5y", "10y", "ytd", "max"
    );

    private final YahooApiClient apiClient;
    private final ExecutorService executor;

    public ChartService() {
        this(new YahooApiClient(), Executors.newCachedThreadPool());
    }

    public ChartService(YahooApiClient apiClient, ExecutorService executor) {
        this.apiClient = apiClient;
        this.executor = executor;
    }

    public Chart getChartByRange(String symbol, String interval, String range) throws IOException, InterruptedException {
        return getChartByRange(symbol, interval, range, null);
    }

    public Chart getChartByRange(String symbol, String interval, String range, String timezone) throws IOException, InterruptedException {
        interval = validateInterval(interval);
        range = validateRange(range);

        String format = getFormat(interval);
        HttpRequest request = buildRequest(symbol, interval, range);

        return apiClient.sendChartRequest(request, format, timezone);
    }

    public List<Chart> getChartsByRange(List<String> symbols, String interval, String range) {
        return getChartsByRange(symbols, interval, range, null);
    }

    public List<Chart> getChartsByRange(List<String> symbols, String interval, String range, String timezone) {
        List<CompletableFuture<Chart>> futures = symbols.stream()
                .map(symbol -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return getChartByRange(symbol, interval, range, timezone);
                    } catch (IOException | InterruptedException e) {
                        throw new IllegalStateException("Failed to fetch chart for symbol: " + symbol, e);
                    }
                }, executor))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Chart getChartByPeriod(String symbol, String interval, String period1, String period2) throws IOException, InterruptedException {
        return getChartByPeriod(symbol, interval, period1, period2, null);
    }

    public Chart getChartByPeriod(String symbol, String interval, String period1, String period2, String timezone) throws IOException, InterruptedException {
        interval = validateInterval(interval);

        long period1Timestamp = TimestampConverter.convertDateToTimestamp(period1);
        long period2Timestamp = TimestampConverter.convertDateToTimestamp(period2);

        String format = getFormat(interval);
        HttpRequest request = buildRequest(symbol, interval, period1Timestamp, period2Timestamp);

        return apiClient.sendChartRequest(request, format, timezone);
    }

    public List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2) {
        return getChartsByPeriod(symbols, interval, period1, period2, null);
    }

    public List<Chart> getChartsByPeriod(List<String> symbols, String interval, String period1, String period2, String timezone) {
        List<CompletableFuture<Chart>> futures = symbols.stream()
                .map(symbol -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return getChartByPeriod(symbol, interval, period1, period2, timezone);
                    } catch (IOException | InterruptedException e) {
                        throw new IllegalStateException("Failed to fetch chart for symbol: " + symbol, e);
                    }
                }, executor))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // --- Helper Methods ---

    private String validateInterval(String interval) {
        if (interval == null || interval.isEmpty() || !VALID_INTERVALS.contains(interval)) {
            return "1d";
        }
        return interval;
    }

    private String validateRange(String range) {
        if (range == null || range.isEmpty() || !VALID_RANGES.contains(range)) {
            return "5d";
        }
        return range;
    }

    private HttpRequest buildRequest(String symbol, String interval, String range) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + symbol + "?interval=" + interval + "&range=" + range))
                .header("User-Agent", USER_AGENT)
                .header("Accept", "*/*")
                .build();
    }

    private HttpRequest buildRequest(String symbol, String interval, long period1, long period2) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + symbol + "?interval=" + interval + "&period1=" + period1 + "&period2=" + period2))
                .header("User-Agent", USER_AGENT)
                .header("Accept", "*/*")
                .build();
    }

    private String getFormat(String interval) {
        if (VALID_INTERVALS.indexOf(interval) < VALID_INTERVALS.indexOf("1d")) {
            return "yyyy-MM-dd HH:mm";
        }
        return "yyyy-MM-dd";
    }
}