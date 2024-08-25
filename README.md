## Jfinance

A Java library that provides a simple interface to interact with the Yahoo Finance API and retrieve stock data. This library is inspired by the popular **yfinance** library for Python.


### Introduction

**Jfinance** offers an easy way to access Yahoo Finance data using Java. With this library, you can fetch detailed stock information, historical price data, and more. It's designed to simplify the process of integrating financial data into your Java applications.

### Usage examples

Retrieve detailed information about a stock:

```
Stock stock = YahooFinanceAPI.getStock("AAPL");
System.out.println(stock);
```
Example output:
```
Stock{symbol='AAPL', name='Apple Inc.', type='EQUITY', sector='Technology', industry='Consumer Electronics', exchangeName='NasdaqGS', currency='USD', longBusinessSummary='Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discover and download applications and digital content, such as books, music, video, games, and podcasts. In addition, the company offers various services, such as Apple Arcade, a game subscription service; Apple Fitness+, a personalized fitness service; Apple Music, which offers users a curated listening experience with on-demand radio stations; Apple News+, a subscription news and magazine service; Apple TV+, which offers exclusive original content; Apple Card, a co-branded credit card; and Apple Pay, a cashless payment service, as well as licenses its intellectual property. The company serves consumers, and small and mid-sized businesses; and the education, enterprise, and government markets. It distributes third-party applications for its products through the App Store. The company also sells its products through its retail and online stores, and direct sales force; and third-party cellular network carriers, wholesalers, retailers, and resellers. Apple Inc. was founded in 1976 and is headquartered in Cupertino, California.', regularMarketPrice=226.84, regularMarketChangePercent=0.010288146, marketCap=3448898060288, bookValue=4.382, priceToBook=51.766315, trailingPE=34.474163, forwardPE=30.326202, trailingEps=6.58, forwardEps=7.48, dividendYield=0.0044, sharesOutstanding=15204100096, enterpriseValue=3488409452544, floatShares=15179810381, sharesShort=117696224, shortRatio=2.25, shortPercentOfFloat=0.0077, impliedSharesOutstanding=15410899968, netIncomeToCommon=1.01956001792E11, pegRatio=3.02, enterpriseToRevenue=9.047, enterpriseToEbitda=26.471, totalCash=61801000960, totalDebt=101304000512, totalRevenue=385603010560, ebitda=131781001216, debtToEquity=151.862, revenuePerShare=24.957, returnOnAssets=0.2261199951171875, returnOnEquity=1.60583, freeCashflow=86158123008, operatingCashflow=113040998400, earningsGrowth=0.111, revenueGrowth=0.049, grossMargins=0.45962003, ebitdaMargins=0.34175, operatingMargins=0.29556, profitMargins=0.26441}
```

Retrieve detailed information about multiple stocks concurrently:

```
List<String> symbols = Arrays.asList("AAPL", "MSFT", "NVDA", "META", "AMZN", "GOOGL", "TSLA");
List<Stock> stocks = YahooFinanceAPI.getStocks(symbols);
stocks.sort(Comparator.comparingLong(Stock::getMarketCap).reversed());
stocks.forEach(stock -> System.out.println("Stock: " + stock.getSymbol() + ", Market Cap: " + stock.getMarketCap()));
```
Example output:
```
Stock: AAPL, Market Cap: 3448898060288
Stock: NVDA, Market Cap: 3182282145792
Stock: MSFT, Market Cap: 3098016743424
Stock: GOOGL, Market Cap: 2048884998144
Stock: AMZN, Market Cap: 1858140897280
Stock: META, Market Cap: 1335744921600
Stock: TSLA, Market Cap: 703843074048
```

Retrieve historical price data for a stock in a specified period. The expected arguments are:
`symbol, period1, period2` and optionally you can add a `timezone` as fourth argument.

Valid Intervals:
- "1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h", "1d", "5d", "1wk", "1mo", "3mo"

```
Chart chart = YahooFinanceAPI.getChartByRange("AAPL", "1h", "1d");
System.out.println(YahooFinanceAPI.buildTable(chart));
```
Example output:
```
Date                 Open       High       Low        Close      Volume    
--------------------------------------------------------------------------------------
2024-07-31 10:30     221,53     223,44     220,64     222,83     10706115  
2024-07-31 11:30     222,84     223,82     222,30     223,15     5147851   
2024-07-31 12:30     223,15     223,37     222,17     222,37     3107571   
2024-07-31 13:30     222,38     223,01     222,08     222,60     3484238   
2024-07-31 14:30     222,60     223,01     221,31     222,93     3509520   
2024-07-31 15:30     222,94     223,37     221,29     221,61     4951278   
2024-07-31 16:30     221,64     223,01     221,37     221,99     5618520   
2024-07-31 17:00     222,08     222,08     222,08     222,08     0         
```

Retrieve historical price data for a stock up to a specified range. The expected arguments are:
`symbol, interval, range` and optionally you can add a `timezone` as fourth argument.

Valid Ranges:
- "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max"

```
Chart chart = YahooFinanceAPI.getChartByPeriod("AAPL", "5d", "2024-03-01", "2024-04-01");
System.out.println(TableBuilder.buildFromChart(chart));
```
Example output:
```
Date                 Open       High       Low        Close      Adj Close  Volume    
--------------------------------------------------------------------------------------
2024-03-01           179,55     180,53     177,38     179,66     179,42     73488000  
2024-03-06           171,06     171,24     168,68     169,12     168,89     68587700  
2024-03-11           172,94     174,38     172,05     172,75     172,52     60139500  
2024-03-21           177,05     177,49     170,84     171,37     171,14     106181300 
2024-03-26           170,00     171,42     169,58     169,71     169,48     57388400  
```


### Java Version

- **Java 11** or later

This project is developed with Java 11, and it is compatible with any subsequent version of Java due to its backward compatibility.


### Required libraries

- Jackson Databind
- Jsoup

Maven:
```
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.18.1</version>
</dependency>
```
Gradle:
```
implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.1'
implementation 'org.jsoup:jsoup:1.18.1'
```

### Contributing

Contributions are welcome. If you find a bug or have a suggestion for an improvement, please open an issue the on repository.