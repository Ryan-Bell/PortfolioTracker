package Models.Transaction;

import Models.Portfolio.CashAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Adds money to a CashAccount.
 */
public class DepositTransaction implements Transaction, Serializable {

    private CashAccount target;
    private float amount;
    private LocalDateTime date;

    public DepositTransaction(CashAccount target, float amount){
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    @Override
    public void execute() {
        target.deposit(amount);
    }

    @Override
    public String toString() {
        return "Deposited "+amount+" into "+target.getName()+" on "+date;
    }
}
