package Models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Removes money from a CashAccount.
 */
public class WithdrawTransaction implements Transaction, Serializable {


    private CashAccount target;
    private float amount;
    private LocalDateTime date;
    private boolean failed;

    public WithdrawTransaction(CashAccount target, float amount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.failed = false;
    }

    @Override
    public void execute() {
        if (target.sufficientFunds(amount)){
            target.withdraw(amount);
        }
        else failed = true;

    }

    @Override
    public String toString() {
        if(failed)return "Could not withdraw "+amount+" out of "+target.getName()+" on "+date;
        return "Withdrew "+amount+" out of "+target.getName()+" on "+date;
    }
}
