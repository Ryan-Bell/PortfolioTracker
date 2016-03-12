package Portfolio;

import Market.MarketEquity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represents a MarketEquity that has been purchased by the user
 */
public class HoldingEquity extends MarketEquity implements Serializable{
    private int numShares;
    private LocalDateTime datePurchased;
    private float pricePerShare;

    /**
     * Constructor.
     * @param numShares         the number of shares
     * @param pricePerShare     the value of each share
     */
    public HoldingEquity(int numShares, float pricePerShare) {
        this.numShares = numShares;
        this.datePurchased = LocalDateTime.now();
        this.pricePerShare = pricePerShare;
    }

    public String getName() {
        return name;
    }

    public float getNumShares() {
        return numShares;
    }

    public LocalDateTime getDatePurchased() {
        return datePurchased;
    }

    public float getPricePerShare() {
        return pricePerShare;
    }

    // calculates the current value of the equity
    public float getEquityValue() {
        return numShares * pricePerShare;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void setPricePerShare(float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
}
