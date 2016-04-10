package Models.Market;

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
    protected String triggerStatus;

    /** Constructor
     * @param name the name of the equity
     * @param value the price of the equity
     * @param tickerSymbol the ticker symbol of the equity
     */
    public MarketEquity(String name, float value, String tickerSymbol) {
        this.name = name;
        this.value = value;
        this.tickerSymbol = tickerSymbol;
        this.lowTrigger = -1;
        this.highTrigger = -1;
        triggerStatus="";
    }

    //region GettersSetters
    /**
     * Gets the value of this Equity
     * @return  value   the value of this Equity
     */
    public float getValue() {
        return value;
    }

    /** Gets the name of this equity
     * @return the name of this equity
     */
    public String getName() { return name; }

    /** Gets the ticker of this equity
     * @return the ticker symbol of this equity
     */
    public String getTickerSymbol() { return tickerSymbol; }

    /** Gets the low trigger for this equity
     * @return the low trigger of this equity
     */
    public float getLowTrigger(){return lowTrigger;}

    /** Gets the high trigger for this equity
     * @return the high trigger of this equity
     */
    public float getHighTrigger(){return highTrigger;}

    /** Sets the value of this equity
     * @param newValue the value to set the price at
     */
    public void setValue(float newValue){value = newValue;}

    /** Sets the low trigger for this equity
     * @param lowTrigger the value to set the low trigger at
     */
    public void setLowTrigger(float lowTrigger){this.lowTrigger = lowTrigger;}

    /** Sets the high trigger for this equity
     * @param highTrigger the value to set the high trigger at
     */
    public void setHighTrigger(float highTrigger){this.highTrigger = highTrigger;}
    //endregion

    public String getTriggerStatus(){return triggerStatus;}
    public void setTriggerStatus(String triggerStatus){this.triggerStatus = triggerStatus;}

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
