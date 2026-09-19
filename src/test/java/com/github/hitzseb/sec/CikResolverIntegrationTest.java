package com.github.hitzseb.sec;

import com.github.hitzseb.sec.client.SecApiClient;
import com.github.hitzseb.sec.resolver.CikResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class CikResolverIntegrationTest {

    private CikResolver cikResolver;

    @BeforeEach
    void setUp() {
        SecApiClient realApiClient = new SecApiClient("JFinanceTest hitzseb@example.com");
        this.cikResolver = new CikResolver(realApiClient);
    }

    @Test
    void shouldResolveRealTickerToPaddedCik() throws IOException, InterruptedException {
        String appleCik = cikResolver.resolveCik("aapl");
        assertEquals("0000320193", appleCik);
    }
}
