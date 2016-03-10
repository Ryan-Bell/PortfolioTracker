package Market;

/**
 * A leaf of MarketEquity. Extends
 * MarketEquity to allow both Equities
 * and Indexes to be treated the same.
 */
public class Equity extends MarketEquity {
    private String tickerSymbol;
    private String name;
    private String sector;
    private String index;

    /**
     * Constructor.
     * @param tickerSymbol  the equity's name symbol
     * @param name          the equity's name
     * @param value         the equity's value
     * @param sector        the sector the equity belongs to
     * @param index         the index the equity belongs to
     */
    public Equity(String tickerSymbol, String name, float value, String sector, String index) {
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.value = value;
        this.sector = sector;
        this.index = index;
    }

    /**
     * Gets the value of this Equity
     * @return  value   the value of this Equity
     */
    @Override
    public float getValue() {
        return value;
    }

    public String getTickerSymbol(){return tickerSymbol;}

    public String getName(){return name;}

    public String getSector(){return sector;}

    public String getIndex(){return index;}
}
