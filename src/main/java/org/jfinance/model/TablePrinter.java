package org.jfinance.model;

import org.jfinance.service.TimestampConverter;

import java.util.List;

/**
 * Utility class for printing a table with chart data.
 */
public class TablePrinter {

    /**
     * Prints a table with chart data including timestamps, opens, highs, lows, closes, adjCloses, and volumes.
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
    public static void printTable(List<Long> timestamps, List<Double> opens, List<Double> highs, List<Double> lows,
                                  List<Double> closes, List<Double> adjCloses, List<Long> volumes, String timeZone) {
        // Print the table heading
        System.out.printf("%-20s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Date", "Open", "High", "Low", "Close", "Adj Close", "Volume");
        System.out.println("--------------------------------------------------------------------------------------");

        // Print the table rows
        for (int i = 0; i < timestamps.size(); i++) {
            String dateStr = TimestampConverter.convertTimestampToDate(timestamps.get(i), timeZone);
            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f %-10d\n",
                    dateStr, opens.get(i), highs.get(i), lows.get(i), closes.get(i), adjCloses.get(i), volumes.get(i));
        }
    }
}
