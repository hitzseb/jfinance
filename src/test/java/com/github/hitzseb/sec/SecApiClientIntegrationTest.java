package com.github.hitzseb.sec;

import com.github.hitzseb.sec.client.SecApiClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
class SecApiClientIntegrationTest {

    private static final String USER_AGENT = "jfinance hitzseb@gmail.com";

    @Test
    void fetchCompanyTickersRaw_RealApiCall() throws Exception {
        SecApiClient client = new SecApiClient(USER_AGENT);

        String rawTickers = client.fetchCompanyTickersRaw();

        assertNotNull(rawTickers);
        assertFalse(rawTickers.isBlank());

        System.out.println("=== SEC COMPANY TICKERS RAW JSON ===");
        System.out.println(rawTickers.substring(0, Math.min(rawTickers.length(), 500)) + "...\n");
    }

    @Test
    void fetchCompanyFactsRaw_RealApiCall_AppleInc() throws Exception {
        SecApiClient client = new SecApiClient(USER_AGENT);

        long appleCik = 320193L;

        String rawCompanyFacts = client.fetchCompanyFactsRaw(appleCik);

        assertNotNull(rawCompanyFacts);
        assertFalse(rawCompanyFacts.isBlank());

        System.out.println("=== SEC COMPANY FACTS RAW JSON (Apple Inc.) ===");
        System.out.println(rawCompanyFacts.substring(0, Math.min(rawCompanyFacts.length(), 1000)) + "...\n");
    }
}
