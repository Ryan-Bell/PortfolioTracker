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
     * @param tickerSymbol  The equity's name symbol
     * @param name          The equity's name
     * @param value         The equity's value
     * @param sector        The sector the equity belongs to
     * @param Index         The index the equity belongs to
     */
    public void addMarketEquity(String tickerSymbol, String name, float value, String sector, String Index) {

    }

    /**
     * Searches the marketEquities Array to return
     * a query of MarketEquities.
     * @param query
     * @param type
     * @param seachTarget
     * @return  query       The Array of matching MarketEquities based on the search params
     */
    public MarketEquity[] search(String query, String type, String seachTarget) {
        return marketEquities;
    }
}
