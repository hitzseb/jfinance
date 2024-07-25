## Yahoo Finance API

A Java library that provides a simple interface to interact with the Yahoo Finance API and retrieve stock data.

#### Usage examples
Retrieve detailed information about a stock:
```
Stock stock = YahooFinanceAPI.getStock("AAPL");
System.out.println(stock.toString());
```
Example output:
```
Stock{symbol='AAPL', name='Apple Inc.', type='EQUITY', sector='Technology', industry='Consumer Electronics', exchange='NASDAQ', regularMarketPrice=223.96, regularMarketChangePercent=-0.15602998, marketCap=3434225074176, bookValue=4.837, priceToBook=46.30143, trailingPE=34.776398, forwardPE=30.595629, epsTrailingTwelveMonths=6.44, epsCurrentYear=6.61, epsForward=7.32, priceEpsCurrentYear=33.881996, dividendDate=1715817600, trailingAnnualDividendRate=0.96, trailingAnnualDividendYield=0.0042797914, earningsTimestamp=1722542400, sharesOutstanding=15334099968}
```
Retrieve historical price data for a stock. The expected arguments are:
`symbol, period1, period2` or `symbol, interval, range`.

Valid Intervals:
- "1d", "5d", "1wk", "1mo", "3mo"

Valid Ranges:
- "1d", "5d", "1mo", "3mo", "6mo", "1y", "2y", "5y", "10y", "ytd", "max"

```
Chart chart = YahooFinanceAPI.getChart("AAPL", "1d", "5d");
System.out.println(stock.buildTable();
```
Example output:
```
Date                 Open       High       Low        Close      Adj Close  Volume    
--------------------------------------------------------------------------------------
2024-07-16           235,00     236,27     232,33     234,82     234,82     43234300  
2024-07-17           229,45     231,46     226,64     228,88     228,88     57345900  
2024-07-18           230,28     230,44     222,27     224,18     224,18     66034600  
2024-07-19           224,82     226,80     223,28     224,31     224,31     48992100  
2024-07-22           227,27     227,78     223,09     223,96     223,96     45939219 
```

#### Required libraries
- Jackson Databind
```
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.1</version>
</dependency>
```