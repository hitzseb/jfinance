package org.jfinance;

import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimestampConverterTest {

    private final String isoDate = "2009-03-10";
    private final long timestamp = 1236657600L;

    @Test
    public void testConvertDateToTimestamp() {
        long actualTimestamp = TimestampConverter.convertDateToTimestamp(isoDate);
        assertEquals(timestamp, actualTimestamp);
    }

    @Test
    public void testConvertTimestampsToDates() {
        long timestamp2 = 1236744000L;
        List<Long> timestamps = Arrays.asList(timestamp, timestamp2);
        String isoDate2 = "2009-03-11";
        List<String> expectedDates = Arrays.asList(isoDate, isoDate2);

        List<String> actualDates = TimestampConverter.convertTimestampsToDates(timestamps, "yyyy-MM-dd");
        assertEquals(expectedDates, actualDates);
    }

    @Test
    public void testConvertTimestampToDate() {
        String actualDate = TimestampConverter.convertTimestampToDate(timestamp, "yyyy-MM-dd");
        assertEquals(isoDate, actualDate);
    }

}