package Transaction;

import Portfolio.CashAccount;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A concrete command. Removes money from a CashAccount.
 */

public class WithdrawTransaction implements Transaction, Serializable {


    private CashAccount target;
    private float amount;
    private LocalDateTime date;

    public WithdrawTransaction(CashAccount target, float amount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    @Override
    public void execute() {
        if (target.sufficientFunds(amount)){
            target.withdraw(amount);
        }

    }

    @Override
    public String toString() {
        return "Account:\t" + target + "\tAmount Withdrawn:\t" + amount + "\tDate:\t" + date;
    }
}
