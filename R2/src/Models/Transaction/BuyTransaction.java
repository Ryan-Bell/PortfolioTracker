package Models.Transaction;

import Models.Market.MarketEquity;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Adds a holdingEquity
 * to a portfolio
 */
public class BuyTransaction implements UndoRedo, Serializable {

    //region Fields
    //The equity to be purchased
    private MarketEquity target;

    //The number off shares to be purchased
    private int amount;

    //The time this command was created
    private LocalDateTime date;

    //The portfolio that this equity will be added to
    private Portfolio portfolio;

    //The failure status of this command
    private boolean failed;
    //endregion

    //region GetterSetters
    /** Getter for the amount of shares
     * @return the amount of shares to be purchased
     */
    public int getAmount(){return amount;}


    /** Getter for the Market Equity
     * @return the MarketEquity to be purchased
     */
    public MarketEquity getTarget(){return target;}
    //endregion

    /** Contructor
     * @param target The MarketEquity to be purchased
     * @param amount the number of shares that are going to be bought
     * @param portfolio the portfolio that will hold the purchased equities
     */
    public BuyTransaction(MarketEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.failed = false;
    }

    /**
     * Adds the correct number of the equity to the portfolio
     */
    @Override
    public void execute() {
        portfolio.buyEquity(target, amount);
    }

    /** Provides a string representation of this command dependant on the failure status
     * @return The string representing this command
     */
    @Override
    public String toString() {
        if(failed) return "Could not purchase "+amount+" shares of "+target.getName()+"on "+date;
        return "Purchased "+amount+" shares of "+target.getName()+"on "+date;
    }

    /**
     * Reverts the action of the execute command
     */
    @Override
    public void unExecute() {
        //Don't do anything if the execute failed
        if(failed) return;

        //remove the equity from the portfolio that was added by the execute method
        portfolio.sellEquity((HoldingEquity)target, amount);
    }
}
