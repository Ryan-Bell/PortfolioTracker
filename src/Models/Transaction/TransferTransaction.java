package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Concrete command allowing users to withdraw money from a CashAccount and add it to
 * a different CashAccount.
 */
public class TransferTransaction implements UndoRedo, Serializable {

    //region Fields
    //the account that money is being taken out of
    private CashAccount withdrawTarget;

    //the account that money is being put into
    private CashAccount depositTarget;

    //the amount of money being transferred
    private float amount;

    ///the time this command was created
    private LocalDateTime date;

    public LocalDateTime getDate(){return date;}

    //the failure status of this command
    private boolean failed;
    //endregion

    /** Constructor
     * @param withdrawTarget the account to withdraw from
     * @param depositTarget the account to deposit into
     * @param amount the money being transfered
     */
    public TransferTransaction(CashAccount withdrawTarget, CashAccount depositTarget, float amount) {
        this.withdrawTarget = withdrawTarget;
        this.depositTarget = depositTarget;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.failed = false;
    }

    /**
     * Transefers the moeny from one account to the other if there are enough fonds
     */
    @Override
    public void execute() {
        //check that the account being withdrawn from has enough money
        if (withdrawTarget.sufficientFunds(amount)){
            //transfer the money
            withdrawTarget.withdraw(amount);
            depositTarget.deposit(amount);
        }

        //update the failure status
        else failed = true;
    }

    /** Returns a string representation of this command based on the failure status
     * @return the string repressentation
     */
    @Override
    public String toString() {
        if(failed) return "Could not withdraw "+amount+" out of "+withdrawTarget.getName()+" and deposit in "+depositTarget.getName()+" on "+date;
        return "Withdrew "+amount+" out of "+withdrawTarget.getName()+" and deposited in "+depositTarget.getName()+" on "+date;
    }

    /**
     * Reverts the action of the execute method
     */
    @Override
    public void unExecute() {
        //Don't do anythin if execute failed
        if(failed) return;

        //swap the money back
        withdrawTarget.deposit(amount);
        depositTarget.withdraw(amount);
    }
}