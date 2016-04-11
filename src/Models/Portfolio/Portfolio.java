package Models.Portfolio;

import Models.Market.MarketEquity;
import Models.Transaction.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Contains user equityholdings and cash accounts.
 */
public class Portfolio extends Observable implements Observer,Serializable  {
    //region Fields
    private ArrayList<HoldingEquity> holdingEquities;
    private ArrayList<CashAccount> cashAccounts;
    private ArrayList<Transaction> transactionLog;
    private String hashedPass;
    private String id;
    private HashMap<String, Integer> cashAccountNames;
    private HashMap<String, Integer> equityNames;
    private ArrayList<MarketEquity> watchEquities;
    //endregion

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
        watchEquities = new ArrayList<>();

        //save the hashed password
        this.hashedPass = hashedPass;
        this.id = id;

        //instantiate the cash account name map
        cashAccountNames = new HashMap<>();

        //Instantiate the equities name map
        equityNames = new HashMap<>();


    }
    //region GetterSetter

    /** Getter for the cash account list
     * @return the list of cash accounts
     */
    public ArrayList<CashAccount> getCashAccounts() {
        return cashAccounts;
    }

    /** Returns the index of a cash account by name
     * @param name the name to search for
     * @return  the index of the cash acccount or -1
     */
    public int getCashAccNameExists(String name){return cashAccountNames.containsKey(name) ? cashAccountNames.get(name) : -1;}

    /** Returns the index of a equity by name
     * @param name the name to search for
     * @return the index of the equity or -1
     */
    public int getEquityNameExists(String name){return equityNames.containsKey(name) ? equityNames.get(name) : -1;}

    /** Getter for the transaction log
     * @return the transaction list
     */
    public ArrayList<Transaction> getTransactionLog() {
        return transactionLog;
    }

    /** Getter for the holding equities
     * @return the list of hodings
     */
    public ArrayList<HoldingEquity> getHoldingEquities() {
        return holdingEquities;
    }

    /** Getter for the watchlist
     * @return the list of watch equities
     */
    public ArrayList<MarketEquity> getWatchEquities(){return watchEquities;}

    /** Setter for list of watched equities
     * @param watchEquities the list to set to
     */
    public void setWatchEquities(ArrayList<MarketEquity> watchEquities){this.watchEquities = watchEquities;}

    /**
     * returns the password
     * @return the password
     */
    public String getPassword(){ return hashedPass; }

    /** Getter for the id
     * @return the id of the portfolio
     */
    public String getId(){ return id; }
    //endregion

    /** Adds a transaction to the transaction log
     * @param transaction  the transaction to add
     */
    public void addTransaction(Transaction transaction){
        transactionLog.add(transaction);

        //notify the observers that the log has changed
        setChanged();
        notifyObservers();
    }

    /** Removed a transaction from the transaction log
     * @param transaction the transaction to remove
     */
    public void removeTransaction(Transaction transaction){
        transactionLog.remove(transaction);

        //notify the observers that the log has changed
        setChanged();
        notifyObservers();
    }


    /**
     * creates and adds a specific number of shares of the given equity to this portfolio
     * @param target the equity that is being purchased
     * @param numShares the number of shares that are being aquired
     */
    public void buyEquity(MarketEquity target, int numShares){
        //If the portfolio already has a copy of the equity
        if(getEquityNameExists(target.getName()) != -1){
            HoldingEquity currentEquity = holdingEquities.get(getEquityNameExists((target.getName())));
            currentEquity.setNumShares(currentEquity.getNumShares() + numShares);
        }
        else {
            //add target equity to HoldingEquity with numShares
            HoldingEquity newEquity = new HoldingEquity(numShares, target.getValue(), target.getName(), target.getTickerSymbol(), target);
            holdingEquities.add(newEquity);

            //update the equities hashmap
            equityNames.put(newEquity.getName(), holdingEquities.indexOf(newEquity));
        }

        setChanged();
        notifyObservers(ObserveType.HOLDING_EQUITY);
    }


    public void sellEquity(HoldingEquity target, int numShares){
        //remove target number of shares from Equity holding, **if shares goes to 0 delete object?
        target.setNumShares((target.getNumShares()-numShares));

        if(target.getNumShares() <= 0) {
            //update the equities hashmap
            equityNames.remove(target.getName());

            int index = holdingEquities.indexOf(target);
            holdingEquities.remove(index);

        }

        setChanged();
        notifyObservers(ObserveType.HOLDING_EQUITY);
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

        //update the names hashmap
        cashAccountNames.put(name, cashAccounts.indexOf(newCashAccount));

        setChanged();
        notifyObservers(ObserveType.CASH_ACCOUNT);
    }

    /**
     * deletes a cash account from the portfolio
     * @param target the cash account to  be deleted
     */
    public void removeCashAccount(CashAccount target){
        //update the names hashmap
        cashAccountNames.remove(target.getName());

        //remove target cash account
        int index = cashAccounts.indexOf(target);
        cashAccounts.remove(index);


        setChanged();
        notifyObservers(ObserveType.CASH_ACCOUNT);
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
