package Market;

/**
 * Created by Brian on 3/8/2016.
 */
public class Index extends MarketEquity {
    private MarketEquity[] children;

    public void addChildren(MarketEquity child) {

    }

    public MarketEquity[] getChildren() {
        return children;
    }

    public float getValue() {
        return 0;
    }
}
