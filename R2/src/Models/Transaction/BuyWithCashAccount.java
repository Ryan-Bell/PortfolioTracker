package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.UndoRedo.UndoRedo;

public class BuyWithCashAccount extends ExtendedTransactionDecorator{

    private CashAccount cashAccount;

    public BuyWithCashAccount(UndoRedo decoratorTarget, CashAccount cashAccount) {
        super(decoratorTarget);
        this.cashAccount = cashAccount;
    }

    @Override
    public void execute(){
        float cost = ((BuyTransaction)transactionToBoDecorated).getAmount() * ((BuyTransaction)transactionToBoDecorated).getTarget().getValue();
        if (cashAccount.sufficientFunds(cost)) {
            cashAccount.withdraw(cost);
        } else failed = true;
        transactionToBoDecorated.execute();
    }

    @Override
    public void unExecute(){

    }
}
