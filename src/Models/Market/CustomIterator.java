package Models.Market;

import java.util.ArrayList;

public interface CustomIterator {
    CustomIterator setSearchParams(String ticker, MatchType tickerMatch, String name, MatchType nameMatch, String index, MatchType indexMatch);
    boolean hasNext();
    boolean hasNext(int length);
    MarketEquity next();
    ArrayList<MarketEquity> next(int length);
    ArrayList<MarketEquity> toEnd();
}
