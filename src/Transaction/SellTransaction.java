package Transaction;

import Market.MarketEquity;
import Portfolio.HoldingEquity;
import Portfolio.Portfolio;

import java.io.Serializable;
import java.time.LocalDateTime;
import Portfolio.CashAccount;

/**
 * A concrete command. Removes a holdingEquity
 * from a portfolio and *optional deposite money
 * from a holding account.
 */

public class SellTransaction implements Transaction, Serializable {

    private HoldingEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private CashAccount cashAccount;
    private String description;

    public SellTransaction(HoldingEquity target, int amount, Portfolio portfolio, CashAccount cashAccount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.cashAccount = cashAccount;
    }

    @Override
    public void execute() {
        if(target.getNumShares() - amount >=0) {
            portfolio.sellEquity(target, amount);
            cashAccount.deposit(amount);
            target.getValue();
        }
    }

    @Override
    public String toString() {
        return "Equity Sold:\t" + target.getName() + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
