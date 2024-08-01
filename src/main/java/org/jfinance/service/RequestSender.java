package org.jfinance.service;

import org.jfinance.mapper.ChartMapper;
import org.jfinance.model.Chart;
import org.jfinance.mapper.StockMapper;
import org.jfinance.model.Stock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class RequestSender {

    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Sends an HTTP request and returns the response.
     *
     * @param request the HTTP request to be sent
     * @return the HTTP response if the status code is 200, otherwise null
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    private static HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response;
            } else {
                System.out.println("Error obtaining data. Status code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw e; // Re-throw exception after logging
        }
        return null;
    }

    /**
     * Sends an HTTP request to obtain chart data.
     *
     * @param request the HTTP request to be sent
     * @return a Chart object if the request is successful, otherwise null
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart sendChartRequest(HttpRequest request, String format) throws IOException, InterruptedException {
        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            return ChartMapper.buildChartFromJson(response.body(), format);
        }
        return null;
    }

    /**
     * Sends an HTTP request to obtain chart data based on the specified timezone.
     *
     * @param request the HTTP request to be sent
     * @return a Chart object if the request is successful, otherwise null
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Chart sendChartRequest(HttpRequest request, String format, String timezone) throws IOException, InterruptedException {
        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            return ChartMapper.buildChartFromJson(response.body(), format, timezone);
        }
        return null;
    }

    /**
     * Sends HTTP requests to obtain stock data.
     *
     * @param optionsRequest the HTTP request for options data
     * @param searchRequest the HTTP request for search data
     * @param symbol the stock symbol to fetch data for (e.g., "AAPL" for Apple Inc.)
     * @return a Stock object if all are successful, otherwise null
     * @throws InterruptedException if the operation is interrupted
     */
    public static Stock sendStockRequest(HttpRequest optionsRequest, HttpRequest searchRequest, String symbol) throws InterruptedException {
        CompletableFuture<HttpResponse<String>> optionsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return sendRequest(optionsRequest);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });

        CompletableFuture<HttpResponse<String>> searchFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return sendRequest(searchRequest);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });

        CompletableFuture<String> quoteFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return DataScrapper.getQuote(symbol);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });

        CompletableFuture<Stock> stockFuture = optionsFuture.thenCombineAsync(searchFuture, (optionsResponse, searchResponse) -> {
            if (optionsResponse != null && searchResponse != null) {
                try {
                    return StockMapper.buildStockFromJson(optionsResponse.body(), searchResponse.body(), quoteFuture.join());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });

        try {
            return stockFuture.join(); // Wait for the combined future to complete and return the stock
        } catch (Exception e) {
            e.printStackTrace();
            throw new InterruptedException("Execution interrupted while fetching stock data");
        }
    }

}