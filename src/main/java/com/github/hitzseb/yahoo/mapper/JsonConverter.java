package com.github.hitzseb.yahoo.mapper;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for converting JSON nodes to lists of numbers.
 */
public class JsonConverter {

    public static List<Double> convertJsonNodeToList(JsonNode node) {
        List<Double> list = new ArrayList<>();
        if (node != null && node.isArray()) {
            for (JsonNode n : node) {
                if (n.isNull()) {
                    list.add(null);
                } else {
                    list.add(n.asDouble());
                }
            }
        }
        return list;
    }

    public static List<Long> convertJsonNodeToListLong(JsonNode node) {
        List<Long> list = new ArrayList<>();
        if (node != null && node.isArray()) {
            for (JsonNode n : node) {
                if (n.isNull()) {
                    list.add(null);
                } else {
                    list.add(n.asLong());
                }
            }
        }
        return list;
    }
}
