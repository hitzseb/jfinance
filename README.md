## Jfinance | Yahoo Finance API

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
Stock{symbol='AAPL', name='Apple Inc.', type='EQUITY', sector='Technology', industry='Consumer Electronics', exchange='NASDAQ', currency='USD', longBusinessSummary='Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discover and download applications and digital content, such as books, music, video, games, and podcasts. In addition, the company offers various services, such as Apple Arcade, a game subscription service; Apple Fitness+, a personalized fitness service; Apple Music, which offers users a curated listening experience with on-demand radio stations; Apple News+, a subscription news and magazine service; Apple TV+, which offers exclusive original content; Apple Card, a co-branded credit card; and Apple Pay, a cashless payment service, as well as licenses its intellectual property. The company serves consumers, and small and mid-sized businesses; and the education, enterprise, and government markets. It distributes third-party applications for its products through the App Store. The company also sells its products through its retail and online stores, and direct sales force; and third-party cellular network carriers, wholesalers, retailers, and resellers. Apple Inc. was founded in 1976 and is headquartered in Cupertino, California.', regularMarketPrice=222.08, regularMarketChangePercent=1.4990853, marketCap=3405396836352, bookValue=4.837, priceToBook=34.538105, trailingPE=30.29741, forwardPE=6.43, epsTrailingTwelveMonths=6.62, epsCurrentYear=7.33, epsForward=33.54683, priceEpsCurrentYear=0.0, trailingAnnualDividendRate=0.0, trailingAnnualDividendYield=0.0, sharesOutstanding=15334099968, enterpriseValue=3392537100288, floatShares=15309394128, sharesShort=135383184, shortRatio=1.83, shortPercentOfFloat=0.0088, impliedSharesOutstanding=15410899968, netIncomeToCommon=1.00389003264E11, pegRatio=2.98, enterpriseToRevenue=8.89, enterpriseToEbitda=26.171, totalCash=67150000128, totalDebt=104590000128, totalRevenue=381623009280, ebitda=129629003776, debtToEquity=140.968, revenuePerShare=24.537, returnOnAssets=0.22073999047279358, returnOnEquity=1.4725, freeCashflow=84726874112, operatingCashflow=110563000320, earningsGrowth=0.007, revenueGrowth=-0.043, grossMargins=0.45586, ebitdaMargins=0.33968, operatingMargins=0.30743, profitMargins=0.26306}
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
Stock: AAPL, Market Cap: 3436886884352
Stock: MSFT, Market Cap: 3110504235008
Stock: NVDA, Market Cap: 3064456282112
Stock: GOOGL, Market Cap: 2015978323968
Stock: AMZN, Market Cap: 1858350874624
Stock: META, Market Cap: 1334277570560
Stock: TSLA, Market Cap: 690425561088
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