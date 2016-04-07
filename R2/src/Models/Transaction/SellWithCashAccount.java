package Models.Transaction;

import Models.Portfolio.CashAccount;
import Models.Portfolio.HoldingEquity;
import Models.Portfolio.Portfolio;
import Models.UndoRedo.UndoRedo;

public class SellWithCashAccount extends ExtendedTransactionDecorator{

    private CashAccount cashAccount;

    public SellWithCashAccount(UndoRedo decoratorTarget, CashAccount cashAccount) {
        super(decoratorTarget);
        this.cashAccount = cashAccount;
    }

    @Override
    public void execute(){
        HoldingEquity target = ((SellTransaction)transactionToBoDecorated).getTarget();
        int amount = ((SellTransaction)transactionToBoDecorated).getAmount();
        Portfolio portfolio = ((SellTransaction)transactionToBoDecorated).getPortfolio();
        if(target.getNumShares() - amount >=0) {
            cashAccount.deposit(amount);
        }
        else failed = true;
        transactionToBoDecorated.execute();
    }

    @Override
    public void unExecute(){

    }
}