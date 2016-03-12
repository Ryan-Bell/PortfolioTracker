package Transaction;

import Market.Equity;
import Market.MarketEquity;
import Portfolio.Portfolio;

import java.time.LocalDateTime;

/**
 * Created by Meg on 3/11/2016.
 */
public class Buy implements Transaction {

    private MarketEquity target;
    private int amount;
    private LocalDateTime date;
    private Portfolio portfolio;


    public Buy(MarketEquity target, int amount, Portfolio portfolio) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;

    }

    @Override
    public void execute() {
        //call buyEquity on target with num of shares
        portfolio.buyEquity(target,amount);
    }

    @Override
    public String toString() {
        return "Equity Purchased:\t" + target + "\tShares:\t" + amount + "\tDate:\t" + date;
    }
}
