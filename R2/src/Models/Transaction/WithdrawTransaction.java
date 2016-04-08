package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Removes money from a CashAccount.
 */
public class WithdrawTransaction implements UndoRedo, Serializable {

    //region Fields
    //The account being withdrawn from
    private CashAccount target;

    //the amount of money being withdrawn
    private float amount;

    //the time this command was created
    private LocalDateTime date;

    //the failure status of this command
    private boolean failed;
    //endregion

    /** Constructor
     * @param target the account being withdrawn from
     * @param amount the amount of money being withdrawn
     */
    public WithdrawTransaction(CashAccount target, float amount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.failed = false;
    }

    /**
     * withdraws the money from the account if there is sufficient funds
     */
    @Override
    public void execute() {
        //check that the account has enough funds
        if (target.sufficientFunds(amount)){
            target.withdraw(amount);
        }

        //update the failure status
        else failed = true;

    }

    /** Provides a string representation of this command based on the failure status
     * @return the string representation
     */
    @Override
    public String toString() {
        if(failed)return "Could not withdraw "+amount+" out of "+target.getName()+" on "+date;
        return "Withdrew "+amount+" out of "+target.getName()+" on "+date;
    }

    /**
     * Reverts the action of the execute command
     */
    @Override
    public void unExecute() {
        //Don't do anything if execute failed
        if(failed) return;

        //deposit the money that was withdrawn in execute
        target.deposit(amount);
    }
}
