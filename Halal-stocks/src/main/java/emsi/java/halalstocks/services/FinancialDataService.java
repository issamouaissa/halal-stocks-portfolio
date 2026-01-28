package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FinancialDataService {

    private final String apiKey = "Jo81hUb49jsgogfc7uzIlYGKlKmxINWD";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://financialmodelingprep.com/api/v3/";

    public IncomeStatement[] getIncomeStatement(String symbol) {
        String url = baseUrl + "income-statement/" + symbol + "?period=annual&apikey=" + apiKey;
        IncomeStatement[] result = restTemplate.getForObject(url, IncomeStatement[].class);
        if (result == null || result.length == 0) {
            System.err.println("Income statement data unavailable for symbol: " + symbol);
        }
        return result;
    }

    public Screener[] getScreener() {
        String url = baseUrl + "stock-screener?apikey=" + apiKey;
        return restTemplate.getForObject(url, Screener[].class);
    }

    public BalanceSheet[] getBalanceSheet(String symbol) {
        String url = baseUrl + "balance-sheet-statement/" + symbol + "?period=annual&apikey=" + apiKey;
        return restTemplate.getForObject(url, BalanceSheet[].class);
    }

    public FinancialRatios[] getFinancialRatios(String symbol) {
        String url = baseUrl + "ratios/" + symbol + "?period=quarter&apikey=" + apiKey;
        return restTemplate.getForObject(url, FinancialRatios[].class);
    }

    public MarketCapitalization[] getMarketCapitalization(String symbol) {
        String url = baseUrl + "market-capitalization/" + symbol + "?apikey=" + apiKey;
        return restTemplate.getForObject(url, MarketCapitalization[].class);
    }
    //https://financialmodelingprep.com/api/v3/quote-order/AAPL
    public Companys[] getCompanys(String symbol) {
        String url = baseUrl + "quote-order/" + symbol + "?apikey=" + apiKey;
        return restTemplate.getForObject(url, Companys[].class);
    }


    //https://financialmodelingprep.com/api/v4/company-outlook?symbol=AAPL&apikey
    public Stocks getStocks(String symbol) {
        String url = "https://financialmodelingprep.com/api/v4"+"/company-outlook?symbol=" + symbol + "&apikey=" + apiKey;
        return restTemplate.getForObject(url, Stocks.class);
    }
    public StockList[] getAllCompanies() {
        String url = baseUrl + "stock/list?apikey=" + apiKey;
        return restTemplate.getForObject(url, StockList[].class);
    }


}
