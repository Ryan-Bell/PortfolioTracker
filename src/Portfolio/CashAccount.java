package Portfolio;

import java.util.Date;

/**
 * Represents a cash account of a user
 */
public class CashAccount {
    private String name;
    private float balance;
    private Date dateAdded;

    public CashAccount(String name, float balance, Date dateAdded) {
        this.name = name;
        this.balance = balance;
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean sufficientFunds(float amount){
        return this.getBalance() - amount >= 0;
    }

    public void withdraw(float amount){
        float newBalance;
        newBalance = this.getBalance() - amount;
        setBalance(newBalance);
    }

    public void deposit(float amount){
        float newBalance;
        newBalance = this.getBalance() + amount;
        setBalance(newBalance);
    }

    public Date getDateAdded() {
        return dateAdded;
    }

}
