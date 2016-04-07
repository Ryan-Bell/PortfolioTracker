package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RemoveCashAccTransaction implements UndoRedo, Serializable {
    private CashAccount cashAccount;
    private Portfolio portfolio;
    private LocalDateTime date;
    private boolean failed;


    public RemoveCashAccTransaction(CashAccount cashAccount, Portfolio portfolio) {
        this.cashAccount = cashAccount;
        this.portfolio  = portfolio;
        date = LocalDateTime.now();
        this.failed = false;
    }

    @Override
    public void execute() {
        if(portfolio.getCashAccNameExists(cashAccount.getName()) != -1) portfolio.removeCashAccount(cashAccount);
        else failed = true;
    }

    @Override
    public String toString() {
        if(failed) return "Could not remove Cash Account '" + cashAccount.getName() + "' with balance of " + cashAccount.getBalance() + " on " + date;
        return "Removed Cash Account '" + cashAccount.getName() + "' with balance of " + cashAccount.getBalance() + " on " + date;
    }

    @Override
    public void unExecute() {
        if(failed) return;
        portfolio.addCashAccount(cashAccount.getName(), cashAccount.getBalance());
    }

}
