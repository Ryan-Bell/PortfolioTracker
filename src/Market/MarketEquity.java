package Market;

import java.io.Serializable;

/**
 * The component of the MarketEquity composite
 * Pattern. Extends MarketEquity to allow both
 * Indexes and Equities to be treated the same.
 */
public class MarketEquity implements Serializable{
    protected String name;
    protected float value;
    protected String tickerSymbol;

    public MarketEquity(String name, float value, String tickerSymbol) {
        this.name = name;
        this.value = value;
        this.tickerSymbol = tickerSymbol;
    }

    /**
     * Gets the value of this Equity
     * @return  value   the value of this Equity
     */
    public float getValue() {
        return value;
    }

    public void setValue(float newValue){value = newValue;}

    public String getName() { return name; }

    public String getTickerSymbol() { return tickerSymbol; }

    public void accept(EquityVisitor visitor){}
}
