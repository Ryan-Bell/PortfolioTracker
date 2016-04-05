package Transaction;

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
    private boolean failed;


    public BuyTransaction(MarketEquity target, int amount, Portfolio portfolio, CashAccount cashAccount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.cashAccount = cashAccount;
        this.failed = false;
    }

    @Override
    public void execute() {
        if(cashAccount != null) {
            float cost = amount * target.getValue();
            if (cashAccount.sufficientFunds(cost)) {
                cashAccount.withdraw(cost);

                //call buyEquity on target with num of shares
                portfolio.buyEquity(target, amount);
            } else failed = true;
        }
        else{
            portfolio.buyEquity(target, amount);
        }
    }

    @Override
    public String toString() {
        if(failed) return "Could not purchase "+amount+" shares of "+target.getName()+"on "+date;
        return "Purchased "+amount+" shares of "+target.getName()+"on "+date;
    }
}
