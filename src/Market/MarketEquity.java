package Market;

/**
 * The component of the MarketEquity composite
 * Pattern. Extends MarketEquity to allow both
 * Indexes and Equities to be treated the same.
 */
public class MarketEquity {
    protected String name;
    protected float value;

    /**
     * Gets the value of this Equity
     * @return  value   the value of this Equity
     */
    public float getValue() {
        return value;
    }
}
