package org.jfinance.model;

import java.util.List;

public class Chart {

    private String symbol;
    private String currency;
    private String exchangeTimezoneName;
    private List<String> timestamp;
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

    public List<String> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<String> timestamp) {
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

}