package com.github.hitzseb.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<String, String> getQuote(String symbol) throws IOException {
        String url = "https://finance.yahoo.com/quote/" + symbol;
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Failed to fetch data from Yahoo Finance for symbol: " + symbol);
            throw e;
        }

        String quoteSummaryData = "";
        String fundamentalsTimeseriesData = "";

        Elements scriptElements = doc.select("script[data-sveltekit-fetched][data-url]");
        for (Element scriptElement : scriptElements) {
            String dataUrl = scriptElement.attr("data-url");
            String jsonData = scriptElement.html();

            if (dataUrl.contains("https://query1.finance.yahoo.com/v10/finance/quoteSummary/")) {
                quoteSummaryData = jsonData;
            }
            if (dataUrl.contains("https://query1.finance.yahoo.com/ws/fundamentals-timeseries/v1/finance/timeseries/")) {
                fundamentalsTimeseriesData = jsonData;
            }
        }

        Map<String, String> result = new HashMap<>();
        result.put("quoteSummary", quoteSummaryData);
        result.put("fundamentalsTimeseries", fundamentalsTimeseriesData);

        if (quoteSummaryData.isEmpty()) {
            System.err.println("No quoteSummary data found for symbol: " + symbol);
        }
        if (fundamentalsTimeseriesData.isEmpty()) {
            System.err.println("No fundamentalsTimeseries data found for symbol: " + symbol);
        }

        return result;
    }
}
