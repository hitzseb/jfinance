package org.jfinance.mapper;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for converting JSON nodes to lists of numbers.
 */
public class JsonConverter {

    /**
     * Converts a JSON node to a list of doubles.
     *
     * @param node the JSON node to convert
     * @return a list of doubles extracted from the JSON node
     */
    public static List<Double> convertJsonNodeToList(JsonNode node) {
        List<Double> list = new ArrayList<>();
        for (JsonNode n : node) {
            list.add(n.asDouble());
        }
        return list;
    }

    /**
     * Converts a JSON node to a list of longs.
     *
     * @param node the JSON node to convert
     * @return a list of longs extracted from the JSON node
     */
    public static List<Long> convertJsonNodeToListLong(JsonNode node) {
        List<Long> list = new ArrayList<>();
        for (JsonNode n : node) {
            list.add(n.asLong());
        }
        return list;
    }

}