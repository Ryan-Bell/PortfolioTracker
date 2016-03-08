package Market;

/**
 * The composite of MarketEquity.
 * extends MarketEquity to allow both Indexes
 * and Equities to be treated the same.
 */
public class Index extends MarketEquity {
    private MarketEquity[] children;

    /**
    * Adds a child MarketEquity to children
    * @param  child  a MarketEquity object to add
    */
    public void addChildren(MarketEquity child) {

    }
    
    /**
     * Gets the children field
     * @return  children    the array of MarketEquities
     */
    public MarketEquity[] getChildren() {
        return children;
    }

    /**
     * Gets the added value of all the children
     * @return  value   the value of all children
     */
    public float getValue() {
        float value = 0;

        return value;
    }
}
