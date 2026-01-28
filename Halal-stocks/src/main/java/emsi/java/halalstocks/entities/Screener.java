package emsi.java.halalstocks.entities;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screener {
    private long id;
    private String symbol;
    private String companyName;
    private long marketCap;
    private String sector;
    private String industry;
    private double beta;
    private double price;
    private double lastAnnualDividend;
    private long volume;
    private String exchange;
    private String exchangeShortName;
    private String country;
    private boolean etf; // Note: Use 'boolean' instead of 'isEtf' for simplicity
    private boolean activelyTrading; // Note: Use 'boolean' instead of 'isActivelyTrading' for simplicity
}
