package Models.Transaction;

import Models.UndoRedo.UndoRedo;

public abstract class ExtendedTransactionDecorator implements UndoRedo{

    protected UndoRedo transactionToBoDecorated;
    protected boolean failed;

    public ExtendedTransactionDecorator(UndoRedo decoratorTarget){
        transactionToBoDecorated = decoratorTarget;
        failed = false;
    }

    @Override
    public void unExecute() {
        transactionToBoDecorated.unExecute();
    }

    @Override
    public void execute() {
        transactionToBoDecorated.execute();
    }
}
