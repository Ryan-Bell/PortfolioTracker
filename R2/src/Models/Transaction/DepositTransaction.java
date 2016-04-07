package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Adds money to a CashAccount.
 */
public class DepositTransaction implements UndoRedo, Serializable {

    //region Fields
    //The cash account to add  funds to
    private CashAccount target;

    //the amount of money to add
    private float amount;

    //the time this command was created
    private LocalDateTime date;
    //endregion

    /** Constructor
     * @param target the cash account to add funds to
     * @param amount the amount of funds to add
     */
    public DepositTransaction(CashAccount target, float amount){
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    /**
     * Adds the funds to the cash account
     */
    @Override
    public void execute() {
        target.deposit(amount);
    }

    /** Return a string representation of this command
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Deposited " + amount + " into " + target.getName() + " on " + date;
    }

    /**
     * Reverts the action of the execute method
     */
    @Override
    public void unExecute() {
        //withdraw the money that was added with execute
        target.withdraw(amount);
    }
}
