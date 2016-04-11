package Models.Portfolio;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * CashAccount has a name and holds a balance to be tracked by a Portfolio and
 * may be used in the process of buying and selling equities
 */
public class CashAccount implements Serializable {
    private String name;
    private float balance;
    private LocalDateTime dateAdded;

    /**
     * Constructor.
     * @param name      name of cash account
     * @param balance   initial balance of cash account
     */
    public CashAccount(String name, float balance) {
        this.name = name;
        this.balance = balance;
        this.dateAdded = LocalDateTime.now();
    }

    //region GetterSetter

    /** Getter for name
     * @return the name of the account
     */
    public String getName() {
        return name;
    }

    /** Getter for the balance
     * @return the account balance
     */
    public float getBalance() {
        return balance;
    }

    /** Getter for the date
     * @return the date the account was added
     */
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    /** Setter for the balance of balance
     * @param balance the balance to set thee account to
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }
    //endregion

    /** checks that the account has sufficient funds
     * @param amount the amount to check sufffeciency for
     * @return whether there are enough funds or not
     */
    public boolean sufficientFunds(float amount){
        return this.getBalance() - amount >= 0;
    }

    /** Handles decreasing account funds
     * @param amount the amount to decrease by
     */
    public void withdraw(float amount){
        float newBalance;
        newBalance = this.getBalance() - amount;
        setBalance(newBalance);

    }

    /** Handles increasing account funds
     * @param amount the amount too increase by
     */
    public void deposit(float amount){
        float newBalance;
        newBalance = this.getBalance() + amount;
        setBalance(newBalance);
    }



    @Override
    public String toString(){
        return "Name: " + this.getName() + " Balance: " + this.getBalance();
    }

}
