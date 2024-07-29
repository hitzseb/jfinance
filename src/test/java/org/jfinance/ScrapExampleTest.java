package org.jfinance;

import org.jfinance.service.DataScrapper;

import java.io.IOException;

public class ScrapExampleTest {

    public static void main(String[] args) throws IOException {
        String json = DataScrapper.getQuote("AAPL");
        System.out.println(json);
    }

}
