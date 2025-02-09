package com.github.hitzseb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hitzseb.service.DataScrapper;

import java.io.IOException;
import java.util.Map;

public class ScrapExampleTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Map<String, String> jsonMap = DataScrapper.getQuote("MSFT");

        if (jsonMap.containsKey("quoteSummary")) {
            processQuoteSummary(jsonMap.get("quoteSummary"));
        }

        if (jsonMap.containsKey("fundamentalsTimeseries")) {
            processFundamentalsTimeseries(jsonMap.get("fundamentalsTimeseries"));
        }
    }

    private static void processQuoteSummary(String json) throws IOException {
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

    private static void processFundamentalsTimeseries(String json) throws IOException {
        JsonNode rootNode = objectMapper.readTree(json);
        String bodyJson = rootNode.path("body").asText();
        JsonNode timeseriesNode = objectMapper.readTree(bodyJson)
                .path("timeseries")
                .path("result");

        double trailingPegRatio = 0;

        for (JsonNode jsonElement : timeseriesNode) {
            JsonNode trailingPegRatioNode = jsonElement.path("trailingPegRatio");

            if (!trailingPegRatioNode.isMissingNode() && trailingPegRatioNode.isArray() && trailingPegRatioNode.size() > 1) {
                JsonNode currentTrailingPegRatio = trailingPegRatioNode.get(1);
                JsonNode reportedValueNode = currentTrailingPegRatio.path("reportedValue");

                if (!reportedValueNode.isMissingNode()) {
                    trailingPegRatio = reportedValueNode.path("raw").asDouble();
                }
            }
        }

        System.out.println("******************************\nFundamentals Timeseries Node\n******************************\n" + timeseriesNode.toPrettyString());
        System.out.println("******************************\nTrailing Peg Ratio\n******************************\n" + trailingPegRatio);
    }
}
