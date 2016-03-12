package Transaction;

import Market.MarketEquity;
import Portfolio.Portfolio;

import java.time.LocalDateTime;

/**
 * A concrete command. Removes a holdingEquity
 * from a portfolio and *optional deposite money
 * from a holding account.
 */
public class SellTransaction implements Transaction{

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;

    public SellTransaction(MarketEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        portfolio.sellEquity(target, amount);
    }

    @Override
    public String toString() {
        return "Equity Sold:\t" + target + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
