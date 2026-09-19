package com.github.hitzseb.sec.client;

import com.github.hitzseb.sec.util.CikUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.zip.GZIPInputStream;

/**
 * Client for fetching raw financial data and ticker mappings directly from the SEC EDGAR API.
 */
public class SecApiClient {

    private static final String SEC_TICKERS_URL = "https://www.sec.gov/files/company_tickers.json";
    private static final String SEC_COMPANY_FACTS_URL = "https://data.sec.gov/api/xbrl/companyfacts/CIK%s.json";

    private final HttpClient httpClient;
    private final String userAgent;

    public SecApiClient(String userAgent) {
        if (userAgent == null || userAgent.isBlank()) {
            throw new IllegalArgumentException("User-Agent header is required by SEC EDGAR guidelines.");
        }
        this.userAgent = userAgent;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public String fetchCompanyTickersRaw() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SEC_TICKERS_URL))
                .header("User-Agent", this.userAgent)
                .header("Accept", "application/json")
                .GET()
                .build();

        return sendRequest(request, "Failed to fetch company tickers mapping");
    }

    public String fetchCompanyFactsRaw(long cik) throws IOException, InterruptedException {
        return fetchCompanyFactsRaw(CikUtils.formatCik(cik));
    }

    public String fetchCompanyFactsRaw(String rawCik) throws IOException, InterruptedException {
        String paddedCik = CikUtils.formatCik(rawCik);
        String targetUrl = String.format(SEC_COMPANY_FACTS_URL, paddedCik);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetUrl))
                .header("User-Agent", this.userAgent)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept", "application/json")
                .GET()
                .build();

        return sendRequest(request, "Failed to fetch company facts for CIK: " + paddedCik);
    }

    private String sendRequest(HttpRequest request, String errorMessage) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        if (response.statusCode() == 404) {
            throw new IllegalArgumentException("Requested SEC resource not found (404). Check CIK or URL.");
        }

        if (response.statusCode() != 200) {
            throw new IOException(errorMessage + " - HTTP " + response.statusCode());
        }

        String encoding = response.headers().firstValue("Content-Encoding").orElse("");
        InputStream responseStream = response.body();

        if ("gzip".equalsIgnoreCase(encoding)) {
            responseStream = new GZIPInputStream(responseStream);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
}
