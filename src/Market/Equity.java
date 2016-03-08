package Market;

/**
 * A leaf of MarketEquity. Extends
 * MarketEquity to allow both Equities
 * and Indexes to be treated the same.
 */
public class Equity extends MarketEquity {
    private String tickerSymbol;
    private String name;
    private float value;
    private String sector;
    private String index;

    /**
     * Constructor.
     * @param tickerSymbol  The equity's name symbol
     * @param name          The equity's name
     * @param value         The equity's value
     * @param sector        The sector the equity belongs to
     * @param index         The index the equity belongs to
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
    public float getValue() {
        return value;
    }
}
