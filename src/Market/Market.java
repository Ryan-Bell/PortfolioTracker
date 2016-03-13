package Market;

import java.util.ArrayList;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market {
    private ArrayList<MarketEquity> marketEquities;
    private ArrayList<String> indexNames;
    private ArrayList<MarketEquity> indexes;

    public Market(){
        marketEquities = new ArrayList<>();
        indexNames = new ArrayList<>();
        indexes = new ArrayList<>();
    }

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


        //create the index
        MarketEquity newIndex = null;
        MarketEquity newEquity = null;

        ArrayList<MarketEquity> searchResults = null;

        if ((searchResults = search(QueryType.TICKER, tickerSymbol, MatchType.EXACT)).isEmpty()){
            newEquity = new Equity(tickerSymbol, name, value, sector, index);
            marketEquities.add(newEquity);
            if(sector != null){
                if (!indexNames.contains(sector)){
                    newIndex = new Index(sector);
                    marketEquities.add(newIndex);
                    ((Index) newIndex).addChildren(newEquity);
                    indexNames.add(sector);
                    indexes.add(newIndex);
                } else {
                    ((Index)indexes.get(indexNames.indexOf(sector))).addChildren(newEquity);
                }
            }
            if(index != null){
                if (!indexNames.contains(index)){
                    newIndex = new Index(index);
                    marketEquities.add(newIndex);
                    ((Index) newIndex).addChildren(newEquity);
                    indexNames.add(index);
                    indexes.add(newIndex);
                } else {
                    ((Index)indexes.get(indexNames.indexOf(index))).addChildren(newEquity);
                }
            }
        }
    }

    @Override
    public String toString(){
        for (MarketEquity e: marketEquities) {
            System.out.println(e);
        }
        return "Done";
    }


    /**
     * Searches the marketEquities Array to return
     * a query of MarketEquities.
     * @param query
     * @param type
     * @param matchType
     * @return  query the Array of matching MarketEquities based on the search params
     */
    public ArrayList<MarketEquity> search(QueryType type, String query, MatchType matchType) {
        ArrayList<MarketEquity> results = new ArrayList<>();
        switch (type) {
            case TICKER:
                for (MarketEquity equity : marketEquities) {
                    if (equity instanceof Equity) {
                        switch (matchType) {
                            case EXACT:
                                if (((Equity) equity).getTickerSymbol().toLowerCase().equals(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (((Equity) equity).getTickerSymbol().toLowerCase().startsWith(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (((Equity) equity).getTickerSymbol().toLowerCase().contains(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                        }

                    }
                }
                break;
            case NAME:
                for (MarketEquity equity : marketEquities) {
                    switch (matchType) {
                        case EXACT:
                            if (equity.name.toLowerCase().equals(query.toLowerCase())) {
                                results.add(equity);
                            }
                            break;
                        case BEGIN:
                            if (equity.name.toLowerCase().startsWith(query.toLowerCase())) {
                                results.add(equity);
                            }
                            break;
                        case CONTAINED:
                            if (equity.name.toLowerCase().contains(query.toLowerCase())) {
                                results.add(equity);
                            }
                            break;
                    }
                }
                break;
            case INDEX_OR_SECTOR:
                for (MarketEquity equity : marketEquities) {
                    if (equity instanceof Equity) {
                        switch (matchType) {
                            case EXACT:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().toLowerCase().equals(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().toLowerCase().startsWith(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().toLowerCase().contains(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                        }
                    }
                    if (equity instanceof Index) {
                        switch (matchType) {
                            case EXACT:
                                if (equity.name.toLowerCase().equals(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (equity.name.toLowerCase().startsWith(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (equity.name.toLowerCase().contains(query.toLowerCase())) {
                                    results.add(equity);
                                }
                                break;
                        }
                    }
                }
                break;
        }
        //System.out.println(results);
        return results;
    }
}
