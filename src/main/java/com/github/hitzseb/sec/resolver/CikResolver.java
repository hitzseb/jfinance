package com.github.hitzseb.sec.resolver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hitzseb.sec.client.SecApiClient;
import com.github.hitzseb.sec.util.CikUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Resolver responsible for translating stock tickers into SEC CIKs.
 */
public class CikResolver {

    private final SecApiClient apiClient;
    private final ObjectMapper objectMapper;

    public CikResolver(SecApiClient apiClient) {
        this.apiClient = apiClient;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Resolves a stock ticker to a 10-digit padded CIK string.
     */
    public String resolveCik(String ticker) throws IOException, InterruptedException {
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Ticker symbol cannot be null or blank");
        }

        String targetTicker = ticker.trim().toUpperCase();
        String rawJson = apiClient.fetchCompanyTickersRaw();

        Map<String, TickerEntry> entries = objectMapper.readValue(
                rawJson,
                new TypeReference<Map<String, TickerEntry>>() {}
        );

        return entries.values().stream()
                .filter(entry -> targetTicker.equals(entry.getTicker()))
                .findFirst()
                .map(entry -> CikUtils.formatCik(entry.getCikStr()))
                .orElseThrow(() -> new IllegalArgumentException("Ticker not found in SEC registry: " + ticker));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TickerEntry {

        @JsonProperty("cik_str")
        private long cikStr;

        @JsonProperty("ticker")
        private String ticker;

        @JsonProperty("title")
        private String title;

        public long getCikStr() {
            return cikStr;
        }

        public void setCikStr(long cikStr) {
            this.cikStr = cikStr;
        }

        public String getTicker() {
            return ticker;
        }

        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}