package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Responds to user input in order to create a CashAccount.
 */
public class AddCashAccTransaction implements UndoRedo, Serializable {

    private float amount;
    private LocalDateTime date;
    private Portfolio portfolio;
    private String name;
    private boolean failed;


    public AddCashAccTransaction(String name, float amount, Portfolio portfolio) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.portfolio = portfolio;
        this.name = name;
        this.failed = false;
    }

    @Override
    public void execute() {
        boolean doesntExist = true;
        for (CashAccount account:portfolio.getCashAccounts()) {
            if(account.getName().equals(name)){
                doesntExist = false;
            }
        }
        if(doesntExist) portfolio.addCashAccount(name,amount);
        else failed = true;
    }

    @Override
    public String toString() {
        if(failed) return "Could not create Cash Account '"+name+"' with initial balance of "+amount+" on "+date;
        return "Created Cash Account '"+name+"' with initial balance of "+amount+" on "+date;
    }

    @Override
    public void unExecute() {

    }
}
