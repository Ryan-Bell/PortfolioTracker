package Transaction;

import Portfolio.CashAccount;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Command allowing users to withdraw money from a CashAccount and add it to
 * a different CashAccount.
 */
public class TransferTransaction implements Transaction, Serializable {


    private CashAccount withdrawTarget;
    private CashAccount depositTarget;
    private float amount;
    private LocalDateTime date;
    private boolean failed;

    public TransferTransaction(CashAccount withdrawTarget, CashAccount depositTarget, float amount) {
        this.withdrawTarget = withdrawTarget;
        this.depositTarget = depositTarget;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.failed = false;
    }

    @Override
    public void execute() {
        if (withdrawTarget.sufficientFunds(amount)){
            withdrawTarget.withdraw(amount);
            depositTarget.deposit(amount);
        }
        else failed = true;
    }

    @Override
    public String toString() {
        if(failed) return "Could not withdraw "+amount+" out of "+withdrawTarget.getName()+" and deposit in "+depositTarget.getName()+" on "+date;
        return "Withdrew "+amount+" out of "+withdrawTarget.getName()+" and deposited in "+depositTarget.getName()+" on "+date;
    }
}
