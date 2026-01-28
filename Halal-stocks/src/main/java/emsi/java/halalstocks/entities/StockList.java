package emsi.java.halalstocks.entities;

public class StockList {
    private String symbol;
    private String name;
    private String currency;
    private String exchange;
    private String country;
    private String ipoDate;
    private String type;

    // Constructor
    public StockList() {
    }

    public StockList(String symbol, String name, String currency, String exchange, String country, String ipoDate, String type) {
        this.symbol = symbol;
        this.name = name;
        this.currency = currency;
        this.exchange = exchange;
        this.country = country;
        this.ipoDate = ipoDate;
        this.type = type;
    }

    // Getters and Setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpoDate() {
        return ipoDate;
    }

    public void setIpoDate(String ipoDate) {
        this.ipoDate = ipoDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Override toString() method if needed for debugging
    @Override
    public String toString() {
        return "StockList{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", exchange='" + exchange + '\'' +
                ", country='" + country + '\'' +
                ", ipoDate='" + ipoDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
