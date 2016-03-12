package Portfolio;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * Represents a cash account of a user
 */
public class CashAccount {
    private String name;
    private float balance;
    private LocalDateTime dateAdded;

    public CashAccount(String name, float balance) {
        this.name = name;
        this.balance = balance;
        this.dateAdded = LocalDateTime.now();
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

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

}
