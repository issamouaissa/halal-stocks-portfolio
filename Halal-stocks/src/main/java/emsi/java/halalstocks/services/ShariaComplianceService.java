package emsi.java.halalstocks.services;

import emsi.java.halalstocks.entities.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ShariaComplianceService {

    private final FinancialDataService financialDataService;

    public ShariaComplianceService(FinancialDataService financialDataService) {
        this.financialDataService = financialDataService;
    }

    // List of non-compliant sectors
    private final List<String> nonCompliantSectors = Arrays.asList(
            "Alcohol",
            "Gambling",
            "Conventional Financial Services",
            "Tobacco",
            "Brewers",
            "Adult Entertainment",
            "Weapons & Defense",
            "Banks",
            "Diversified",
            "Financial Services"
    );

    // Check if the sector or industry is compliant
    private boolean isSectorCompliant(String sector, String industry) {
        return !nonCompliantSectors.contains(sector) && !nonCompliantSectors.contains(industry);
    }

    // Method to calculate non-compliant revenue
    public double calculateImpermissibleRevenue(IncomeStatement incomeStatement) {
        double impermissibleRevenue = incomeStatement.getInterestIncome(); // Interest income
        // Add other non-compliant revenues if necessary
        return impermissibleRevenue;
    }

    // Method to check revenue compliance
    public boolean isRevenueCompliant(IncomeStatement incomeStatement) {
        double impermissibleRevenue = calculateImpermissibleRevenue(incomeStatement);
        double revenueRatio = impermissibleRevenue / incomeStatement.getRevenue();
        return revenueRatio < 0.05; // 5% threshold for non-compliant revenue
    }

    // Method to check interest compliance
    public boolean isInterestCompliant(BalanceSheet balanceSheet, double averageMarketCap) {
        double totalInvestments = balanceSheet.getShortTermInvestments() + balanceSheet.getLongTermInvestments();
        double interestRatio = totalInvestments / averageMarketCap;
        return interestRatio < 0.30; // 33% threshold for interest-bearing investments
    }

    // Method to check debt compliance
    public boolean isDebtCompliant(BalanceSheet balanceSheet, double averageMarketCap) {
        double debtRatio = balanceSheet.getTotalDebt() / averageMarketCap;
        return debtRatio < 0.30; // 33% threshold for debt
    }

    // Method to check short-term debt compliance
    public boolean isShortTermDebtCompliant(BalanceSheet balanceSheet) {
        double shortTermDebtRatio = balanceSheet.getShortTermDebt() / balanceSheet.getTotalCurrentAssets();
        return shortTermDebtRatio < 0.50; // 50% threshold for short-term debt
    }

    // Method to check liquidity compliance
    public boolean isLiquidityCompliant(BalanceSheet balanceSheet) {
        double currentRatio = balanceSheet.getTotalCurrentAssets() / balanceSheet.getTotalCurrentLiabilities();
        return currentRatio > 1.00; // Threshold for current ratio
    }

    // Calculate average market capitalization over 36 months
    public double calculateAverageMarketCap(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "Jo81hUb49jsgogfc7uzIlYGKlKmxINWD";
        String url = "https://financialmodelingprep.com/api/v3/historical-market-capitalization/" + symbol + "?apikey=" + apiKey;

        MarketCapitalization[] marketCapData = restTemplate.getForObject(url, MarketCapitalization[].class);

        if (marketCapData == null || marketCapData.length == 0) {
            throw new RuntimeException("Market capitalization data not available.");
        }

        double totalMarketCap = 0;
        int count = Math.min(marketCapData.length, 36);  // Maximum of 36 months
        for (int i = 0; i < count; i++) {
            totalMarketCap += marketCapData[i].getMarketCap();
        }

        return totalMarketCap / count;
    }

    // Main function to calculate Shariah compliance based on AAOIFI standards
    public ShariaComplianceResult calculateShariaCompliance(String symbol) {
        try {
            // Fetch stock data, including profile
            Stocks stocks = financialDataService.getStocks(symbol);

            // Check sector and industry compliance
            if (!isSectorCompliant(stocks.getProfile().getSector(), stocks.getProfile().getIndustry())) {
                return new ShariaComplianceResult("Not Halal");
            }

            // Fetch financial data
            IncomeStatement[] incomeStatements = financialDataService.getIncomeStatement(symbol);
            BalanceSheet[] balanceSheets = financialDataService.getBalanceSheet(symbol);

            if (incomeStatements.length == 0 || balanceSheets.length == 0) {
                throw new RuntimeException("Financial data not available for symbol: " + symbol);
            }

            IncomeStatement latestIncomeStatement = incomeStatements[0];
            BalanceSheet latestBalanceSheet = balanceSheets[0];

            // Calculate average market capitalization
            double averageMarketCap = calculateAverageMarketCap(symbol);

            // Check compliance for each criterion
            boolean revenueCompliant = isRevenueCompliant(latestIncomeStatement);
            boolean debtCompliant = isDebtCompliant(latestBalanceSheet, averageMarketCap);
            boolean interestCompliant = isInterestCompliant(latestBalanceSheet, averageMarketCap);
            boolean liquidityCompliant = isLiquidityCompliant(latestBalanceSheet);

            // Calculate ratios for doubtful condition
            double debtRatio = latestBalanceSheet.getTotalDebt() / averageMarketCap;
            double interestRatio = (latestBalanceSheet.getShortTermInvestments() + latestBalanceSheet.getLongTermInvestments()) / averageMarketCap;
            double revenueRatio = calculateImpermissibleRevenue(latestIncomeStatement) / latestIncomeStatement.getRevenue();

            // Apply AAOIFI rules for compliance categories
            if (revenueCompliant && debtCompliant && interestCompliant) {
                return new ShariaComplianceResult("Halal");
            } else if (debtRatio >= 0.30 || interestRatio >= 0.30 || revenueRatio >= 0.05) {
                return new ShariaComplianceResult("Doubtful");
            } else {
                return new ShariaComplianceResult("Not Halal");
            }

        } catch (Exception e) {
            System.err.println("Error calculating Shariah compliance for " + symbol + ": " + e.getMessage());
            return new ShariaComplianceResult("NULL");
        }
    }
}