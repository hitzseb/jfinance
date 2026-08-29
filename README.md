## Jfinance

A Java library that provides a simple interface to interact with the Yahoo Finance API and retrieve stock data. This library is inspired by the popular **yfinance** library for Python.


### Introduction

**Jfinance** offers an easy way to access Yahoo Finance data using Java. With this library, you can fetch detailed stock information, historical price data, and more. It's designed to simplify the process of integrating financial data into your Java applications.

### Usage examples

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
2024-10-25 10:30     229,74     232,43     229,57     232,13     8235944   
2024-10-25 11:30     232,12     233,20     232,12     232,23     4138746   
2024-10-25 12:30     232,23     232,73     231,76     232,71     3211126   
2024-10-25 13:30     232,74     232,94     231,53     231,86     2367087   
2024-10-25 14:30     231,83     231,97     231,07     231,84     2818009   
2024-10-25 15:30     231,85     232,20     231,37     231,62     2642893   
2024-10-25 16:30     231,65     231,80     231,11     231,40     3572932   
2024-10-25 17:00     231,41     231,41     231,41     231,41     0         
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

Retrieve historical price data for multiple stocks concurrently:

```
List<String> symbols = Arrays.asList("AAPL", "MSFT", "NVDA", "META", "AMZN", "GOOGL", "TSLA");
List<Chart> charts = YahooFinanceAPI.getChartsByRange(symbols, "1d", "1d");
System.out.println(charts);
```
Example output:
```
[Chart{symbol='AAPL', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[229.57000732421875], volume=[38776700], open=[229.74000549316406], high=[233.22000122070312], close=[231.41000366210938]}], adjclose=[AdjClose{adjclose=[231.41000366210938]}]}}, Chart{symbol='MSFT', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[426.57000732421875], volume=[16888400], open=[426.760009765625], high=[432.5199890136719], close=[428.1499938964844]}], adjclose=[AdjClose{adjclose=[428.1499938964844]}]}}, Chart{symbol='NVDA', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[140.8000030517578], volume=[204182400], open=[140.92999267578125], high=[144.1300048828125], close=[141.5399932861328]}], adjclose=[AdjClose{adjclose=[141.5399932861328]}]}}, Chart{symbol='META', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[571.719970703125], volume=[11318300], open=[573.9299926757812], high=[581.2899780273438], close=[573.25]}], adjclose=[AdjClose{adjclose=[573.25]}]}}, Chart{symbol='AMZN', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[187.52999877929688], volume=[29310400], open=[187.85000610351562], high=[190.4499969482422], close=[187.8300018310547]}], adjclose=[AdjClose{adjclose=[187.8300018310547]}]}}, Chart{symbol='GOOGL', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[163.4199981689453], volume=[19805000], open=[163.6699981689453], high=[165.58999633789062], close=[165.27000427246094]}], adjclose=[AdjClose{adjclose=[165.27000427246094]}]}}, Chart{symbol='TSLA', currency='USD', exchangeTimezoneName='America/New_York', timestamp=[2024-10-25], indicators=Indicators{quote=[Quote{low=[255.32000732421875], volume=[161061400], open=[256.010009765625], high=[269.489990234375], close=[269.19000244140625]}], adjclose=[AdjClose{adjclose=[269.19000244140625]}]}}]
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

### Contributing

Contributions are welcome. If you find a bug or have a suggestion for an improvement, please open an issue.
