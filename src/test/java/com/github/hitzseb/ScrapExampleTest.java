package com.github.hitzseb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hitzseb.service.DataScrapper;

import java.io.IOException;

public class ScrapExampleTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String json = DataScrapper.getQuote("AAPL");
        JsonNode rootNode = objectMapper.readTree(json);
        String bodyJson = rootNode.path("body").asText();
        JsonNode bodyNode = objectMapper.readTree(bodyJson).path("quoteSummary")
                .path("result")
                .get(0);

        JsonNode priceNode = bodyNode.path("price");
        JsonNode summaryDetailNode = bodyNode.path("summaryDetail");
        JsonNode summaryProfileNode = bodyNode.path("summaryProfile");
        JsonNode financialDataNode = bodyNode.path("financialData");
        JsonNode defaultKeyStatisticsNode = bodyNode.path("defaultKeyStatistics");

        System.out.println("******************************\nPrice Node\n******************************\n" + priceNode.toPrettyString());
        System.out.println("******************************\nSummary Detail Node\n******************************\n" + summaryDetailNode.toPrettyString());
        System.out.println("******************************\nSummary Profile Node\n******************************\n" + summaryProfileNode.toPrettyString());
        System.out.println("******************************\nFinancial Data Node\n******************************\n" + financialDataNode.toPrettyString());
        System.out.println("******************************\nDefault Key Statistics Node\n******************************\n" + defaultKeyStatisticsNode.toPrettyString());
    }

}
