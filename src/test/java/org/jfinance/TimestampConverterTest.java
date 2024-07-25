package org.jfinance;

import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimestampConverterTest {

    private static final TimestampConverter tsConverter = TimestampConverter.getInstance();

    private final String isoDate1 = "2009-03-10";
    private final String isoDate2 = "2009-03-11";
    private final long timestamp1 = 1236657600L;
    private final long timestamp2 = 1236744000L;

    @Test
    public void testConvertDateToTimestamp() {
        long expectedTimestamp = timestamp1;

        long actualTimestamp = tsConverter.convertDateToTimestamp(isoDate1);
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testConvertTimestampsToDates() {
        List<Long> timestamps = Arrays.asList(timestamp1, timestamp2);
        List<String> expectedDates = Arrays.asList(isoDate1, isoDate2);

        List<String> actualDates = tsConverter.convertTimestampsToDates(timestamps);
        assertEquals(expectedDates, actualDates);
    }

    @Test
    public void testConvertTimestampToDate() {
        String expectedDate = isoDate1;

        String actualDate = tsConverter.convertTimestampToDate(timestamp1);
        assertEquals(expectedDate, actualDate);
    }

}