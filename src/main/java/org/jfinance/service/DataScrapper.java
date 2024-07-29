package org.jfinance.service;

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
        Document doc = Jsoup.connect(url).get();
        Elements scriptElements = doc.select("script[data-sveltekit-fetched][data-url]");
        for (Element scriptElement : scriptElements) {
            String dataUrl = scriptElement.attr("data-url");
            if (dataUrl.contains("https://query1.finance.yahoo.com/v10/finance/quoteSummary/")) {
                return scriptElement.html();
            }
        }
        return "";
    }
}
