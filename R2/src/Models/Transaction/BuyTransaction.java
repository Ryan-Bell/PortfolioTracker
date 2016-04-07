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

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private boolean failed;

    public int getAmount(){return amount;}
    public MarketEquity getTarget(){return target;}

    public BuyTransaction(MarketEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.failed = false;
    }

    @Override
    public void execute() {
        portfolio.buyEquity(target, amount);
    }

    @Override
    public String toString() {
        if(failed) return "Could not purchase "+amount+" shares of "+target.getName()+"on "+date;
        return "Purchased "+amount+" shares of "+target.getName()+"on "+date;
    }

    @Override
    public void unExecute() {
        if(failed) return;
        portfolio.sellEquity((HoldingEquity)target, amount);
    }
}
