package Transaction;

import Market.Equity;
import Market.MarketEquity;
import Portfolio.Portfolio;
import Portfolio.CashAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Adds a holdingEquity
 * to a portfolio and *optional withdraw money
 * from a holding account.
 */

public class BuyTransaction implements Transaction, Serializable {

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private CashAccount cashAccount;


    public BuyTransaction(MarketEquity target, int amount, Portfolio portfolio, CashAccount cashAccount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.cashAccount = cashAccount;
    }

    @Override
    public void execute() {
        if (cashAccount.sufficientFunds(amount)){
            cashAccount.withdraw(amount);

            //call buyEquity on target with num of shares
            portfolio.buyEquity(target, amount);
        }
    }

    @Override
    public String toString() {
        return "Equity Purchased:\t" + target.getName() + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
