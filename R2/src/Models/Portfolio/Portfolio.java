package Models.Portfolio;

import Models.Market.MarketEquity;
import Models.Transaction.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Contains user equityholdings and cash accounts.
 */
public class Portfolio extends Observable implements Observer,Serializable  {
    private ArrayList<HoldingEquity> holdingEquities;
    private ArrayList<CashAccount> cashAccounts;
    private ArrayList<Transaction> transactionLog;
    private String hashedPass;
    private String id;

    //Used to denote serialized version
    private static final long serialVersionUID = 681129221878275270L;

    /**
     * Constructor
     * @param hashedPass the hashed password to be associated with this object
     */
    public Portfolio(String hashedPass, String id){
        //create default array lists
        holdingEquities =  new ArrayList<>();
        cashAccounts = new ArrayList<>();
        transactionLog = new ArrayList<>();

        //save the hashed password
        this.hashedPass = hashedPass;
        this.id = id;


    }

    public ArrayList<CashAccount> getCashAccounts() {
        return cashAccounts;
    }

    public void setCashAccounts(ArrayList<CashAccount> cashAccounts) {
        this.cashAccounts = cashAccounts;
    }

    public ArrayList<Transaction> getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(ArrayList<Transaction> transactionLog) {
        this.transactionLog = transactionLog;
    }

    public ArrayList<HoldingEquity> getHoldingEquities() {
        return holdingEquities;
    }

    public void setHoldingEquities(ArrayList<HoldingEquity> holdingEquities) {
        this.holdingEquities = holdingEquities;
    }

    /**
     * returns the password
     * @return the password
     */
    public String getPassword(){ return hashedPass; }
    public String getId(){ return id; }

    public void addTransaction(Transaction transaction){
        transactionLog.add(transaction);

        setChanged();
        notifyObservers();
    }


    /**
     * creates and adds a specific number of shares of the given equity to this portfolio
     * @param target the equity that is being purchased
     * @param numShares the number of shares that are being aquired
     */
    public void buyEquity(MarketEquity target, int numShares){
        //add target equity to HoldingEquity with numShares
        HoldingEquity newEquity = new HoldingEquity(numShares,target.getValue(),target.getName(), target.getTickerSymbol(), target);
        holdingEquities.add(newEquity);

        setChanged();
        notifyObservers();
    }


    public void sellEquity(HoldingEquity target, int numShares){
        //remove target number of shares from Equity holding, **if shares goes to 0 delete object?
        target.setNumShares((target.getNumShares()-numShares));

        if(target.getNumShares() <= 0) {
            int index = holdingEquities.indexOf(target);
            holdingEquities.remove(index);
        }

        setChanged();
        notifyObservers();
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

        setChanged();
        notifyObservers();
    }

    /**
     * deletes a cash account from the portfolio
     * @param target the cash account to  be deleted
     */
    public void removeCashAccount(CashAccount target){
        //remove target cash account
        int index = cashAccounts.indexOf(target);
        cashAccounts.remove(index);

        setChanged();
        notifyObservers();
    }

    /**
     * returns the total value of equities in the portfolio.
     * @return value the total value fo the portfolio
     */
    public float getPortfolioValue() {
        float value = 0;

        for (int i = 0; i < holdingEquities.size(); i++) {
            value += holdingEquities.get(i).getValue();
        }

        return value;
    }

    @Override
    public void update(Observable o, Object arg){


    }

}
