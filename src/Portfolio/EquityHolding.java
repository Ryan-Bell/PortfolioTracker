package Portfolio;

import Market.MarketEquity;
import java.util.Date;

/**
 * Represents a MarketEquity that has been purchased by the user
 */
public class EquityHolding extends MarketEquity{
    private String name;
    private int numShares;
    private Date datePurchased;
    private float pricePerShare;



    /**
     * Constructor.
     * @param name              the equity's name
     * @param numShares         the number of shares
     * @param datePurchased     the date purchased
     * @param pricePerShare     the value of each share
     */
    public EquityHolding(String name,int numShares,Date datePurchased, float pricePerShare) {
        this.name = name;
        this.numShares = numShares;
        this.datePurchased = datePurchased;
        this.pricePerShare = pricePerShare;
    }



    public String getName() {
        return name;
    }

    public float getNumShares() {
        return numShares;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public float getPricePerShare() {
        return pricePerShare;
    }

    // calculates the current value of the equity
    public float getEquityValue() {
        return numShares*pricePerShare;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }
    public void setPricePerShare(float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
}
