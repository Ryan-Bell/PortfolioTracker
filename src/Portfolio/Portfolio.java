package Portfolio;

import Market.MarketEquity;
import Transaction.Transaction;

import java.util.ArrayList;

/**
 * Contains user equityholdings and cash accounts.
 */
public class Portfolio {
    ArrayList<HoldingEquity> holdingEquities;
    ArrayList<CashAccount> cashAccounts;
    ArrayList<Transaction> transactionLog;

    public void buyEquity(MarketEquity target, int numShares){
        //add target equity to HoldingEquity with numShares
        HoldingEquity newEquity = new HoldingEquity(numShares,target.getValue());
        holdingEquities.add(newEquity);
    }

    public void sellEquity(MarketEquity target, int numShares){
        //remove target number of shares from Equity holding, **if shares goes to 0 delete object?
        int index = holdingEquities.indexOf(target);
        holdingEquities.remove(index);
    }

    public void addCashAccount(String name, float initialBalance){
        //add target cash account with initial balance
        CashAccount newCashAccount = new CashAccount(name,initialBalance);
        cashAccounts.add(newCashAccount);
    }

    public void removeCashAccount(CashAccount target){
        //remove target cash account
        int index = cashAccounts.indexOf(target);
        cashAccounts.remove(index);
    }
}
