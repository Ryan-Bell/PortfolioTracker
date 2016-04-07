package Models.Transaction;

import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * A concrete command. Removes a holdingEquity
 * from a portfolio
 */

public class SellTransaction implements UndoRedo, Serializable {

    //region Fields
    //The equity to sell
    private HoldingEquity target;

    //the number of shares to sell
    private int amount;

    //The time this command was created
    private LocalDateTime date;

    //the portfolio to sell equities from
    private Portfolio portfolio;

    //the failure status of this command
    private boolean failed;
    //endregion

    //region GetterSetters
    /** Getter for the HoldingEquity
     * @return the holding equity that is being sold
     */
    public HoldingEquity getTarget(){return target;}

    /** Getter for the amount
     * @return the number of shares being sold
     */
    public int getAmount(){return amount;}

    /** Getter for the Portfolio
     * @return the portfolio being sold from
     */
    public Portfolio getPortfolio(){return portfolio;}
    //endregion


    /** Constructor
     * @param target the holding equity being sold
     * @param amount the number off shares being sold
     * @param portfolio the portfolio being sold from
     */
    public SellTransaction(HoldingEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.failed = false;
    }

    /**
     * Sells the number of shares of the equity from portfolio if there are enough shares bought
     */
    @Override
    public void execute() {
        //check that the number of shares being sold doesn't exceed the number bought
        if(target.getNumShares() - amount >=0) {
            portfolio.sellEquity(target, amount);
        }

        //update failure status
        else failed = true;
    }

    /** Provides a string representation of this command based on the failure status
     * @return the string representation
     */
    @Override
    public String toString() {
        if(failed) return "Could not sell "+amount+" shares of "+target.getName()+" equity on "+date;
        return "Sold "+amount+" shares of "+target.getName()+" equity on "+date;
    }

    /**
     * Reverts the action of the execute method
     */
    @Override
    public void unExecute() {
        //Don't do anything if the execute failed
        if(failed) return;

        //buy the number of shares of the equity that were sold in execute
        portfolio.buyEquity(target, amount);
    }
}
