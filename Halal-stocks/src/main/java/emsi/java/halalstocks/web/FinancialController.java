package emsi.java.halalstocks.web;

import emsi.java.halalstocks.entities.*;
import emsi.java.halalstocks.services.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/financial")
public class FinancialController {

    @Autowired
    private FinancialDataService financialDataService;

    @GetMapping("/income-statement/{symbol}")
    public IncomeStatement[] getIncomeStatement(@PathVariable String symbol) {
        return financialDataService.getIncomeStatement(symbol);
    }

    @GetMapping("/balance-sheet/{symbol}")
    public BalanceSheet[] getBalanceSheet(@PathVariable String symbol) {
        return financialDataService.getBalanceSheet(symbol);
    }

    @GetMapping("/ratios/{symbol}")
    public FinancialRatios[] getFinancialRatios(@PathVariable String symbol) {
        return financialDataService.getFinancialRatios(symbol);
    }

    @GetMapping("/market-capitalization/{symbol}")
    public MarketCapitalization[] getMarketCapitalization(@PathVariable String symbol) {
        return financialDataService.getMarketCapitalization(symbol);
    }
    @GetMapping("/company/{symbol}")
    public Companys[] getCompanys(@PathVariable String symbol) {
        return financialDataService.getCompanys(symbol);
    }
    @GetMapping("/screener")
    public List<Screener> getScreener() {
        Screener[] screeners = financialDataService.getScreener();
        return Arrays.asList(screeners);
    }

    @GetMapping("/company-outlook/{symbol}")
    public Stocks getStocks(@PathVariable String symbol) {
        return financialDataService.getStocks(symbol);
    }


    @GetMapping("/companies/stocks")
    public List<StockList> getStockCompanies() {
        StockList[] allCompanies = financialDataService.getAllCompanies();
        return Arrays.stream(allCompanies)
                .filter(company -> "stock".equalsIgnoreCase(company.getType()))
                .collect(Collectors.toList());
    }

    // Endpoint to get companies of type 'afd'
    @GetMapping("/companies/etf")
    public List<StockList> getAfdCompanies() {
        StockList[] allCompanies = financialDataService.getAllCompanies();
        return Arrays.stream(allCompanies)
                .filter(company -> "etf".equalsIgnoreCase(company.getType()))
                .collect(Collectors.toList());
    }

}