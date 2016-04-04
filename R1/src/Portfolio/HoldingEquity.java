package Portfolio;

import Market.EquityVisitor;
import Market.MarketEquity;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a MarketEquity that has been purchased by the user
 */
public class HoldingEquity extends MarketEquity implements Serializable{
    private int numShares;
    private LocalDateTime datePurchased;
    private float pricePerShare;
    private MarketEquity marketAssociation;

    /**
     * Constructor.
     * @param numShares         the number of shares
     * @param pricePerShare     the value of each share
     */
    public HoldingEquity(int numShares, float pricePerShare, String name, String ticker, MarketEquity marketAssociation) {
        super(name, pricePerShare * numShares, ticker);
        this.numShares = numShares;
        this.datePurchased = LocalDateTime.now();
        this.pricePerShare = pricePerShare;
        this.marketAssociation = marketAssociation;
    }

    public String getName() {
        return name;
    }

    public int getNumShares() {
        return numShares;
    }

    public LocalDateTime getDatePurchased() {
        return datePurchased;
    }

    public float getPricePerShare() {
        this.pricePerShare = marketAssociation.getValue();
        return pricePerShare;
    }

    // calculates the current value of the equity
    @Override
    public float getValue() {
        value = numShares * marketAssociation.getValue();
        return value;
    }

    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public void setPricePerShare(float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public String getTickerSymbol(){return tickerSymbol;}

    @Override
    public void accept(EquityVisitor visitor){
        visitor.visit(this);
    }
}
