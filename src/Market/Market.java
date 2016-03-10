package Market;

import java.util.ArrayList;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market {
    private ArrayList<MarketEquity> marketEquities;

    /**
     * Creates and adds a MarketEquity to the
     * marketEquities Array.
     * @param tickerSymbol  the equity's name symbol
     * @param name          the equity's name
     * @param value         the equity's value
     * @param sector        the sector the equity belongs to
     * @param index         the index the equity belongs to
     */
    public void addMarketEquity(String tickerSymbol, String name, float value, String sector, String index) {
        //create the equity
        MarketEquity newEquity = new Equity(tickerSymbol, name, value, sector, index);

        //add to the marketEquities array if it isn't already in there
        if(!marketEquities.contains(newEquity)){
            marketEquities.add(newEquity);
        }
    }

    /**
     * Provides a method for adding a MarketEquity to the
     * marketEquities Array when adding a sector / index
     * @param name the name of the index
     */
    public void addMarketEquity(String name){
        //create the index
        MarketEquity newEquity = new Index(name);

        //add to the marketEquities array iif it isn't already in there
        if(!marketEquities.contains(newEquity)){
            marketEquities.add(newEquity);
        }
    }

    /**
     * Searches the marketEquities Array to return
     * a query of MarketEquities.
     * @param query
     * @param type
     * @param matchType
     * @return  query       the Array of matching MarketEquities based on the search params
     */
    public ArrayList<MarketEquity> search(QueryType type, String query, MatchType matchType) {
        ArrayList<MarketEquity> results = new ArrayList<>();
        switch (type){
            case TICKER:
                for(MarketEquity equity : marketEquities){
                    if (equity instanceof Equity){
                        switch (matchType) {
                            case EXACT:
                                if(((Equity)equity).getTickerSymbol().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if(((Equity)equity).getTickerSymbol().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if(((Equity)equity).getTickerSymbol().equals(query)){
                                    results.add(equity);
                                }
                                break;
                        }

                    }
                }
                break;
            case NAME:
                for(MarketEquity equity : marketEquities){
                    switch (matchType){
                        case EXACT:
                            if(equity.name.equals(query)){
                                results.add(equity);
                            }
                            break;
                        case BEGIN:
                            if(equity.name.startsWith(query)){
                                results.add(equity);
                            }
                        case CONTAINED:
                            if(equity.name.contains(query)) {
                                results.add(equity);
                            }
                    }
                }
                break;
            case SECTOR:
                for(MarketEquity equity : marketEquities){
                    if (equity instanceof Equity){
                        switch (matchType) {
                            case EXACT:
                                if(((Equity)equity).getSector().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if(((Equity)equity).getSector().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if(((Equity)equity).getSector().equals(query)){
                                    results.add(equity);
                                }
                                break;
                        }

                    }
                }
                break;
            case INDEX:
                for(MarketEquity equity : marketEquities){
                    if (equity instanceof Equity){
                        switch (matchType) {
                            case EXACT:
                                if(((Equity)equity).getIndex().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if(((Equity)equity).getIndex().equals(query)){
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if(((Equity)equity).getIndex().equals(query)){
                                    results.add(equity);
                                }
                                break;
                        }

                    }
                }
                break;
            default:
                break;
        }
        return results;
    }
}
