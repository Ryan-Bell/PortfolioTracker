package Transaction;

import Portfolio.CashAccount;


import java.time.LocalDateTime;

/**
 * Command that allow as user to remove money from a CashAccount
 */
public class Withdraw implements Transaction{

    private CashAccount target;
    private float amount;
    private LocalDateTime date;

    public Withdraw(CashAccount target, float amount) {
        this.target = target;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    @Override
    public void execute() {
        target.withdraw(amount);

    }

    @Override
    public String toString() {
        return "Account:\t" + target + "\tAmount Withdrawn:\t" + amount + "\tDate:\t" + date;
    }
}
