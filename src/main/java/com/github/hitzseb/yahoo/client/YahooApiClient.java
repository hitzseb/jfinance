package com.github.hitzseb.yahoo.client;

import com.github.hitzseb.yahoo.mapper.ChartMapper;
import com.github.hitzseb.yahoo.model.Chart;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YahooApiClient {

    private final HttpClient httpClient;

    public YahooApiClient() {
        this(HttpClient.newHttpClient());
    }

    public YahooApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private String sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        }

        throw new IOException("Yahoo Finance API request failed with HTTP status code: " + response.statusCode());
    }

    public Chart sendChartRequest(HttpRequest request, String format) throws IOException, InterruptedException {
        return sendChartRequest(request, format, null);
    }

    public Chart sendChartRequest(HttpRequest request, String format, String timezone) throws IOException, InterruptedException {
        String jsonBody = sendRequest(request);

        if (timezone != null) {
            return ChartMapper.buildChartFromJson(jsonBody, format, timezone);
        }

        return ChartMapper.buildChartFromJson(jsonBody, format);
    }
}