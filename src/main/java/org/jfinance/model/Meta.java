package org.jfinance.model;

public class Meta {
    private String symbol;
    private String currency;
    private String exchangeTimezoneName;

    public Meta() {
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

    @Override
    public String toString() {
        return "Meta{" +
                "symbol='" + symbol + '\'' +
                ", currency='" + currency + '\'' +
                ", exchangeTimezoneName='" + exchangeTimezoneName + '\'' +
                '}';
    }
}
