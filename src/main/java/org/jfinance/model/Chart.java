package org.jfinance.model;

import java.util.List;

public class Chart {
    private Meta meta;
    private List<Long> timestamp;
    private Indicators indicators;

    public Chart() {
    }

    public Chart(Meta meta, List<Long> timestamp, Indicators indicators) {
        this.meta = meta;
        this.timestamp = timestamp;
        this.indicators = indicators;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<Long> getTimestamp() {
        return timestamp;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "meta=" + meta +
                ", timestamp=" + timestamp +
                ", indicators=" + indicators +
                '}';
    }

    public void printTable() {
        if (timestamp == null || timestamp.isEmpty() || indicators == null || indicators.getQuote() == null || indicators.getQuote().isEmpty()) {
            System.out.println("No hay datos disponibles para mostrar.");
            return;
        }

        List<Double> opens = indicators.getQuote().get(0).getOpen();
        List<Double> highs = indicators.getQuote().get(0).getHigh();
        List<Double> lows = indicators.getQuote().get(0).getLow();
        List<Double> closes = indicators.getQuote().get(0).getClose();
        List<Long> volumes = indicators.getQuote().get(0).getVolume();
        List<Double> adjCloses = indicators.getAdjclose().get(0).getAdjclose();

        TablePrinter.printTable(timestamp, opens, highs, lows, closes, adjCloses, volumes, null);
    }

    public void printTable(String timeZone) {
        if (isEmpty()) {
            System.out.println("Chart has no data to show.");
            return;
        }

        List<Double> opens = indicators.getQuote().get(0).getOpen();
        List<Double> highs = indicators.getQuote().get(0).getHigh();
        List<Double> lows = indicators.getQuote().get(0).getLow();
        List<Double> closes = indicators.getQuote().get(0).getClose();
        List<Long> volumes = indicators.getQuote().get(0).getVolume();
        List<Double> adjCloses = indicators.getAdjclose().get(0).getAdjclose();

        TablePrinter.printTable(timestamp, opens, highs, lows, closes, adjCloses, volumes, timeZone);
    }

    private boolean isEmpty() {
        return (timestamp == null || timestamp.isEmpty() || indicators == null || indicators.getQuote() == null || indicators.getQuote().isEmpty());
    }
}
