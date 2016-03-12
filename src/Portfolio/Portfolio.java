package Portfolio;

import Market.MarketEquity;
import Transaction.Transaction;

import java.io.*;
import java.util.ArrayList;

/**
 * Contains user equityholdings and cash accounts.
 */
public class Portfolio implements Serializable{
    private ArrayList<HoldingEquity> holdingEquities;
    private ArrayList<CashAccount> cashAccounts;
    private ArrayList<Transaction> transactionLog;


    public Portfolio(){
        holdingEquities =  new ArrayList<>();
        cashAccounts = new ArrayList<>();
        transactionLog = new ArrayList<>();
    }


    /**
     * creates and adds a specific number of shares of the given equity to this portfolio
     * @param target the equity that is being purchased
     * @param numShares the number of shares that are being aquired
     */
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

    /**
     * creates and adds a cash account object to this portfolio with the given name and initial balance
     * @param name the name requested for the cash account
     * @param initialBalance the starting balance of the account
     */
    public void addCashAccount(String name, float initialBalance){
        //add target cash account with initial balance
        CashAccount newCashAccount = new CashAccount(name, initialBalance);
        cashAccounts.add(newCashAccount);
    }

    /**
     * deletes a cash account from the portfolio
     * @param target the cash account to  be deleted
     */
    public void removeCashAccount(CashAccount target){
        //remove target cash account
        int index = cashAccounts.indexOf(target);
        cashAccounts.remove(index);
    }


    public static Portfolio deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return (Portfolio)obj;
    }

    public static void serialize(Object obj, String fileName)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        fos.close();
    }

}
