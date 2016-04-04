package Models;

import java.io.Serializable;

/**
 * A leaf of MarketEquity. Extends
 * MarketEquity to allow both Equities
 * and Indexes to be treated the same.
 */
public class Equity extends MarketEquity implements Serializable {
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
        super(name, value, tickerSymbol);
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

    /**
     * getter for tickerSymbol
     * @return the ticker symbol
     */
    public String getTickerSymbol(){return tickerSymbol;}

    /**
     * getter for name
     * @return the name
     */
    public String getName(){return name;}

    /**
     * getter for sector
     * @return the sector
     */
    public String getSector(){return sector;}

    /**
     * getter for index
     * @return the index
     */
    public String getIndex(){return index;}

    @Override
    public String toString(){
        return "Ticker: " + getTickerSymbol() + "\tName: " + getName() + "\tValue: " + getValue();
    }

    @Override
    public void accept(EquityVisitor visitor){
        visitor.visit(this);
    }
}
