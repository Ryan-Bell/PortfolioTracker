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
    protected float lowTrigger;
    protected float highTrigger;

    public MarketEquity(String name, float value, String tickerSymbol) {
        this.name = name;
        this.value = value;
        this.tickerSymbol = tickerSymbol;
        this.lowTrigger = -1;
        this.highTrigger = -1;
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

    public float getLowTrigger(){return lowTrigger;}

    public void setLowTrigger(float lowTrigger){this.lowTrigger = lowTrigger;}

    public float getHighTrigger(){return highTrigger;}

    public void setHighTrigger(float highTrigger){this.highTrigger = highTrigger;}

    public void accept(EquityVisitor visitor){System.out.println("Market Equity accepts");}

    @Override
    public String toString() {
        return "MarketEquity{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                '}';
    }
}
