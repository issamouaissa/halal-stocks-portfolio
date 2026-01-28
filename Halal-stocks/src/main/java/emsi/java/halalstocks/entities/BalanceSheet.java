package emsi.java.halalstocks.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    private String date;
    private String symbol;
    private String reportedCurrency;
    private String cik;
    private String fillingDate;
    private String acceptedDate;
    private String calendarYear;
    private String period;
    private long cashAndCashEquivalents;
    private long shortTermInvestments;
    private long cashAndShortTermInvestments;
    private long netReceivables;
    private long inventory;
    private long otherCurrentAssets;
    private long totalCurrentAssets;
    private long propertyPlantEquipmentNet;
    private long goodwill;
    private long intangibleAssets;
    private long goodwillAndIntangibleAssets;
    private long longTermInvestments;
    private long taxAssets;
    private long otherNonCurrentAssets;
    private long totalNonCurrentAssets;
    private long otherAssets;
    private long totalAssets;
    private long accountPayables;
    private long shortTermDebt;
    private long taxPayables;
    private long deferredRevenue;
    private long otherCurrentLiabilities;
    private long totalCurrentLiabilities;
    private long longTermDebt;
    private long deferredRevenueNonCurrent;
    private long deferredTaxLiabilitiesNonCurrent;
    private long otherNonCurrentLiabilities;
    private long totalNonCurrentLiabilities;
    private long otherLiabilities;
    private long capitalLeaseObligations;
    private long totalLiabilities;
    private long preferredStock;
    private long commonStock;
    private long retainedEarnings;
    private long accumulatedOtherComprehensiveIncomeLoss;
    private long othertotalStockholdersEquity;
    private long totalStockholdersEquity;
    private long totalEquity;
    private long totalLiabilitiesAndStockholdersEquity;
    private long minorityInterest;
    private long totalLiabilitiesAndTotalEquity;
    private long totalInvestments;
    private long totalDebt;
    private long netDebt;

    public long getTotalDebt() {
        return totalDebt;
    }

    public long getShortTermInvestments() {
        return shortTermInvestments;
    }

    public long getLongTermInvestments() {
        return longTermInvestments;
    }
}
