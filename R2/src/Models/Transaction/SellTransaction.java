package Models.Transaction;

import Models.Portfolio.CashAccount;
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

    private HoldingEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private boolean failed;

    public HoldingEquity getTarget(){return target;}
    public int getAmount(){return amount;}
    public Portfolio getPortfolio(){return portfolio;}

    public SellTransaction(HoldingEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.failed = false;
    }

    @Override
    public void execute() {
        if(target.getNumShares() - amount >=0) {
            portfolio.sellEquity(target, amount);
        }
        else failed = true;
    }

    @Override
    public String toString() {
        if(failed) return "Could not sell "+amount+" shares of "+target.getName()+" equity on "+date;
        return "Sold "+amount+" shares of "+target.getName()+" equity on "+date;
    }

    @Override
    public void unExecute() {

    }
}
