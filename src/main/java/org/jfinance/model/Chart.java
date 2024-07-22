package org.jfinance.model;

import java.util.List;

public class Chart {
    private String symbol;
    private String currency;
    private String exchangeTimezoneName;
    private List<Long> timestamp;
    private Indicators indicators;

    public Chart() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeTimezoneName() {
        return exchangeTimezoneName;
    }

    public void setExchangeTimezoneName(String exchangeTimezoneName) {
        this.exchangeTimezoneName = exchangeTimezoneName;
    }

    public List<Long> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<Long> timestamp) {
        this.timestamp = timestamp;
    }

    public Indicators getIndicators() {
        return indicators;
    }

    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "symbol='" + symbol + '\'' +
                ", currency='" + currency + '\'' +
                ", exchangeTimezoneName='" + exchangeTimezoneName + '\'' +
                ", timestamp=" + timestamp +
                ", indicators=" + indicators +
                '}';
    }

    public void printTable() {
        if (timestamp == null || timestamp.isEmpty() || indicators == null || indicators.getQuote() == null || indicators.getQuote().isEmpty()) {
            System.out.println("Chart has no data to show.");
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
