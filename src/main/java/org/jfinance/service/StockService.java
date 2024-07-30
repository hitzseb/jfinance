package org.jfinance.service;

import org.jfinance.model.Stock;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

/**
 * Service class for obtaining stock data.
 */
public class StockService {

    /**
     * Base URL for options data requests.
     */
    private static final String OPTIONS_URL = "https://query1.finance.yahoo.com/v6/finance/options/";

    /**
     * Base URL for search data requests.
     */
    private static final String SEARCH_URL = "https://query1.finance.yahoo.com/v1/finance/search?q=";

    /**
     * Retrieves stock data based on the provided symbol.
     *
     * @param symbol the stock symbol to fetch data for (e.g., "AAPL" for Apple Inc.)
     * @return a Stock object containing the stock data
     * @throws IOException if an I/O exception occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public static Stock getStock(String symbol) throws IOException, InterruptedException {

        HttpRequest optionsRequest = HttpRequest.newBuilder()
                .uri(URI.create(OPTIONS_URL + symbol))
                .build();

        HttpRequest searchRequest = HttpRequest.newBuilder()
                .uri(URI.create(SEARCH_URL + symbol))
                .build();

        return RequestSender.sendStockRequest(optionsRequest, searchRequest, "yyyy-MM-dd", symbol);
    }

}