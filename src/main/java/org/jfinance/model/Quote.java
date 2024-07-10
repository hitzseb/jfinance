package org.jfinance.model;

import java.util.List;

public class Quote {
    private List<Double> low;
    private List<Long> volume;
    private List<Double> open;
    private List<Double> high;
    private List<Double> close;

    public Quote() {
    }

    public List<Double> getLow() {
        return low;
    }

    public void setLow(List<Double> low) {
        this.low = low;
    }

    public List<Long> getVolume() {
        return volume;
    }

    public void setVolume(List<Long> volume) {
        this.volume = volume;
    }

    public List<Double> getOpen() {
        return open;
    }

    public void setOpen(List<Double> open) {
        this.open = open;
    }

    public List<Double> getHigh() {
        return high;
    }

    public void setHigh(List<Double> high) {
        this.high = high;
    }

    public List<Double> getClose() {
        return close;
    }

    public void setClose(List<Double> close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "low=" + low +
                ", volume=" + volume +
                ", open=" + open +
                ", high=" + high +
                ", close=" + close +
                '}';
    }
}
