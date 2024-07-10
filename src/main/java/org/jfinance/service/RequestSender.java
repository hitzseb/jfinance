package org.jfinance.service;

import org.jfinance.mapper.ChartMapper;
import org.jfinance.model.Chart;
import org.jfinance.mapper.StockMapper;
import org.jfinance.model.Stock;

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
    public static Chart sendChartRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            return ChartMapper.buildChartfromJson(response.body());
        }
        return null;
    }

    /**
     * Sends HTTP requests to obtain stock data.
     *
     * @param optionsRequest the HTTP request for options data
     * @param searchRequest the HTTP request for search data
     * @return a Stock object if both requests are successful, otherwise null
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Stock sendStockRequest(HttpRequest optionsRequest, HttpRequest searchRequest) throws IOException, InterruptedException {
        HttpResponse<String> optionsResponse = sendRequest(optionsRequest);
        HttpResponse<String> searchResponse = sendRequest(searchRequest);

        if (optionsResponse != null && searchResponse != null) {
            return StockMapper.buildStockFromJson(optionsResponse.body(), searchResponse.body());
        }
        return null;
    }
}
