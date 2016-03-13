package Transaction;

import Market.MarketEquity;
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

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private CashAccount cashAccount;
    private String description;

    public SellTransaction(MarketEquity target, int amount, Portfolio portfolio, CashAccount cashAccount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.cashAccount = cashAccount;
    }

    @Override
    public void execute() {
        portfolio.sellEquity(target, amount);
        cashAccount.deposit(amount);
    }

    @Override
    public String toString() {
        return "Equity Sold:\t" + target + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
