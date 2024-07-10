package org.jfinance.model;

import java.util.List;

public class AdjClose {
    private List<Double> adjclose;

    public AdjClose() {
    }

    public List<Double> getAdjclose() {
        return adjclose;
    }

    public void setAdjclose(List<Double> adjclose) {
        this.adjclose = adjclose;
    }

    @Override
    public String toString() {
        return "AdjClose{" +
                "adjclose=" + adjclose +
                '}';
    }
}
