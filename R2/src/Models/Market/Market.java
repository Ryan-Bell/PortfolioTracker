package Models.Market;

import Models.WebService.RequestYahooAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Observable;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market extends Observable {
    private ArrayList<MarketEquity> marketEquities;
    private ArrayList<MarketEquity> indexes;
    private HashMap<String, Integer> marketMap;

    public Market(){
        marketEquities = new ArrayList<>();
        indexes = new ArrayList<>();
        marketMap = new HashMap<>();
    }

    public int getEquityExists(String name){return marketMap.containsKey(name) ? marketMap.get(name) : -1;}
    public ArrayList<MarketEquity> getMarketEquities() {return marketEquities;}

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
        ArrayList<MarketEquity> searchResults = null;
        if (!(searchResults = search(QueryType.TICKER, tickerSymbol, MatchType.EXACT)).isEmpty()){return;}
        MarketEquity newEquity = new Equity(tickerSymbol, name, value, sector, index);
        marketEquities.add(newEquity);
        marketMap.put(newEquity.getName(), marketEquities.indexOf(newEquity));
        checkIndex(sector, newEquity);
        checkIndex(index, newEquity);
    }

    private void checkIndex(String average, MarketEquity newEquity){
        if(average == null){ return;}

        //create the index
        MarketEquity newIndex = null;

        if(getEquityExists(average) == -1){
            newIndex = new MarketAverage(average);
            marketEquities.add(newIndex);
            marketMap.put(average, marketEquities.indexOf(newIndex));
            ((MarketAverage)newIndex).addChildren(newEquity);
            indexes.add(newIndex);
        } else {
            ((MarketAverage)marketEquities.get(marketMap.get(average))).addChildren(newEquity);
        }

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
        Comparator<String> compFunc;
        ArrayList<MarketEquity> results = new ArrayList<>();
        for(MarketEquity equity : marketEquities){

            compFunc = pickCompFunc(matchType);

            if (type == QueryType.INDEX_OR_SECTOR){
                if (equity instanceof Equity)
                    checkEq((Equity) equity, compFunc, query, results);
                else
                    checkEq((MarketAverage) equity, compFunc, query, results);
                break;
            } else {
                checkEq(equity, compFunc, query, type, results);
            }
        }
        return results;
    }

    private Comparator<String> pickCompFunc(MatchType matchType){
        switch (matchType){
            case CONTAINED:
                return new MatchContained();
            case EXACT:
                return new MatchExact();
            default:
                return new MatchBegins();
        }
    }

    private void checkEq(MarketEquity equity, Comparator<String> comp, String query, QueryType queryType, ArrayList<MarketEquity> results ){
        switch (queryType){
            case TICKER:
                if (!(equity instanceof Equity)){ return;}
                if(comp.compare(equity.getTickerSymbol(), query) == 0)
                    results.add(equity);
                break;
            case NAME:
                if(comp.compare(equity.getName(), query) == 0)
                    results.add(equity);
                break;
        }
    }

    private void checkEq(Equity equity, Comparator<String> comp, String query, ArrayList<MarketEquity> results){
        if (comp.compare(equity.getSector(), query) == 0)
            results.add(equity);
    }
    private void checkEq(MarketAverage equity, Comparator<String> comp, String query, ArrayList<MarketEquity> results){
        if (comp.compare(equity.getName(), query) == 0)
            results.add(equity);
    }

    private class MatchExact implements Comparator<String>{
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().equals(query.toLowerCase()) ? 0 : -1;
        }
    }

    private class MatchContained implements Comparator<String>{
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().contains(query.toLowerCase()) ? 0 : -1;
        }
    }
    private class MatchBegins implements Comparator<String> {
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().startsWith(query.toLowerCase()) ? 0 : -1;
        }
    }

    public void updateEquities(){

        HashMap<String, Float> newValues = new RequestYahooAPI(marketEquities).getUpdatedMarketEquities();
        EquityUpdateVisitor updateVisitor = new EquityUpdateVisitor(newValues);

        for (MarketEquity equity: marketEquities) {
            float o = equity.getValue();

            if(equity instanceof MarketAverage) ((MarketAverage)equity).accept(updateVisitor);
            else if(equity instanceof Equity) ((Equity)equity).accept(updateVisitor);
        }

        setChanged();
        notifyObservers();
    }
}
