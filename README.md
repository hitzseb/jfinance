## Jfinance | Yahoo Finance API

A Java library that provides a simple interface to interact with the Yahoo Finance API and retrieve stock data. This library is inspired by the popular `yfinance` library for Python.


### Usage examples

Retrieve detailed information about a stock:

```
Stock stock = YahooFinanceAPI.getStock("AAPL");
System.out.println(stock);
```
Example output:
```
Stock{symbol='AAPL', name='Apple Inc.', type='EQUITY', sector='Technology', industry='Consumer Electronics', exchange='NASDAQ', currency='USD', exchangeTimezone='America/New_York', longBusinessSummary='Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, and HomePod. It also provides AppleCare support and cloud services; and operates various platforms, including the App Store that allow customers to discover and download applications and digital content, such as books, music, video, games, and podcasts. In addition, the company offers various services, such as Apple Arcade, a game subscription service; Apple Fitness+, a personalized fitness service; Apple Music, which offers users a curated listening experience with on-demand radio stations; Apple News+, a subscription news and magazine service; Apple TV+, which offers exclusive original content; Apple Card, a co-branded credit card; and Apple Pay, a cashless payment service, as well as licenses its intellectual property. The company serves consumers, and small and mid-sized businesses; and the education, enterprise, and government markets. It distributes third-party applications for its products through the App Store. The company also sells its products through its retail and online stores, and direct sales force; and third-party cellular network carriers, wholesalers, retailers, and resellers. Apple Inc. was founded in 1976 and is headquartered in Cupertino, California.', regularMarketPrice=218.24, regularMarketChangePercent=0.12846337, marketCap=3346514051072, bookValue=4.837, priceToBook=45.118877, averageDailyVolume3Month=68058093, averageDailyVolume10Day=52130860, fiftyTwoWeekLowChange=54.160004, fiftyTwoWeekLowChangePercent=0.3300829, fiftyTwoWeekRange='164.08 - 237.23', fiftyTwoWeekLow=164.08, fiftyTwoWeekHighChange=-18.98999, fiftyTwoWeekHighChangePercent=-0.08004886, fiftyTwoWeekHigh=237.23, trailingPE=33.940903, forwardPE=29.73297, epsTrailingTwelveMonths=6.43, epsCurrentYear=6.61, epsForward=7.34, priceEpsCurrentYear=33.01664, dividendDate='2024-05-15', trailingAnnualDividendRate=0.96, trailingAnnualDividendYield=0.004404478, earningsTimestamp='2024-08-01', sharesOutstanding=15334099968, enterpriseValue=3379656392704, floatShares=15309394128, sharesShort=135383184, shortRatio=1.83, shortPercentOfFloat=0.0088, impliedSharesOutstanding=15410899968, netIncomeToCommon=1.00389003264E11, pegRatio=3.14, enterpriseToRevenue=8.856, enterpriseToEbitda=26.072, totalCash=67150000128, totalDebt=104590000128, totalRevenue=381623009280, ebitda=129629003776, debtToEquity=140.968, revenuePerShare=24.537, returnOnAssets=0.22073999047279358, returnOnEquity=1.4725, freeCashflow=0, operatingCashflow=110563000320, earningsGrowth=0.007, revenueGrowth=-0.043, grossMargins=0.45586, ebitdaMargins=0.33968, operatingMargins=0.30743, profitMargins=0.26306}
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
2024-07-24 09:30     224,00     224,80     220,30     220,89     11171284  
2024-07-24 10:30     220,90     221,03     218,29     218,68     9222223   
2024-07-24 11:30     218,67     218,84     217,13     217,90     6271860   
2024-07-24 12:30     217,91     218,55     217,72     218,03     4492564   
2024-07-24 13:30     218,02     218,86     217,84     218,16     3875716   
2024-07-24 14:30     218,16     218,43     217,76     217,89     4462246   
2024-07-24 15:30     217,88     218,78     217,70     218,57     7678488   
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