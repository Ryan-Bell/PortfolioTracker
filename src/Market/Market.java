package Market;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market {
    private MarketEquity[] marketEquities;

    /**
     * Creates and adds a MarketEquity to the
     * marketEquities Array.
     * @param tickerSymbol  the equity's name symbol
     * @param name          the equity's name
     * @param value         the equity's value
     * @param sector        the sector the equity belongs to
     * @param Index         the index the equity belongs to
     */
    public void addMarketEquity(String tickerSymbol, String name, float value, String sector, String Index) {

    }

    /**
     * Searches the marketEquities Array to return
     * a query of MarketEquities.
     * @param query
     * @param type
     * @param seachTarget
     * @return  query       the Array of matching MarketEquities based on the search params
     */
    public MarketEquity[] search(String query, String type, String seachTarget) {
        return marketEquities;
    }
}
