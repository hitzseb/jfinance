package org.jfinance.service;

import java.sql.Time;
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

    private static final TimestampConverter instance = new TimestampConverter();

    private TimestampConverter() {}

    public static TimestampConverter getInstance() {
        return instance;
    }

    private static final String NY_TIMEZONE = "America/New_York";

    /**
     * Converts a timestamp to a formatted date string based on the specified timezone.
     *
     * @param timestamp the timestamp to convert
     * @return the formatted date string
     */
    public String convertTimestampToDate(Long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zoneId = ZoneId.of(NY_TIMEZONE);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return zonedDateTime.format(formatter);
    }

    /**
     * Converts a list of timestamps to a list of formatted date strings based on the specified timezone.
     *
     * @param timestamps the list of timestamps to convert
     * @return the list of formatted date strings
     */
    public List<String> convertTimestampsToDates(List<Long> timestamps) {
        return timestamps.stream()
                .map(timestamp -> convertTimestampToDate(timestamp))
                .collect(Collectors.toList());
    }

    /**
     * Converts a date string in ISO format to a timestamp based on the specified timezone.
     *
     * @param isoDate the date string in ISO format (e.g., "yyyy-MM-dd")
     * @return the timestamp in seconds since epoch, or -1 if parsing fails
     */
    public long convertDateToTimestamp(String isoDate) {
        try {
            LocalDate localDate = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE);
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(NY_TIMEZONE));
            return zonedDateTime.toEpochSecond();
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + isoDate);
            e.printStackTrace();
            return -1;
        }
    }

}