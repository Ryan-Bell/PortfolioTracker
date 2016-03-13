package Transaction;

import Portfolio.CashAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Command allowing users to withdraw money from a CashAccount and add it to
 * a different CashAccount
 */

public class TransferTransaction implements Transaction, Serializable {


    private CashAccount withdrawTarget;
    private CashAccount depositTarget;
    private float amount;
    private LocalDateTime date;

    public TransferTransaction(CashAccount withdrawTarget, CashAccount depositTarget, float amount) {
        this.withdrawTarget = withdrawTarget;
        this.depositTarget = depositTarget;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    @Override
    public void execute() {
        if (withdrawTarget.sufficientFunds(amount)){
            withdrawTarget.withdraw(amount);
            depositTarget.deposit(amount);
        }
    }

    @Override
    public String toString() {
        return "WithdrawTransaction Account:\t" + withdrawTarget.getName() + "DepositTransaction Account:\t" + depositTarget.getName() + "\tAmount Deposited:\t" + amount + "\tDate:\t" + date;
    }
}
