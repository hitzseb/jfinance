package com.github.hitzseb.service;

import com.github.hitzseb.mapper.ChartMapper;
import com.github.hitzseb.model.Chart;
import com.github.hitzseb.mapper.StockMapper;
import com.github.hitzseb.model.Stock;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
     * Sends a request to obtain stock data for a given symbol by scraping data from Yahoo Finance.
     * This method does not perform an HTTP request but instead scrapes data using Jsoup, as the
     * required data is no longer available through the public API.
     *
     * @param symbol the stock symbol to fetch data for (e.g., "AAPL" for Apple Inc.)
     * @return a Stock object containing the scraped data, or null if the data could not be obtained
     * @throws IOException if an I/O exception occurs during the scraping process
     */ static Stock sendStockRequest(String symbol) throws IOException {
        return StockMapper.buildStockFromJson(DataScrapper.getQuote(symbol));
    }

}