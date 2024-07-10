package org.jfinance.model;

public class FinancialData {
    private double regularMarketPrice;
    private double regularMarketChangePercent;
    private long marketCap;
    private double bookValue;
    private double priceToBook;
    private double trailingPE;
    private double forwardPE;
    private double epsTrailingTwelveMonths;
    private double epsCurrentYear;
    private double epsForward;
    private double priceEpsCurrentYear;
    private long dividendDate;
    private double trailingAnnualDividendRate;
    private double trailingAnnualDividendYield;
    private long earningsTimestamp;
    private long sharesOutstanding;

    public FinancialData() {
    }

    public double getRegularMarketPrice() {
        return regularMarketPrice;
    }

    public void setRegularMarketPrice(double regularMarketPrice) {
        this.regularMarketPrice = regularMarketPrice;
    }

    public double getRegularMarketChangePercent() {
        return regularMarketChangePercent;
    }

    public void setRegularMarketChangePercent(double regularMarketChangePercent) {
        this.regularMarketChangePercent = regularMarketChangePercent;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getBookValue() {
        return bookValue;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public double getPriceToBook() {
        return priceToBook;
    }

    public void setPriceToBook(double priceToBook) {
        this.priceToBook = priceToBook;
    }

    public double getTrailingPE() {
        return trailingPE;
    }

    public void setTrailingPE(double trailingPE) {
        this.trailingPE = trailingPE;
    }

    public double getForwardPE() {
        return forwardPE;
    }

    public void setForwardPE(double forwardPE) {
        this.forwardPE = forwardPE;
    }

    public double getEpsTrailingTwelveMonths() {
        return epsTrailingTwelveMonths;
    }

    public void setEpsTrailingTwelveMonths(double epsTrailingTwelveMonths) {
        this.epsTrailingTwelveMonths = epsTrailingTwelveMonths;
    }

    public double getEpsCurrentYear() {
        return epsCurrentYear;
    }

    public void setEpsCurrentYear(double epsCurrentYear) {
        this.epsCurrentYear = epsCurrentYear;
    }

    public double getEpsForward() {
        return epsForward;
    }

    public void setEpsForward(double epsForward) {
        this.epsForward = epsForward;
    }

    public double getPriceEpsCurrentYear() {
        return priceEpsCurrentYear;
    }

    public void setPriceEpsCurrentYear(double priceEpsCurrentYear) {
        this.priceEpsCurrentYear = priceEpsCurrentYear;
    }

    public long getDividendDate() {
        return dividendDate;
    }

    public void setDividendDate(long dividendDate) {
        this.dividendDate = dividendDate;
    }

    public double getTrailingAnnualDividendRate() {
        return trailingAnnualDividendRate;
    }

    public void setTrailingAnnualDividendRate(double trailingAnnualDividendRate) {
        this.trailingAnnualDividendRate = trailingAnnualDividendRate;
    }

    public double getTrailingAnnualDividendYield() {
        return trailingAnnualDividendYield;
    }

    public void setTrailingAnnualDividendYield(double trailingAnnualDividendYield) {
        this.trailingAnnualDividendYield = trailingAnnualDividendYield;
    }

    public long getEarningsTimestamp() {
        return earningsTimestamp;
    }

    public void setEarningsTimestamp(long earningsTimestamp) {
        this.earningsTimestamp = earningsTimestamp;
    }

    public long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    @Override
    public String toString() {
        return "FinancialData{" +
                "regularMarketPrice=" + regularMarketPrice +
                ", regularMarketChangePercent=" + regularMarketChangePercent +
                ", marketCap=" + marketCap +
                ", bookValue=" + bookValue +
                ", priceToBook=" + priceToBook +
                ", trailingPE=" + trailingPE +
                ", forwardPE=" + forwardPE +
                ", epsTrailingTwelveMonths=" + epsTrailingTwelveMonths +
                ", epsCurrentYear=" + epsCurrentYear +
                ", epsForward=" + epsForward +
                ", priceEpsCurrentYear=" + priceEpsCurrentYear +
                ", dividendDate=" + dividendDate +
                ", trailingAnnualDividendRate=" + trailingAnnualDividendRate +
                ", trailingAnnualDividendYield=" + trailingAnnualDividendYield +
                ", earningsTimestamp=" + earningsTimestamp +
                ", sharesOutstanding=" + sharesOutstanding +
                '}';
    }
}
