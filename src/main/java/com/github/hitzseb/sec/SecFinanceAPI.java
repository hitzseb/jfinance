package com.github.hitzseb.sec;

import com.github.hitzseb.sec.client.SecApiClient;
import com.github.hitzseb.sec.resolver.CikResolver;

import java.io.IOException;

public class SecFinanceAPI {

    private final SecApiClient apiClient;
    private final CikResolver cikResolver;

    /**
     * @param userAgent must have "AppName email@domain.com" format
     */
    public SecFinanceAPI(String userAgent) {
        this.apiClient = new SecApiClient(userAgent);
        this.cikResolver = new CikResolver(apiClient);
    }

    public String getCompanyFactsByTickerRaw(String ticker) throws IOException, InterruptedException {
        String cik = cikResolver.resolveCik(ticker);
        return apiClient.fetchCompanyFactsRaw(cik);
    }

    public String getCompanyTickersRaw() throws IOException, InterruptedException {
        return apiClient.fetchCompanyTickersRaw();
    }

    public String getCompanyFactsRaw(String cik) throws IOException, InterruptedException {
        return apiClient.fetchCompanyFactsRaw(cik);
    }

    public String getCompanyFactsRaw(long cik) throws IOException, InterruptedException {
        return apiClient.fetchCompanyFactsRaw(cik);
    }
}