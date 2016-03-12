package Portfolio;

import Market.MarketEquity;

/**
 * Contains user equityholdings and cash accounts
 */
public class Portfolio {
    HoldingEquity[] holdingEquities;
    CashAccount[] cashAccounts;

    public void buyEquity(MarketEquity target, int numShares){
        //add target equity to HoldingEquity with numShares
    }

    public void sellEquity(MarketEquity target, int numShares){
        //remove target number of shares from Equity holding, **if shares goes to 0 delete object?
    }
}
