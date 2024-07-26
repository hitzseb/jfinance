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

    private static final TimestampConverter instance = new TimestampConverter();

    private TimestampConverter() {}

    public static TimestampConverter getInstance() {
        return instance;
    }

    private static final String DEFAULT_TIMEZONE = "America/New_York";

    /**
     * Converts a timestamp to a formatted date string.
     *
     * @param timestamp the timestamp to convert
     * @param format the date format, e.g., "yyyy-MM-dd"
     * @return the formatted date string
     */
    public String convertTimestampToDate(Long timestamp, String format) {
        return convertTimestampToDate(timestamp, format, DEFAULT_TIMEZONE);
    }

    /**
     * Converts a timestamp to a formatted date string based on the specified timezone.
     *
     * @param timestamp the timestamp to convert
     * @param format the date format, e.g., "yyyy-MM-dd"
     * @param timezone the time zone to use for conversion
     * @return the formatted date string
     */
    public String convertTimestampToDate(Long timestamp, String format, String timezone) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return zonedDateTime.format(formatter);
    }

    /**
     * Converts a list of timestamps to a list of formatted date strings.
     *
     * @param timestamps the list of timestamps to convert
     * @param format the date format, e.g., "yyyy-MM-dd"
     * @return the list of formatted date strings
     */
    public List<String> convertTimestampsToDates(List<Long> timestamps, String format) {
        return convertTimestampsToDates(timestamps, format, DEFAULT_TIMEZONE);
    }

    /**
     * Converts a list of timestamps to a list of formatted date strings based on the specified timezone.
     *
     * @param timestamps the list of timestamps to convert
     * @param format the date format, e.g., "yyyy-MM-dd"
     * @param timezone the time zone to use for conversion
     * @return the list of formatted date strings
     */
    public List<String> convertTimestampsToDates(List<Long> timestamps, String format, String timezone) {
        return timestamps.stream()
                .map(timestamp -> convertTimestampToDate(timestamp, format, timezone))
                .collect(Collectors.toList());
    }

    /**
     * Converts a date string in ISO format to a timestamp.
     *
     * @param isoDate the date string in ISO format, e.g., "yyyy-MM-dd"
     * @return the timestamp in seconds since epoch, or -1 if parsing fails
     */
    public long convertDateToTimestamp(String isoDate) {
        return convertDateToTimestamp(isoDate, DEFAULT_TIMEZONE);
    }

    /**
     * Converts a date string in ISO format to a timestamp based on the specified timezone.
     *
     * @param isoDate the date string in ISO format, e.g., "yyyy-MM-dd"
     * @param timezone the time zone to use for conversion
     * @return the timestamp in seconds since epoch, or -1 if parsing fails
     */
    public long convertDateToTimestamp(String isoDate, String timezone) {
        try {
            LocalDate localDate = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE);
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of(timezone));
            return zonedDateTime.toEpochSecond();
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + isoDate);
            e.printStackTrace();
            return -1;
        }
    }

}