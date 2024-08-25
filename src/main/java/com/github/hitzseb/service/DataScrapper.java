package com.github.hitzseb.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * The DataScrapper class provides functionality to scrape financial data from Yahoo Finance.
 */
public class DataScrapper {

    /**
     * Fetches the JSON data for a given stock symbol from Yahoo Finance.
     *
     * @param symbol the stock symbol to fetch data for (e.g., "AAPL" for Apple Inc.)
     * @return a JSON string containing the stock data, or an empty string if the data cannot be found
     * @throws IOException if an I/O error occurs while fetching the data
     */
    public static String getQuote(String symbol) throws IOException {
        String url = "https://finance.yahoo.com/quote/" + symbol;
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Failed to fetch data from Yahoo Finance for symbol: " + symbol);
            throw e;
        }

        Elements scriptElements = doc.select("script[data-sveltekit-fetched][data-url]");
        for (Element scriptElement : scriptElements) {
            String dataUrl = scriptElement.attr("data-url");
            if (dataUrl.contains("https://query1.finance.yahoo.com/v10/finance/quoteSummary/")) {
                String jsonData = scriptElement.html();
                if (jsonData.isEmpty()) {
                    System.err.println("No JSON data found in the script tag for symbol: " + symbol);
                }
                return jsonData;
            }
        }

        System.err.println("No valid data URL found in the script tags for symbol: " + symbol);
        return "";
    }
}
