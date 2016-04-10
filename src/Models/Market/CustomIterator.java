package Models.Market;

import java.util.ArrayList;

/**
 * Interface requiring method necessary for iterating through a market search
 */
public interface CustomIterator {

    /** Sets the fields necessary for preforming a complete search on the market
     * @param ticker the value of the ticker symbol field
     * @param tickerMatch the type of match to preform on the ticker value
     * @param name the value of the name field
     * @param nameMatch the type of match to preform on the name value
     * @param index the value of the index field
     * @param indexMatch the type of match to preform on the index value
     * @return
     */
    CustomIterator setSearchParams(String ticker, MatchType tickerMatch, String name, MatchType nameMatch, String index, MatchType indexMatch);

    /** returns whether or not there is a following search item
     * @return bool for if another search item remains
     */
    boolean hasNext();

    /** returns whether or not there are a specific number of items remaining
     * @param length the number of elems to check for
     * @return bool for if the elements remain
     */
    boolean hasNext(int length);

    /**
     * @return
     */
    MarketEquity next();

    /**
     * @param length
     * @return
     */
    ArrayList<MarketEquity> next(int length);

    /**
     * @return
     */
    ArrayList<MarketEquity> toEnd();
}
