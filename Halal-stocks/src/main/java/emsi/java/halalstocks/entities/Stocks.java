package emsi.java.halalstocks.entities;


import lombok.Data;
import java.util.List;

@Data
public class Stocks {
    private Profile profile;
    private Metrics metrics;
    private List<Ratio> ratios;
    private List<InsideTrade> insideTrades;
    private List<KeyExecutive> keyExecutives;
    private List<SplitsHistory> splitsHistory;
    private List<StockDividend> stockDividend;
    private List<StockNews> stockNews;
    private List<Rating> rating;
    private FinancialsAnnual financialsAnnual;
    private FinancialsQuarter financialsQuarter;

    @Data
    public static class Profile {
        private String symbol;
        private double price;
        private double beta;
        private long volAvg;
        private long mktCap;
        private double lastDiv;
        private String range;
        private double changes;
        private String companyName;
        private String currency;
        private String cik;
        private String isin;
        private String cusip;
        private String exchange;
        private String exchangeShortName;
        private String industry;
        private String website;
        private String description;
        private String ceo;
        private String sector;
        private String country;
        private String fullTimeEmployees;
        private String phone;
        private String address;
        private String city;
        private String state;
        private String zip;
        private double dcfDiff;
        private double dcf;
        private String image;
        private boolean defaultImage;
        private boolean isEtf;
        private boolean isActivelyTrading;
        private boolean isAdr;
        private boolean isFund;
    }

    @Data
    public static class Metrics {
        private double dividendYielTTM;
        private long volume;
        private double yearHigh;
        private double yearLow;
    }

    @Data
    public static class Ratio {
        private double dividendYielTTM;
        private double dividendYielPercentageTTM;
        private double peRatioTTM;
        private double pegRatioTTM;
        private double payoutRatioTTM;
        private double currentRatioTTM;
        private double quickRatioTTM;
        private double cashRatioTTM;
        private double daysOfSalesOutstandingTTM;
        private double daysOfInventoryOutstandingTTM;
        private double operatingCycleTTM;
        private double daysOfPayablesOutstandingTTM;
        private double cashConversionCycleTTM;
        private double grossProfitMarginTTM;
        private double operatingProfitMarginTTM;
        private double pretaxProfitMarginTTM;
        private double netProfitMarginTTM;
        private double effectiveTaxRateTTM;
        private double returnOnAssetsTTM;
        private double returnOnEquityTTM;
        private double returnOnCapitalEmployedTTM;
        private double netIncomePerEBTTTM;
        private double ebtPerEbitTTM;
        private double ebitPerRevenueTTM;
        private double debtRatioTTM;
        private double debtEquityRatioTTM;
        private double longTermDebtToCapitalizationTTM;
        private double totalDebtToCapitalizationTTM;
        private double interestCoverageTTM;
        private double cashFlowToDebtRatioTTM;
        private double companyEquityMultiplierTTM;
        private double receivablesTurnoverTTM;
        private double payablesTurnoverTTM;
        private double inventoryTurnoverTTM;
        private double fixedAssetTurnoverTTM;
        private double assetTurnoverTTM;
        private double operatingCashFlowPerShareTTM;
        private double freeCashFlowPerShareTTM;
        private double cashPerShareTTM;
        private double operatingCashFlowSalesRatioTTM;
        private double freeCashFlowOperatingCashFlowRatioTTM;
        private double cashFlowCoverageRatiosTTM;
        private double shortTermCoverageRatiosTTM;
        private double capitalExpenditureCoverageRatioTTM;
        private double dividendPaidAndCapexCoverageRatioTTM;
        private double priceBookValueRatioTTM;
        private double priceToBookRatioTTM;
        private double priceToSalesRatioTTM;
        private double priceEarningsRatioTTM;
        private double priceToFreeCashFlowsRatioTTM;
        private double priceToOperatingCashFlowsRatioTTM;
        private double priceCashFlowRatioTTM;
        private double priceEarningsToGrowthRatioTTM;
        private double priceSalesRatioTTM;
        private double enterpriseValueMultipleTTM;
        private double priceFairValueTTM;
        private double dividendPerShareTTM;
    }

    @Data
    public static class InsideTrade {
        private String symbol;
        private String filingDate;
        private String transactionDate;
        private String reportingCik;
        private String transactionType;
        private long securitiesOwned;
        private String companyCik;
        private String reportingName;
        private String typeOfOwner;
        private String acquistionOrDisposition;
        private String formType;
        private long securitiesTransacted;
        private double price;
        private String securityName;
        private String link;
    }

    @Data
    public static class KeyExecutive {
        private String title;
        private String name;
        private double pay;
        private String currencyPay;
        private String gender;
        private int yearBorn;
        private long titleSince;
    }

    @Data
    public static class SplitsHistory {
        private String date;
        private String label;
        private int numerator;
        private int denominator;
    }

    @Data
    public static class StockDividend {
        private String date;
        private String label;
        private double adjDividend;
        private double dividend;
        private String recordDate;
        private String paymentDate;
        private String declarationDate;
    }

    @Data
    public static class StockNews {
        private String symbol;
        private String publishedDate;
        private String title;
        private String image;
        private String site;
        private String text;
        private String url;
    }

    @Data
    public static class Rating {
        private String symbol;
        private String date;
        private String rating;
        private double ratingScore;
        private String ratingRecommendation;
        private double ratingDetailsDCFScore;
        private String ratingDetailsDCFRecommendation;
        private double ratingDetailsROEScore;
        private String ratingDetailsROERecommendation;
        private double ratingDetailsROAScore;
        private String ratingDetailsROARecommendation;
        private double ratingDetailsDEScore;
        private String ratingDetailsDERecommendation;
        private double ratingDetailsPEScore;
        private String ratingDetailsPERecommendation;
        private double ratingDetailsPBScore;
        private String ratingDetailsPBRecommendation;
    }

    @Data
    public static class FinancialsAnnual {
        private List<Income> income;

        @Data
        public static class Income {
            private String date;
            private String symbol;
            private String reportedCurrency;
            private String cik;
            private String fillingDate;
            private String acceptedDate;
            private String calendarYear;
            private String period;
            private long revenue;
            private long costOfRevenue;
            private long grossProfit;
            private double grossProfitRatio;
            private long researchAndDevelopmentExpenses;
            private long generalAndAdministrativeExpenses;
            private long sellingAndMarketingExpenses;
            private long sellingGeneralAndAdministrativeExpenses;
            private long otherExpenses;
            private long operatingExpenses;
            private long costAndExpenses;
            private long interestIncome;
            private long interestExpense;
            private long depreciationAndAmortization;
            private long ebitda;
            private double ebitdaratio;
            private long operatingIncome;
            private double operatingIncomeRatio;
            private long totalOtherIncomeExpensesNet;
            private long incomeBeforeTax;
            private double incomeBeforeTaxRatio;
            private long incomeTaxExpense;
            private long netIncome;
            private double netIncomeRatio;
            private double eps;
            private double epsdiluted;
            private long weightedAverageShsOut;
            private long weightedAverageShsOutDil;
            private String link;
            private String finalLink;
        }
    }

    @Data
    public static class FinancialsQuarter {
        private List<Income> income;

        @Data
        public static class Income {
            private String date;
            private String symbol;
            private String reportedCurrency;
            private String cik;
            private String fillingDate;
            private String acceptedDate;
            private String calendarYear;
            private String period;
            private long revenue;
            private long costOfRevenue;
            private long grossProfit;
            private double grossProfitRatio;
            private long researchAndDevelopmentExpenses;
            private long generalAndAdministrativeExpenses;
            private long sellingAndMarketingExpenses;
            private long sellingGeneralAndAdministrativeExpenses;
            private long otherExpenses;
            private long operatingExpenses;
            private long costAndExpenses;
            private long interestIncome;
            private long interestExpense;
            private long depreciationAndAmortization;
            private long ebitda;
            private double ebitdaratio;
            private long operatingIncome;
            private double operatingIncomeRatio;
            private long totalOtherIncomeExpensesNet;
            private long incomeBeforeTax;
            private double incomeBeforeTaxRatio;
            private long incomeTaxExpense;
            private long netIncome;
            private double netIncomeRatio;
            private double eps;
            private double epsdiluted;
            private long weightedAverageShsOut;
            private long weightedAverageShsOutDil;
            private String link;
            private String finalLink;
        }
    }
}