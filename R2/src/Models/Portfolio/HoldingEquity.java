package Models.Portfolio;

import Models.Market.EquityVisitor;
import Models.Market.MarketEquity;

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
     * Contructor
     * @param numShares the number of shares in this holding
     * @param pricePerShare the price per share in this holding
     * @param name the name of the holding
     * @param ticker the ticker symbol of the holding
     * @param marketAssociation reference to the equity this holds
     */
    public HoldingEquity(int numShares, float pricePerShare, String name, String ticker, MarketEquity marketAssociation) {
        super(name, pricePerShare * numShares, ticker);
        this.numShares = numShares;
        this.datePurchased = LocalDateTime.now();
        this.pricePerShare = pricePerShare;
        this.marketAssociation = marketAssociation;
    }

    //region GettersSetters
    /** Gets the name of this holding
     * @return the name of the holding
     */
    public String getName() {
        return name;
    }

    /** Gets the ticker of this holding
     * @return the ticker symbol of the holding
     */
    public String getTickerSymbol(){return tickerSymbol;}

    /** Gets the number of shares in this holding
     * @return the number of shares bought
     */
    public int getNumShares() {
        return numShares;
    }

    /** Gets the date this holding waas purchased
     * @return the date purchased
     */
    public LocalDateTime getDatePurchased() {
        return datePurchased;
    }

    /** Gets the per share price of this holding
     * @return the current price of the shares
     */
    public float getPricePerShare() {
        this.pricePerShare = marketAssociation.getValue();
        return pricePerShare;
    }

    /** Gets the total value of this holding
     * @return the current value of the holding
     */
    @Override
    public float getValue() {
        value = numShares * marketAssociation.getValue();
        return value;
    }

    /** Sets the number of shares in this holding
     * @param numShares the number of shares in this holding
     */
    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    /** Sets the price of the individual shares in this holding
     * @param pricePerShare the price for each share in this holding
     */
    public void setPricePerShare(float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }
    //endregion

    @Override
    public void accept(EquityVisitor visitor){
        visitor.visit(this);
    }
}
