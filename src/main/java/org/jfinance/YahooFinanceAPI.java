package org.jfinance;

import org.jfinance.model.Chart;
import org.jfinance.model.Stock;
import org.jfinance.service.ChartService;
import org.jfinance.service.StockService;

import java.io.IOException;

public class YahooFinanceAPI {

    private static final ChartService chartService = ChartService.getInstance();
    private static final StockService stockService = StockService.getInstance();

    /**
     * Period parameter must be a String with yyyy-MM-dd format
     */
    public static Chart getChart(String symbol, String interval, String period1, String period2) throws IOException, InterruptedException {
        return chartService.getChart(symbol, interval, period1, period2);
    }

    /**
     * List of valid intervals for chart queries.
     * "1d", "5d", "1wk", "1mo", "3mo"
     * List of valid ranges for chart queries.
     * "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max"
     */
    public static Chart getChart(String symbol, String interval, String range) throws IOException, InterruptedException {
        return chartService.getChart(symbol, interval, range);
    }

    /**
     * @param symbol refers to stock tickers
     */
    public static Stock getStock(String symbol) throws IOException, InterruptedException {
        return stockService.getStock(symbol);
    }

}