package Models.Transaction;

import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Adds a CashAccount
 * to a portfolio
 */
public class AddCashAccTransaction implements UndoRedo, Serializable {

    //region Fields
    //the initial starting balance of the account
    private float amount;

    //Keeps track of when this command was created
    private LocalDateTime date;

    //reference to the portfolio to add the accoount to
    private Portfolio portfolio;

    //the proposed name of the cash account
    private String name;

    //the failure status of this command
    private boolean failed;
    //endregion

    /** Constructor
     * @param name The name of the cash account
     * @param amount the initial starting balance of the account
     * @param portfolio reference to the portfolio to add this to
     */
    public AddCashAccTransaction(String name, float amount, Portfolio portfolio) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.name = name;
        this.failed = false;
    }

    /**
     * Adds the cash account to the portfolio if one with the same name doesn't already exist
     */
    @Override
    public void execute() {
        //check that an account with the same name doesn't already exist
        if(portfolio.getCashAccNameExists(name) != -1) portfolio.addCashAccount(name,amount);

        //set the failed status to true otherwise
        else failed = true;
    }

    /** Gives a string representation dependant on whether the commmand failed or not
     * @return the failure dependant string representation
     */
    @Override
    public String toString() {
        if(failed) return "Could not create Cash Account '" + name + "' with initial balance of " + amount + " on " + date;
        return "Created Cash Account '" + name + "' with initial balance of " + amount + " on " + date;
    }

    /**
     * Handles undoing the action of execute()
     */
    @Override
    public void unExecute() {
        //don't attempt to unexecute if execution failed
        if(failed) return;

        //remove the cash account that was added by this command
        portfolio.removeCashAccount(portfolio.getCashAccounts().get(portfolio.getCashAccNameExists(name)));
    }
}
