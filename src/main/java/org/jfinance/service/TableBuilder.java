package org.jfinance.service;

import org.jfinance.model.Chart;
import org.jfinance.model.Indicators;

import java.util.List;

/**
 * Utility class for printing a table with chart data.
 */
public class TableBuilder {

    /**
     * Generates a table based on the given chart data. If adjusted closing prices are available, they are included.
     *
     * @param chart the chart containing the data to include in the table
     * @return the formatted table as a string
     */
    public static String buildFromChart(Chart chart) {
        if (isEmpty(chart)) {
            return "Chart has no data to show.";
        }

        Indicators indicators = chart.getIndicators();

        List<Double> opens = indicators.getQuote().get(0).getOpen();
        List<Double> highs = indicators.getQuote().get(0).getHigh();
        List<Double> lows = indicators.getQuote().get(0).getLow();
        List<Double> closes = indicators.getQuote().get(0).getClose();
        List<Long> volumes = indicators.getQuote().get(0).getVolume();

        List<Double> adjCloses = null;
        if (indicators.getAdjclose() != null && !indicators.getAdjclose().isEmpty()) {
            adjCloses = indicators.getAdjclose().get(0).getAdjclose();
        }

        List<String> timestamp = chart.getTimestamp();

        if (adjCloses != null) {
            return buildFullTable(timestamp, opens, highs, lows, closes, adjCloses, volumes, null);
        } else {
            return buildTableWithoutAdjClose(timestamp, opens, highs, lows, closes, volumes, null);
        }
    }

    /**
     * Checks if the chart is empty.
     *
     * @param chart the chart to check
     * @return true if the chart is empty, false otherwise
     */
    private static boolean isEmpty(Chart chart) {
        return (chart.getTimestamp() == null || chart.getTimestamp().isEmpty() ||
                chart.getIndicators() == null || chart.getIndicators().getQuote() == null ||
                chart.getIndicators().getQuote().isEmpty());
    }

    /**
     * Builds a table with chart data including timestamps, opens, highs, lows, closes, adjCloses, and volumes.
     *
     * @param timestamps the list of timestamps to print
     * @param opens the list of opening prices to print
     * @param highs the list of high prices to print
     * @param lows the list of low prices to print
     * @param closes the list of closing prices to print
     * @param adjCloses the list of adjusted closing prices to print
     * @param volumes the list of volumes to print
     * @param timeZone the timezone identifier for converting timestamps to dates
     */
    private static String buildFullTable(List<String> timestamps, List<Double> opens, List<Double> highs, List<Double> lows,
                                    List<Double> closes, List<Double> adjCloses, List<Long> volumes, String timeZone) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Date", "Open", "High", "Low", "Close", "Adj Close", "Volume"));
        sb.append("--------------------------------------------------------------------------------------\n");

        for (int i = 0; i < timestamps.size(); i++) {
            sb.append(String.format("%-20s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f %-10d\n",
                    timestamps.get(i), opens.get(i), highs.get(i), lows.get(i), closes.get(i), adjCloses.get(i), volumes.get(i)));
        }

        return sb.toString();
    }

    /**
     * Builds a table with chart data excluding adjCloses.
     *
     * @param timestamps the list of timestamps to print
     * @param opens the list of opening prices to print
     * @param highs the list of high prices to print
     * @param lows the list of low prices to print
     * @param closes the list of closing prices to print
     * @param volumes the list of volumes to print
     * @param timeZone the timezone identifier for converting timestamps to dates
     */
    private static String buildTableWithoutAdjClose(List<String> timestamps, List<Double> opens, List<Double> highs, List<Double> lows,
                                                   List<Double> closes, List<Long> volumes, String timeZone) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s\n", "Date", "Open", "High", "Low", "Close", "Volume"));
        sb.append("--------------------------------------------------------------------------------------\n");

        for (int i = 0; i < timestamps.size(); i++) {
            sb.append(String.format("%-20s %-10.2f %-10.2f %-10.2f %-10.2f %-10d\n",
                    timestamps.get(i), opens.get(i), highs.get(i), lows.get(i), closes.get(i), volumes.get(i)));
        }

        return sb.toString();
    }

}