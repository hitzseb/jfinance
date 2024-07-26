package org.jfinance.model;

import java.util.List;

/**
 * Utility class for printing a table with chart data.
 */
public class TableBuilder {

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
    public static String buildTable(List<String> timestamps, List<Double> opens, List<Double> highs, List<Double> lows,
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
    public static String buildTableWithoutAdjClose(List<String> timestamps, List<Double> opens, List<Double> highs, List<Double> lows,
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