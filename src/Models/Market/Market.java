package Models.Market;

import Models.WebService.RequestYahooAPI;

import java.util.*;

/**
 * Holds all the information related to
 * the virtual Equity Market. Can search
 * and return a query of MarketEquities
 */
public class Market extends Observable {
    //list of all equities tracked by the market
    private ArrayList<MarketEquity> marketEquities;

    //list of all market averages in the market
    private ArrayList<MarketEquity> indexes;

    //maps the name of a market equity to its index in the marketEquities array list
    private HashMap<String, Integer> marketMap;

    /**
     * Constructor
     */
    public Market(){
        //initialize the equities list, indexes list, and hashmap
        marketEquities = new ArrayList<>();
        indexes = new ArrayList<>();
        marketMap = new HashMap<>();
    }

    //region GetterSetter
    /** Gives the index of an equity or -1 if it isn't in the list
     * @param name the name of the equity to look for
     * @return the index of the equity or -1 if it does not exist
     */
    public int getEquityExists(String name){return marketMap.containsKey(name) ? marketMap.get(name) : -1;}

    /** returns an iterator forr preforming a search on the market
     * @return a new instance of the private iterator class
     */
    public CustomIterator getIterator(){return new MarketIterator();}

    /** gives the list of market equities
     * @return the list of the market equities
     */
    public ArrayList<MarketEquity> getMarketEquities() {return marketEquities;}
    //endregion

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
        //create a list to store search results
        ArrayList<MarketEquity> searchResults = null;

        //preform the search and exit if the equity already exists
        if (!(searchResults = search(QueryType.TICKER, tickerSymbol, MatchType.EXACT)).isEmpty()){return;}

        //create the new equity
        MarketEquity newEquity = new Equity(tickerSymbol, name, value, sector, index);

        //add the new equity to the equities list
        marketEquities.add(newEquity);

        //add the index of the equity to the hashmap tied to its name
        marketMap.put(newEquity.getName(), marketEquities.indexOf(newEquity));

        //check to see if the index and sector have been added
        checkIndex(sector, newEquity);
        checkIndex(index, newEquity);
    }

    /** Adds a market average if it isn't already in the list
     * @param average the name of the average
     * @param newEquity the equity that belongs to the index
     */
    private void checkIndex(String average, MarketEquity newEquity){
        //don't attempt to add a null average
        if(average == null){ return;}

        //create the index
        MarketEquity newIndex = null;

        //check that the index doesn't already exist
        if(getEquityExists(average) == -1){
            //instantiate the average
            newIndex = new MarketAverage(average);

            //add the index to the equities list
            marketEquities.add(newIndex);

            //update the index of the average in the hashmap
            marketMap.put(average, marketEquities.indexOf(newIndex));

            //add the equity to the new average
            ((MarketAverage)newIndex).addChildren(newEquity);

            //add the index to the list of indexes
            indexes.add(newIndex);
        } else {
            //add the equity to the already created index
            ((MarketAverage)marketEquities.get(marketMap.get(average))).addChildren(newEquity);
        }

    }

    /** Preforms a search of the market based on the the field information
     * @param ticker the ticker value to search on
     * @param tickerMatch the type of match to use for ticker
     * @param name the name value to search on
     * @param nameMatch the type of match to use for name
     * @param index the index value to search on
     * @param indexMatch the type of match to use for index
     * @return the list of market equities that match all search parameters
     */
    private ArrayList<MarketEquity> search(String ticker, MatchType tickerMatch, String name, MatchType nameMatch, String index, MatchType indexMatch){
        //lists to hold the results of all individual searches
        ArrayList<MarketEquity> results1 = null;
        ArrayList<MarketEquity> results2 = null;
        ArrayList<MarketEquity> results3 = null;

        //run a search on ticker if a value is specified
        if(ticker != null)
            results1 = search(QueryType.TICKER, ticker, tickerMatch);

        //run a search on name if a value is specified
        if(name != null)
            results2 = search(QueryType.NAME, name, nameMatch);

        //run a search on index if a value is specified
        if(index != null)
            results3 = search(QueryType.INDEX_OR_SECTOR, index, indexMatch);

        //handle null results to prevent errors in condense the results
        if (results3 == null) results3 = results2;
        if (results2 == null) results2 = results1;
        if (results1 == null) results1 = results3;

        //return a condensed version of all three lists into one
        return condense(results1, results2, results3);
    }

    /** Condenses two lists into one where only elements in both are kept
     * @param search1 the first list
     * @param search2 the second list
     * @return the condensed list
     */
    private ArrayList<MarketEquity> condense(ArrayList<MarketEquity> search1, ArrayList<MarketEquity> search2){
        //do inner join on the two lists
        search1.retainAll(search2);
        return search1;
    }

    /** Condenses three lists into one where only elements in all three are kept
     * @param search1 the first list
     * @param search2 the second list
     * @param search3 the third list
     * @return the condensed list
     */
    private ArrayList<MarketEquity> condense(ArrayList<MarketEquity> search1, ArrayList<MarketEquity> search2, ArrayList<MarketEquity> search3){
        //use the previous condense function twice to condense the three lists
        return condense(condense(search1, search2), search3);
    }

    /**
     * Searches the marketEquities Array to return
     * a query of MarketEquities.
     * @param query the value being searched on
     * @param type the field type being searched on
     * @param matchType the type of match to preform
     * @return  query the Array of matching MarketEquities based on the search params
     */
    private ArrayList<MarketEquity> search(QueryType type, String query, MatchType matchType) {
        //will hold the comparator function to be used
        Comparator<String> compFunc;

        //will hold the results
        ArrayList<MarketEquity> results = new ArrayList<>();

        //call a method to selected the appropriate comparator function
        compFunc = pickCompFunc(matchType);

        //loop through all market equities
        for(MarketEquity equity : marketEquities){
            //call specific check functions if searching on index/sector because it is instance specific
            if (type == QueryType.INDEX_OR_SECTOR){
                //call and cast the specific functions
                if (equity instanceof Equity)
                    checkEq((Equity) equity, compFunc, query, results);
                else
                    checkEq((MarketAverage) equity, compFunc, query, results);
            } else {
                //call a general check function for all other search types
                checkEq(equity, compFunc, query, type, results);
            }
        }
        //return the results list
        return results;
    }

    /** returns a class chosen by the match type to be  preformed
     * @param matchType the matcch type
     * @return the associated comparator function
     */
    private Comparator<String> pickCompFunc(MatchType matchType){
        //switch on match type and return a new comparator instance
        switch (matchType){
            case CONTAINED:
                return new MatchContained();
            case EXACT:
                return new MatchExact();
            default:
                return new MatchBegins();
        }
    }

    //region CheckEquityMethods
    /** Checks a generic equity with a comparator function and adds it to a list
     * @param equity the equity to search against
     * @param comp comparator function
     * @param query query value
     * @param queryType the field type being searched on
     * @param results the list to add the equity to if it matches
     */
    private void checkEq(MarketEquity equity, Comparator<String> comp, String query, QueryType queryType, ArrayList<MarketEquity> results ){
        //switch on the search type
        switch (queryType){
            case TICKER:
                //add the equity if the ticker symbol matches the query
                if(comp.compare(equity.getTickerSymbol(), query) == 0)
                    results.add(equity);
                break;
            case NAME:
                //add the equity if the name matches the query
                if(comp.compare(equity.getName(), query) == 0)
                    results.add(equity);
                break;
        }
    }

    /** Checks a equity object with a comparator function and adds it to a list
     * @param equity the equity to search against
     * @param comp comparator function
     * @param query query value
     * @param results the list to add the equity to if it matches
     */
    private void checkEq(Equity equity, Comparator<String> comp, String query, ArrayList<MarketEquity> results){
        //add the equity if the index or sector matches the query
        if (equity.getSector() != null && comp.compare(equity.getSector(), query) == 0)
            results.add(equity);
        if (equity.getIndex() != null && comp.compare(equity.getIndex(), query) == 0)
            results.add(equity);
    }

    /** Checks a average object with a comparator function and adds it to a list
     * @param equity the equity to search against
     * @param comp the comparator function
     * @param query query value
     * @param results the list to add the equity to if it matches
     */
    private void checkEq(MarketAverage equity, Comparator<String> comp, String query, ArrayList<MarketEquity> results){
        //add the equity if the name matches the query
        if (comp.compare(equity.getName(), query) == 0)
            results.add(equity);
    }
    //endregion

    //region Comparators

    /**
     * Comparator class that checks for exact matches
     */
    private class MatchExact implements Comparator<String>{

        /** main compare function
         * @param subject the string to test against
         * @param query the query to test
         * @return 0 if a match or -1 if not a match
         */
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().equals(query.toLowerCase()) ? 0 : -1;
        }
    }

    /**
     * Comparator class that checks for containing matches
     */
    private class MatchContained implements Comparator<String>{

        /** main compare function
         * @param subject the string to test againstt
         * @param query the query to test
         * @return 0 if a match or -1 if not a match
         */
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().contains(query.toLowerCase()) ? 0 : -1;
        }
    }

    /**
     * Comparator class that checks for beginning matches
     */
    private class MatchBegins implements Comparator<String> {

        /** main compate function
         * @param subject the string to test against
         * @param query the query to test
         * @return 0 if a match or -1 if not a match
         */
        @Override public int compare(String subject, String query) {
            return subject.toLowerCase().startsWith(query.toLowerCase()) ? 0 : -1;
        }
    }
    //endregion

    /**
     * handles updating the prices of all equities
     */
    public void updateEquities(){
        //hash map of new values provided by yahoo
        HashMap<String, Float> newValues = new RequestYahooAPI(marketEquities).getUpdatedMarketEquities();

        //the visitor to send to each equity
        EquityUpdateVisitor updateVisitor = new EquityUpdateVisitor(newValues);

        //loop through each equity and send it the visitor to accept
        for (MarketEquity equity: marketEquities) {
            float o = equity.getValue();

            if(equity instanceof MarketAverage) ((MarketAverage)equity).accept(updateVisitor);
            else if(equity instanceof Equity) ((Equity)equity).accept(updateVisitor);
        }

        //notify observers of change
        setChanged();
        notifyObservers();
    }

    /**
     * Private iterator to traverse market searches
     */
    private class MarketIterator implements CustomIterator{

        //region Fields
        //the current position in the search results
        private int resultsPosition;

        //the parameters to search on
        private boolean paramsSet;

        //the number of matches for the search
        private int resultLength;

        //the list of matches
        private ArrayList<MarketEquity> results;
        //endregion

        /**
         * Constructor
         */
        public MarketIterator(){
            resultsPosition  = 0;
        }

        /** Sets the search parameters for this iterator
         * @param ticker      the value of the ticker symbol field
         * @param tickerMatch the type of match to preform on the ticker value
         * @param name        the value of the name field
         * @param nameMatch   the type of match to preform on the name value
         * @param index       the value of the index field
         * @param indexMatch  the type of match to preform on the index value
         * @return reference to this object
         */
        @Override
        public MarketIterator setSearchParams(String ticker, MatchType tickerMatch, String name, MatchType nameMatch, String index, MatchType indexMatch){
            results = search(ticker, tickerMatch, name, nameMatch, index, indexMatch);
            resultLength = results.size();
            paramsSet = true;
            return this;
        }


        /** Tells if there is a next result
         * @return whether or not a search result remains
         */
        @Override
        public boolean hasNext() {
            return resultsPosition < resultLength;
        }

        /** Tells if there are the specified number of elements remaining
         * @param length the number of elems to check for
         * @return wheter or not the number of search results remain
         */
        @Override
        public boolean hasNext(int length){
            return resultsPosition + length < resultLength;
        }

        /** givves the next result from were the user left off
         * @return the next result
         */
        @Override
        public MarketEquity next() {
            //the leme to return
            MarketEquity nextElem = null;

            //mmake sure the parameters have been set
            if(paramsSet){
                //get the next elem and increase the current position
                nextElem = results.get(resultsPosition);
                resultsPosition++;
            }
            return nextElem;
        }

        /** returns the next x number of elements
         * @param length the number of element to return
         * @return the list of x number of results
         */
        @Override
        public ArrayList<MarketEquity> next(int length){
            //the list of elements to return
            ArrayList<MarketEquity> nextElems = null;

            //make sure the parameters have been set
            if(paramsSet) {
                //get the next elems and increase the current position by that number
                nextElems = new ArrayList<MarketEquity>(results.subList(resultsPosition, resultsPosition + length));
                resultsPosition += length;
            }
            return nextElems;
        }

        /** returns all remaining elements
         * @return the results from the current position to the end
         */
        @Override
        public ArrayList<MarketEquity> toEnd(){
            //the list of results to return
            ArrayList<MarketEquity> nextElems = null;

            //check that the parameters have been set
            if(paramsSet){
                //get the next elems and set the current position to the end
                nextElems = new ArrayList<MarketEquity>(results.subList(resultsPosition, resultLength));
                resultsPosition = resultLength;
            }
            return nextElems;
        }

    }
}
