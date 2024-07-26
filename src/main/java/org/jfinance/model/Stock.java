package org.jfinance.model;

public class Stock {

    private String symbol;
    private String name;
    private String type;
    private String sector;
    private String industry;
    private String exchange;
    private String currency;
    private String exchangeTimezone;
    private double regularMarketPrice;
    private double regularMarketChangePercent;
    private long marketCap;
    private double bookValue;
    private double priceToBook;
    private long averageDailyVolume3Month;
    private long averageDailyVolume10Day;
    private double fiftyTwoWeekLowChange;
    private double fiftyTwoWeekLowChangePercent;
    private String fiftyTwoWeekRange;
    private double fiftyTwoWeekLow;
    private double fiftyTwoWeekHighChange;
    private double fiftyTwoWeekHighChangePercent;
    private double fiftyTwoWeekHigh;
    private double trailingPE;
    private double forwardPE;
    private double epsTrailingTwelveMonths;
    private double epsCurrentYear;
    private double epsForward;
    private double priceEpsCurrentYear;
    private String dividendDate;
    private double trailingAnnualDividendRate;
    private double trailingAnnualDividendYield;
    private String earningsTimestamp;
    private long sharesOutstanding;

    public Stock() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeTimezone() {
        return exchangeTimezone;
    }

    public void setExchangeTimezone(String exchangeTimezone) {
        this.exchangeTimezone = exchangeTimezone;
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

    public long getAverageDailyVolume3Month() {
        return averageDailyVolume3Month;
    }

    public void setAverageDailyVolume3Month(long averageDailyVolume3Month) {
        this.averageDailyVolume3Month = averageDailyVolume3Month;
    }

    public long getAverageDailyVolume10Day() {
        return averageDailyVolume10Day;
    }

    public void setAverageDailyVolume10Day(long averageDailyVolume10Day) {
        this.averageDailyVolume10Day = averageDailyVolume10Day;
    }

    public double getFiftyTwoWeekLowChange() {
        return fiftyTwoWeekLowChange;
    }

    public void setFiftyTwoWeekLowChange(double fiftyTwoWeekLowChange) {
        this.fiftyTwoWeekLowChange = fiftyTwoWeekLowChange;
    }

    public double getFiftyTwoWeekLowChangePercent() {
        return fiftyTwoWeekLowChangePercent;
    }

    public void setFiftyTwoWeekLowChangePercent(double fiftyTwoWeekLowChangePercent) {
        this.fiftyTwoWeekLowChangePercent = fiftyTwoWeekLowChangePercent;
    }

    public String getFiftyTwoWeekRange() {
        return fiftyTwoWeekRange;
    }

    public void setFiftyTwoWeekRange(String fiftyTwoWeekRange) {
        this.fiftyTwoWeekRange = fiftyTwoWeekRange;
    }

    public double getFiftyTwoWeekHighChange() {
        return fiftyTwoWeekHighChange;
    }

    public void setFiftyTwoWeekHighChange(double fiftyTwoWeekHighChange) {
        this.fiftyTwoWeekHighChange = fiftyTwoWeekHighChange;
    }

    public double getFiftyTwoWeekHighChangePercent() {
        return fiftyTwoWeekHighChangePercent;
    }

    public void setFiftyTwoWeekHighChangePercent(double fiftyTwoWeekHighChangePercent) {
        this.fiftyTwoWeekHighChangePercent = fiftyTwoWeekHighChangePercent;
    }

    public double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
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

    public String getDividendDate() {
        return dividendDate;
    }

    public void setDividendDate(String dividendDate) {
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

    public String getEarningsTimestamp() {
        return earningsTimestamp;
    }

    public void setEarningsTimestamp(String earningsTimestamp) {
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
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", exchange='" + exchange + '\'' +
                ", currency='" + currency + '\'' +
                ", exchangeTimezone='" + exchangeTimezone + '\'' +
                ", regularMarketPrice=" + regularMarketPrice +
                ", regularMarketChangePercent=" + regularMarketChangePercent +
                ", marketCap=" + marketCap +
                ", bookValue=" + bookValue +
                ", priceToBook=" + priceToBook +
                ", averageDailyVolume3Month=" + averageDailyVolume3Month +
                ", averageDailyVolume10Day=" + averageDailyVolume10Day +
                ", fiftyTwoWeekLowChange=" + fiftyTwoWeekLowChange +
                ", fiftyTwoWeekLowChangePercent=" + fiftyTwoWeekLowChangePercent +
                ", fiftyTwoWeekRange=" + fiftyTwoWeekRange +
                ", fiftyTwoWeekHighChange=" + fiftyTwoWeekHighChange +
                ", fiftyTwoWeekHighChangePercent=" + fiftyTwoWeekHighChangePercent +
                ", fiftyTwoWeekLow=" + fiftyTwoWeekLow +
                ", fiftyTwoWeekHigh=" + fiftyTwoWeekHigh +
                ", trailingPE=" + trailingPE +
                ", forwardPE=" + forwardPE +
                ", epsTrailingTwelveMonths=" + epsTrailingTwelveMonths +
                ", epsCurrentYear=" + epsCurrentYear +
                ", epsForward=" + epsForward +
                ", priceEpsCurrentYear=" + priceEpsCurrentYear +
                ", dividendDate='" + dividendDate + '\'' +
                ", trailingAnnualDividendRate=" + trailingAnnualDividendRate +
                ", trailingAnnualDividendYield=" + trailingAnnualDividendYield +
                ", earningsTimestamp='" + earningsTimestamp + '\'' +
                ", sharesOutstanding=" + sharesOutstanding +
                '}';
    }

}