package Transaction;

import Portfolio.CashAccount;

import java.time.LocalDateTime;

/**
 * Command allowing user to add money to a cash account
 */
public class Deposit implements Transaction{

    private CashAccount target;
    private float amount;
    private LocalDateTime date;

    public Deposit(CashAccount target, float amount){
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
        return "Account:\t" + target + "\tAmount Deposited:\t" + amount + "\tDate:\t" + date;
    }
}
