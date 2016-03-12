package Portfolio;

import Market.MarketEquity;
import Transaction.Transaction;

import java.util.ArrayList;

/**
 * Contains user equityholdings and cash accounts
 */
public class Portfolio {
    ArrayList<HoldingEquity> holdingEquities;
    ArrayList<CashAccount> cashAccounts;
    ArrayList<Transaction> transactionLog;

    public void buyEquity(MarketEquity target, int numShares){
        //add target equity to HoldingEquity with numShares
    }

    public void sellEquity(MarketEquity target, int numShares){
        //remove target number of shares from Equity holding, **if shares goes to 0 delete object?
    }

    public void addCashAccount(CashAccount target, float initialBalance){
        //add target cash account with initial balance
    }

    public void removeCashAccount(CashAccount target){
        //remove target cash account
    }
}
