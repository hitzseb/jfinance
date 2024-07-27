## Jfinance | Yahoo Finance API

A Java library that provides a simple interface to interact with the Yahoo Finance API and retrieve stock data.

#### Usage examples
Retrieve detailed information about a stock:
```
Stock stock = YahooFinanceAPI.getStock("AAPL");
System.out.println(stock.toString());
```
Example output:
```
Stock{symbol='AAPL', name='Apple Inc.', type='EQUITY', sector='Technology', industry='Consumer Electronics', exchange='NASDAQ', currency='USD', exchangeTimezone='America/New_York', regularMarketPrice=217.69, regularMarketChangePercent=0.09195685, marketCap=3338080354304, bookValue=4.837, priceToBook=45.00517, averageDailyVolume3Month=68492950, averageDailyVolume10Day=53271200, fiftyTwoWeekLowChange=53.61, fiftyTwoWeekLowChangePercent=0.32673088, fiftyTwoWeekRange=164.08 - 237.23, fiftyTwoWeekHighChange=-19.539993, fiftyTwoWeekHighChangePercent=-0.08236729, fiftyTwoWeekLow=164.08, fiftyTwoWeekHigh=237.23, trailingPE=33.802795, forwardPE=29.658037, epsTrailingTwelveMonths=6.44, epsCurrentYear=6.61, epsForward=7.34, priceEpsCurrentYear=32.933434, dividendDate='2024-05-15', trailingAnnualDividendRate=0.96, trailingAnnualDividendYield=0.004413996, earningsTimestamp='2024-08-01', sharesOutstanding=15334099968}
```
Retrieve historical price data for a stock in a specified period. The expected arguments are:
`symbol, period1, period2` and optionally you can add a `timezone` as fourth argument.

Valid Intervals:
- "1m", "2m", "5m", "15m", "30m", "60m", "90m", "1h", "1d", "5d", "1wk", "1mo", "3mo"

```
Chart chart = YahooFinanceAPI.getChartByRange("AAPL", "1h", "1d");
System.out.println(TableBuilder.buildFromChart(chart));
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

#### Required libraries
- Jackson Databind

Maven:
```
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.1</version>
</dependency>
```
Gradle:
```
implementation 'com.fasterxml.jackson.core:jackson-databind:2.17.1'
```