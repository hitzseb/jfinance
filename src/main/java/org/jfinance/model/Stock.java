package org.jfinance.model;

public class Stock {

    private String symbol;
    private String name;
    private String type;
    private String sector;
    private String industry;
    private String exchange;
    private String currency;
    private String longBusinessSummary;
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
    private double trailingAnnualDividendRate;
    private double trailingAnnualDividendYield;
    private long sharesOutstanding;
    private long enterpriseValue;
    private long floatShares;
    private long sharesShort;
    private double shortRatio;
    private double shortPercentOfFloat;
    private long impliedSharesOutstanding;
    private double netIncomeToCommon;
    private float pegRatio;
    private float enterpriseToRevenue;
    private float enterpriseToEbitda;
    private long totalCash;
    private long totalDebt;
    private long totalRevenue;
    private long ebitda;
    private float debtToEquity;
    private float revenuePerShare;
    private double returnOnAssets;
    private float returnOnEquity;
    private long freeCashflow;
    private long operatingCashflow;
    private float earningsGrowth;
    private float revenueGrowth;
    private float grossMargins;
    private float ebitdaMargins;
    private float operatingMargins;
    private float profitMargins;

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

    public String getLongBusinessSummary() {
        return longBusinessSummary;
    }

    public void setLongBusinessSummary(String longBusinessSummary) {
        this.longBusinessSummary = longBusinessSummary;
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

    public long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public long getEnterpriseValue() {
        return enterpriseValue;
    }

    public void setEnterpriseValue(long enterpriseValue) {
        this.enterpriseValue = enterpriseValue;
    }

    public long getFloatShares() {
        return floatShares;
    }

    public void setFloatShares(long floatShares) {
        this.floatShares = floatShares;
    }

    public long getSharesShort() {
        return sharesShort;
    }

    public void setSharesShort(long sharesShort) {
        this.sharesShort = sharesShort;
    }

    public double getShortRatio() {
        return shortRatio;
    }

    public void setShortRatio(double shortRatio) {
        this.shortRatio = shortRatio;
    }

    public double getShortPercentOfFloat() {
        return shortPercentOfFloat;
    }

    public void setShortPercentOfFloat(double shortPercentOfFloat) {
        this.shortPercentOfFloat = shortPercentOfFloat;
    }

    public long getImpliedSharesOutstanding() {
        return impliedSharesOutstanding;
    }

    public void setImpliedSharesOutstanding(long impliedSharesOutstanding) {
        this.impliedSharesOutstanding = impliedSharesOutstanding;
    }

    public double getNetIncomeToCommon() {
        return netIncomeToCommon;
    }

    public void setNetIncomeToCommon(double netIncomeToCommon) {
        this.netIncomeToCommon = netIncomeToCommon;
    }

    public float getPegRatio() {
        return pegRatio;
    }

    public void setPegRatio(float pegRatio) {
        this.pegRatio = pegRatio;
    }

    public float getEnterpriseToRevenue() {
        return enterpriseToRevenue;
    }

    public void setEnterpriseToRevenue(float enterpriseToRevenue) {
        this.enterpriseToRevenue = enterpriseToRevenue;
    }

    public float getEnterpriseToEbitda() {
        return enterpriseToEbitda;
    }

    public void setEnterpriseToEbitda(float enterpriseToEbitda) {
        this.enterpriseToEbitda = enterpriseToEbitda;
    }

    public long getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(long totalCash) {
        this.totalCash = totalCash;
    }

    public long getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(long totalDebt) {
        this.totalDebt = totalDebt;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getEbitda() {
        return ebitda;
    }

    public void setEbitda(long ebitda) {
        this.ebitda = ebitda;
    }

    public float getDebtToEquity() {
        return debtToEquity;
    }

    public void setDebtToEquity(float debtToEquity) {
        this.debtToEquity = debtToEquity;
    }

    public float getRevenuePerShare() {
        return revenuePerShare;
    }

    public void setRevenuePerShare(float revenuePerShare) {
        this.revenuePerShare = revenuePerShare;
    }

    public double getReturnOnAssets() {
        return returnOnAssets;
    }

    public void setReturnOnAssets(double returnOnAssets) {
        this.returnOnAssets = returnOnAssets;
    }

    public float getReturnOnEquity() {
        return returnOnEquity;
    }

    public void setReturnOnEquity(float returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
    }

    public long getFreeCashflow() {
        return freeCashflow;
    }

    public void setFreeCashflow(long freeCashflow) {
        this.freeCashflow = freeCashflow;
    }

    public long getOperatingCashflow() {
        return operatingCashflow;
    }

    public void setOperatingCashflow(long operatingCashflow) {
        this.operatingCashflow = operatingCashflow;
    }

    public float getEarningsGrowth() {
        return earningsGrowth;
    }

    public void setEarningsGrowth(float earningsGrowth) {
        this.earningsGrowth = earningsGrowth;
    }

    public float getRevenueGrowth() {
        return revenueGrowth;
    }

    public void setRevenueGrowth(float revenueGrowth) {
        this.revenueGrowth = revenueGrowth;
    }

    public float getGrossMargins() {
        return grossMargins;
    }

    public void setGrossMargins(float grossMargins) {
        this.grossMargins = grossMargins;
    }

    public float getEbitdaMargins() {
        return ebitdaMargins;
    }

    public void setEbitdaMargins(float ebitdaMargins) {
        this.ebitdaMargins = ebitdaMargins;
    }

    public float getOperatingMargins() {
        return operatingMargins;
    }

    public void setOperatingMargins(float operatingMargins) {
        this.operatingMargins = operatingMargins;
    }

    public float getProfitMargins() {
        return profitMargins;
    }

    public void setProfitMargins(float profitMargins) {
        this.profitMargins = profitMargins;
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
                ", longBusinessSummary='" + longBusinessSummary + '\'' +
                ", regularMarketPrice=" + regularMarketPrice +
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
                ", trailingAnnualDividendRate=" + trailingAnnualDividendRate +
                ", trailingAnnualDividendYield=" + trailingAnnualDividendYield +
                ", sharesOutstanding=" + sharesOutstanding +
                ", enterpriseValue=" + enterpriseValue +
                ", floatShares=" + floatShares +
                ", sharesShort=" + sharesShort +
                ", shortRatio=" + shortRatio +
                ", shortPercentOfFloat=" + shortPercentOfFloat +
                ", impliedSharesOutstanding=" + impliedSharesOutstanding +
                ", netIncomeToCommon=" + netIncomeToCommon +
                ", pegRatio=" + pegRatio +
                ", enterpriseToRevenue=" + enterpriseToRevenue +
                ", enterpriseToEbitda=" + enterpriseToEbitda +
                ", totalCash=" + totalCash +
                ", totalDebt=" + totalDebt +
                ", totalRevenue=" + totalRevenue +
                ", ebitda=" + ebitda +
                ", debtToEquity=" + debtToEquity +
                ", revenuePerShare=" + revenuePerShare +
                ", returnOnAssets=" + returnOnAssets +
                ", returnOnEquity=" + returnOnEquity +
                ", freeCashflow=" + freeCashflow +
                ", operatingCashflow=" + operatingCashflow +
                ", earningsGrowth=" + earningsGrowth +
                ", revenueGrowth=" + revenueGrowth +
                ", grossMargins=" + grossMargins +
                ", ebitdaMargins=" + ebitdaMargins +
                ", operatingMargins=" + operatingMargins +
                ", profitMargins=" + profitMargins +
                '}';
    }
}