package org.jfinance;

import org.jfinance.model.Chart;
import org.jfinance.service.ChartService;
import org.jfinance.service.TimestampConverter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimestampConverterTest {
    private final String isoDate1 = "2009-03-10";
    private final String isoDate2 = "2009-03-11";
    private final long timestamp1 = 1236657600L;
    private final long timestamp2 = 1236744000L;
    private final String timeZone = "America/New_York";

    @Test
    public void testConvertDateToTimestamp() {
        long expectedTimestamp = timestamp1;

        long actualTimestamp = TimestampConverter.convertDateToTimestamp(isoDate1, timeZone);
        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    public void testConvertTimestampsToDates() {
        List<Long> timestamps = Arrays.asList(timestamp1, timestamp2);
        List<String> expectedDates = Arrays.asList(isoDate1, isoDate2);

        List<String> actualDates = TimestampConverter.convertTimestampsToDates(timestamps, timeZone);
        assertEquals(expectedDates, actualDates);
    }

    @Test
    public void testConvertTimestampToDate() {
        String expectedDate = isoDate1;

        String actualDate = TimestampConverter.convertTimestampToDate(timestamp1, timeZone);
        assertEquals(expectedDate, actualDate);
    }
}