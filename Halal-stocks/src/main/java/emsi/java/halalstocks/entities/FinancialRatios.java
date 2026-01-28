package emsi.java.halalstocks.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialRatios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String date;
    private String calendarYear;
    private String period;
    private BigDecimal currentRatio;
    private BigDecimal quickRatio;
    private BigDecimal cashRatio;
    private BigDecimal daysOfSalesOutstanding;
    private BigDecimal daysOfInventoryOutstanding;
    private BigDecimal operatingCycle;
    private BigDecimal daysOfPayablesOutstanding;
    private BigDecimal cashConversionCycle;
    private BigDecimal grossProfitMargin;
    private BigDecimal operatingProfitMargin;
    private BigDecimal pretaxProfitMargin;
    private BigDecimal netProfitMargin;
    private BigDecimal effectiveTaxRate;
    private BigDecimal returnOnAssets;
    private BigDecimal returnOnEquity;
    private BigDecimal returnOnCapitalEmployed;
    private BigDecimal netIncomePerEBT;
    private BigDecimal ebtPerEbit;
    private BigDecimal ebitPerRevenue;
    private BigDecimal debtRatio;
    private BigDecimal debtEquityRatio;
    private BigDecimal longTermDebtToCapitalization;
    private BigDecimal totalDebtToCapitalization;
    private BigDecimal interestCoverage;
    private BigDecimal cashFlowToDebtRatio;
    private BigDecimal companyEquityMultiplier;
    private BigDecimal receivablesTurnover;
    private BigDecimal payablesTurnover;
    private BigDecimal inventoryTurnover;
    private BigDecimal fixedAssetTurnover;
    private BigDecimal assetTurnover;
    private BigDecimal operatingCashFlowPerShare;
    private BigDecimal freeCashFlowPerShare;
    private BigDecimal cashPerShare;
    private BigDecimal payoutRatio;
    private BigDecimal operatingCashFlowSalesRatio;
    private BigDecimal freeCashFlowOperatingCashFlowRatio;
    private BigDecimal cashFlowCoverageRatios;
    private BigDecimal shortTermCoverageRatios;
    private BigDecimal capitalExpenditureCoverageRatio;
    private BigDecimal dividendPaidAndCapexCoverageRatio;
    private BigDecimal dividendPayoutRatio;
    private BigDecimal priceBookValueRatio;
    private BigDecimal priceToBookRatio;
    private BigDecimal priceToSalesRatio;
    private BigDecimal priceEarningsRatio;
    private BigDecimal priceToFreeCashFlowsRatio;
    private BigDecimal priceToOperatingCashFlowsRatio;
    private BigDecimal priceCashFlowRatio;
    private BigDecimal priceEarningsToGrowthRatio;
    private BigDecimal priceSalesRatio;
    private BigDecimal dividendYield;
    private BigDecimal enterpriseValueMultiple;
    private BigDecimal priceFairValue;

    @ManyToOne
    private Company company;
}
