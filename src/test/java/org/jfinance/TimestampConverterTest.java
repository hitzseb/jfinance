package org.jfinance;

import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimestampConverterTest {
    private final String isoDate = "2009-03-10";
    private final long timestamp = 1236657600L;
    private final String timeZone = "America/New_York";

    @Test
    public void testConvertDateToTimestamp() {
        long expectedTimestamp = timestamp;

        long actualTimestamp = TimestampConverter.convertDateToTimestamp(isoDate, timeZone);
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testConvertTimestampToDate() {
        String expectedDate = isoDate;

        String actualDate = TimestampConverter.convertTimestampToDate(timestamp, timeZone);
        assertEquals(expectedDate, actualDate);
    }
}