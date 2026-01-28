package emsi.java.halalstocks.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private String name;
    private BigDecimal price;
    private BigDecimal changesPercentage;

    @Column(nullable = false)
    private BigDecimal changes = BigDecimal.ZERO; // Initialisation par défaut

    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    private BigDecimal yearHigh;
    private BigDecimal yearLow;
    private BigDecimal marketCap;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    private String exchange;
    private Long volume;
    private Long avgVolume;
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal eps;
    private BigDecimal pe;
    private String earningsAnnouncement;
    private Long sharesOutstanding;
    private Long timestamp;

    private BigDecimal earningsPerShare;
    private BigDecimal dividendYield;
    private BigDecimal beta;
    private BigDecimal dividendRate;
    private BigDecimal forwardPE;
    private BigDecimal pegRatio;
    private String fiftyTwoWeekRange;
    private BigDecimal annualDividend;
    private BigDecimal priceToSales;
    private BigDecimal priceToBook;
    private String exDividendDate;
    private BigDecimal targetMeanPrice;
    private BigDecimal targetHighPrice;
    private BigDecimal targetLowPrice;
    private BigDecimal recommendationMean;
    private String recommendationKey;
    private BigDecimal trailingPE;
    private BigDecimal trailingAnnualDividendYield;
    private BigDecimal fiftyDayAverage;
    private BigDecimal twoHundredDayAverage;


    public Company(String symbol, String name, BigDecimal price, BigDecimal changesPercentage, BigDecimal changes,BigDecimal marketCap, BigDecimal eps,Long volume, Long timestamp ) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.changesPercentage = changesPercentage;
        this.changes = changes;
        this.timestamp = timestamp;
        this.volume = volume;
        this.eps = eps;
        this. marketCap = marketCap ;


    }
    public void updateFrom(Company company) {
        this.setName(company.getName());
        this.setPrice(company.getPrice());
        this.setChangesPercentage(company.getChangesPercentage());
        this.setChanges(company.getChanges());
        this.setDayLow(company.getDayLow());
        this.setDayHigh(company.getDayHigh());
        this.setYearHigh(company.getYearHigh());
        this.setYearLow(company.getYearLow());
        this.setMarketCap(company.getMarketCap());
        this.setPriceAvg50(company.getPriceAvg50());
        this.setPriceAvg200(company.getPriceAvg200());
        this.setExchange(company.getExchange());
        this.setVolume(company.getVolume());
        this.setAvgVolume(company.getAvgVolume());
        this.setOpen(company.getOpen());
        this.setPreviousClose(company.getPreviousClose());
        this.setEps(company.getEps());
        this.setPe(company.getPe());
        this.setEarningsAnnouncement(company.getEarningsAnnouncement());
        this.setSharesOutstanding(company.getSharesOutstanding());
        this.setTimestamp(company.getTimestamp());
        this.setEarningsPerShare(company.getEarningsPerShare());
        this.setDividendYield(company.getDividendYield());
        this.setBeta(company.getBeta());
        this.setDividendRate(company.getDividendRate());
        this.setForwardPE(company.getForwardPE());
        this.setPegRatio(company.getPegRatio());
        this.setFiftyTwoWeekRange(company.getFiftyTwoWeekRange());
        this.setAnnualDividend(company.getAnnualDividend());
        this.setPriceToSales(company.getPriceToSales());
        this.setPriceToBook(company.getPriceToBook());
        this.setExDividendDate(company.getExDividendDate());
        this.setTargetMeanPrice(company.getTargetMeanPrice());
        this.setTargetHighPrice(company.getTargetHighPrice());
        this.setTargetLowPrice(company.getTargetLowPrice());
        this.setRecommendationMean(company.getRecommendationMean());
        this.setRecommendationKey(company.getRecommendationKey());
        this.setTrailingPE(company.getTrailingPE());
        this.setTrailingAnnualDividendYield(company.getTrailingAnnualDividendYield());
        this.setFiftyDayAverage(company.getFiftyDayAverage());
        this.setTwoHundredDayAverage(company.getTwoHundredDayAverage());
    }

}
