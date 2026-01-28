package emsi.java.halalstocks.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Companys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private String name;
    private BigDecimal price;
    private BigDecimal changesPercentage;
    private BigDecimal change;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    private BigDecimal yearHigh;
    private BigDecimal yearLow;
    private BigDecimal marketCap;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    private String exchange;
    private BigDecimal volume;
    private BigDecimal avgVolume;
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal eps;  // Earnings per share
    private BigDecimal pe;   // Price to earnings ratio
    private String earningsAnnouncement; // Date format as string
    private BigDecimal sharesOutstanding;
    private Long timestamp;

}
