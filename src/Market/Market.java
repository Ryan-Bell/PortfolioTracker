package Market;

import java.util.ArrayList;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market {
    private ArrayList<MarketEquity> marketEquities;

    public Market(){
        marketEquities = new ArrayList<>();
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
        MarketEquity newSector = null;
        MarketEquity newEquity = null;

        ArrayList<MarketEquity> searchResults = null;

        if ((searchResults = search(QueryType.TICKER, tickerSymbol, MatchType.EXACT)).isEmpty()){
            newEquity = new Equity(tickerSymbol, name, value, sector, index);
            marketEquities.add(newEquity);
            if(sector != null && (searchResults = search(QueryType.INDEX_OR_SECTOR, sector, MatchType.EXACT)).isEmpty()){
                newSector = new Index(sector);
                marketEquities.add(newSector);
                ((Index)newSector).addChildren(newEquity);
            }
            if(index != null && (searchResults = search(QueryType.INDEX_OR_SECTOR, index, MatchType.EXACT)).isEmpty()){
                newIndex = new Index(index);
                marketEquities.add(newIndex);
                ((Index)newIndex).addChildren(newEquity);
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
                    //System.out.println(equity.getName());
                    if (equity instanceof Equity) {
                        switch (matchType) {
                            case EXACT:
                                if (((Equity) equity).getTickerSymbol().equals(query)) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (((Equity) equity).getTickerSymbol().startsWith(query)) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (((Equity) equity).getTickerSymbol().contains(query)) {
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
                            if (equity.name.equals(query)) {
                                results.add(equity);
                            }
                            break;
                        case BEGIN:
                            if (equity.name.startsWith(query)) {
                                results.add(equity);
                            }
                        case CONTAINED:
                            if (equity.name.contains(query)) {
                                results.add(equity);
                            }
                    }
                }
                break;
            case INDEX_OR_SECTOR:
                for (MarketEquity equity : marketEquities) {
                    if (equity instanceof Equity) {
                        switch (matchType) {
                            case EXACT:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().equals(query)) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().startsWith(query)) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (((Equity) equity).getSector() != null && ((Equity) equity).getSector().contains(query)) {
                                    results.add(equity);
                                }
                                break;
                        }
                    }
                    if (equity instanceof Index) {
                        switch (matchType) {
                            case EXACT:
                                if (equity.name.equals(query)) {
                                    results.add(equity);
                                }
                                break;
                            case BEGIN:
                                if (equity.name.startsWith(query)) {
                                    results.add(equity);
                                }
                                break;
                            case CONTAINED:
                                if (equity.name.contains(query)) {
                                    results.add(equity);
                                }
                                break;
                        }
                    }
                }
                break;
        }
        return results;
    }
}
