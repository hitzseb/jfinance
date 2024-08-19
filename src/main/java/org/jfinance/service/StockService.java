package org.jfinance.service;

import org.jfinance.model.Stock;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

        return RequestSender.sendStockRequest(optionsRequest, searchRequest, symbol);
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
        // Creates a futures list, one for each symbol
        List<CompletableFuture<Stock>> futures = symbols.stream()
                .map(symbol -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return getStock(symbol);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .collect(Collectors.toList());

        // Waits for all futures to complete and collect results
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

}