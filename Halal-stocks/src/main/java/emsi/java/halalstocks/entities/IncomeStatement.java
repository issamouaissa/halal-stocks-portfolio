package emsi.java.halalstocks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class IncomeStatement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String symbol;
    private long revenue;
    private long grossProfit;
    private long interestIncome;
    private long netIncome;
    // Getters and setters



    private BigDecimal estimatedRevenueLow;
    private BigDecimal estimatedRevenueHigh;
    private BigDecimal estimatedRevenueAvg;
    private BigDecimal estimatedEbitdaLow;
    private BigDecimal estimatedEbitdaHigh;
    private BigDecimal estimatedEbitdaAvg;
    private BigDecimal estimatedEbitLow;
    private BigDecimal estimatedEbitHigh;
    private BigDecimal estimatedEbitAvg;
    private BigDecimal estimatedNetIncomeLow;
    private BigDecimal estimatedNetIncomeHigh;
    private BigDecimal estimatedNetIncomeAvg;
    private BigDecimal estimatedSgaExpenseLow;
    private BigDecimal estimatedSgaExpenseHigh;
    private BigDecimal estimatedSgaExpenseAvg;
    private BigDecimal estimatedEpsAvg;
    private BigDecimal estimatedEpsHigh;
    private BigDecimal estimatedEpsLow;
    private int numberAnalystEstimatedRevenue;
    private int numberAnalystsEstimatedEps;
}
