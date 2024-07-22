package org.jfinance.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting timestamps to dates and vice versa.
 */
public class TimestampConverter {

    private static final String DEFAULT_TIMEZONE = "America/New_York";

    /**
     * Converts a timestamp to a formatted date string based on the specified timezone.
     *
     * @param timestamp the timestamp to convert
     * @param timeZone the timezone identifier (e.g., "America/New_York")
     * @return the formatted date string
     */
    public static String convertTimestampToDate(Long timestamp, String timeZone) {
        if (timeZone == null || timeZone.isEmpty()) {
            timeZone = DEFAULT_TIMEZONE;
        }
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zoneId = ZoneId.of(timeZone);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return zonedDateTime.format(formatter);
    }

    /**
     * Converts a list of timestamps to a list of formatted date strings based on the specified timezone.
     *
     * @param timestamps the list of timestamps to convert
     * @param timeZone the timezone identifier (e.g., "America/New_York")
     * @return the list of formatted date strings
     */
    public static List<String> convertTimestampsToDates(List<Long> timestamps, String timeZone) {
        return timestamps.stream()
                .map(timestamp -> convertTimestampToDate(timestamp, timeZone))
                .collect(Collectors.toList());
    }

    /**
     * Converts a date string in ISO format to a timestamp based on the specified timezone.
     *
     * @param isoDate the date string in ISO format (e.g., "yyyy-MM-dd")
     * @param timeZone the timezone identifier (e.g., "America/New_York")
     * @return the timestamp in seconds since epoch, or -1 if parsing fails
     */
    public static long convertDateToTimestamp(String isoDate, String timeZone) {
        if (timeZone == null || timeZone.isEmpty()) {
            timeZone = DEFAULT_TIMEZONE;
        }
        try {
            LocalDate localDate = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE);
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(timeZone));
            return zonedDateTime.toEpochSecond();
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + isoDate);
            e.printStackTrace();
            return -1;
        }
    }

}
