package com.github.hitzseb.sec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Integration test against the real SEC API. Enable only for manual testing.")
class SecFinanceAPIIntegrationTest {

    private static final String TEST_USER_AGENT = "JFinanceTestLibrary hitzseb@example.com";

    private SecFinanceAPI api;

    @BeforeEach
    void setUp() {
        this.api = new SecFinanceAPI(TEST_USER_AGENT);
    }

    @Test
    void testFetchCompanyTickersRaw() throws IOException, InterruptedException {
        String tickersJson = api.getCompanyTickersRaw();

        assertNotNull(tickersJson, "Returned JSON should not be null");
        assertFalse(tickersJson.isBlank(), "Tickers JSON should not be blank");
        assertTrue(tickersJson.contains("title"), "JSON must include 'title' field");
        assertTrue(tickersJson.contains("cik_str"), "JSON must include 'cik_str' field");
    }

    @Test
    void testFetchCompanyFactsRaw() throws IOException, InterruptedException {
        String cikAAPL = "320193";
        String factsJson = api.getCompanyFactsRaw(cikAAPL);

        assertNotNull(factsJson, "Facts JSON should not be null");
        assertFalse(factsJson.isBlank(), "Facts JSON should not be blank");
        assertTrue(factsJson.contains("Apple Inc."), "Response must contain the company name");
        assertTrue(factsJson.contains("facts"), "Response must contain the 'facts' node");
    }

    @Test
    void testFetchCompanyFactsRawWithInvalidCikThrowsException() {
        String invalidCik = "0000000000";

        assertThrows(IllegalArgumentException.class, () -> {
            api.getCompanyFactsRaw(invalidCik);
        });
    }

    @Test
    void testGetCompanyFactsByTickerRaw_EndToEnd() throws IOException, InterruptedException {
        String ticker = "aapl";

        String factsJson = api.getCompanyFactsByTickerRaw(ticker);

        assertNotNull(factsJson, "Facts JSON should not be null");
        assertFalse(factsJson.isBlank(), "Facts JSON should not be blank");
        assertTrue(factsJson.contains("Apple Inc."), "Response must contain the company name");
        assertTrue(factsJson.contains("facts"), "Response must contain the 'facts' node");
    }
}
