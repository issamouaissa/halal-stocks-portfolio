package emsi.java.halalstocks.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class MarketCapitalization {
    private String symbol;
    private String date;
    private long marketCap;

    // Constructeur par défaut
    public MarketCapitalization() {
    }

    // Constructeur avec paramètres
    public MarketCapitalization(String symbol, String date, long marketCap) {
        this.symbol = symbol;
        this.date = date;
        this.marketCap = marketCap;
    }
}
