package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * A concrete command. Removes a holdingEquity
 * from a portfolio and *optional deposit money
 * from a holding account.
 */

public class SellTransaction implements Transaction, Serializable {

    private HoldingEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private CashAccount cashAccount;
    private String description;
    private boolean failed;

    public SellTransaction(HoldingEquity target, int amount, Portfolio portfolio, CashAccount cashAccount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.cashAccount = cashAccount;
        this.failed = false;
    }

    @Override
    public void execute() {
        if(target.getNumShares() - amount >=0) {
            portfolio.sellEquity(target, amount);
            cashAccount.deposit(amount);
            target.getValue();
        }
        else failed = true;
    }

    @Override
    public String toString() {
        if(failed) return "Could not sell "+amount+" shares of "+target.getName()+" equity on "+date;
        return "Sold "+amount+" shares of "+target.getName()+" equity on "+date;
    }
}
