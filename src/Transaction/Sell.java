package Transaction;

import Market.MarketEquity;
import Portfolio.Portfolio;

import java.time.LocalDateTime;

/**
 * Command allowing user to remove EquityHolding from their portfolio
 */
public class Sell implements Transaction{

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;

    public Sell(MarketEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        portfolio.sellEquity(target,amount);

    }

    @Override
    public String toString() {
        return "Equity Sold:\t" + target + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
