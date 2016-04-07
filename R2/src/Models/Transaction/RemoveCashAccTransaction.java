package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Removes a cash account from a portfolio
 */
public class RemoveCashAccTransaction implements UndoRedo, Serializable {

    //region Fields
    //the cash account to remove
    private CashAccount cashAccount;

    //the portfolio to remove the account from
    private Portfolio portfolio;

    //the time this command was created
    private LocalDateTime date;

    //the failure status of this command
    private boolean failed;
    //endregion

    /** Constructor
     * @param cashAccount the cash account to remove
     * @param portfolio the protfolio to remove the  account from
     */
    public RemoveCashAccTransaction(CashAccount cashAccount, Portfolio portfolio) {
        this.cashAccount = cashAccount;
        this.portfolio  = portfolio;
        date = LocalDateTime.now();
        this.failed = false;
    }

    /**
     * Removes the cash account if the portfolio has it
     */
    @Override
    public void execute() {
        //check that the portfolio has the account and remove it or update fallure status
        if(portfolio.getCashAccNameExists(cashAccount.getName()) != -1) portfolio.removeCashAccount(cashAccount);
        else failed = true;
    }

    /** Gives a string representation of this command based on the failure status
     * @return
     */
    @Override
    public String toString() {
        if(failed) return "Could not remove Cash Account '" + cashAccount.getName() + "' with balance of " + cashAccount.getBalance() + " on " + date;
        return "Removed Cash Account '" + cashAccount.getName() + "' with balance of " + cashAccount.getBalance() + " on " + date;
    }

    /**
     * Revert the action of the execute method
     */
    @Override
    public void unExecute() {
        //Don't do anything if execute failed
        if(failed) return;

        //add the cash account back to portfolio that was removed in execute
        portfolio.addCashAccount(cashAccount.getName(), cashAccount.getBalance());
    }

}
