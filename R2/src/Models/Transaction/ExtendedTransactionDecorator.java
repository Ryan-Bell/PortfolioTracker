package Models.Transaction;

import Models.UndoRedo.UndoRedo;

import java.io.Serializable;

/**
 * Abstract class that defines behaviour for decorators of UndoRedo Objects
 */
public abstract class ExtendedTransactionDecorator implements UndoRedo, Serializable{

    //region Fields
    //the UndoRedo object that is being decorated
    protected UndoRedo transactionToBoDecorated;

    //the failure status of the action specific to this decorator
    protected boolean failed;
    //endregion

    /** Constructor
     * @param decoratorTarget the object being decorated
     */
    public ExtendedTransactionDecorator(UndoRedo decoratorTarget){
        transactionToBoDecorated = decoratorTarget;
        failed = false;
    }

    /**
     * Base unExecute just calls unExecute of the decoratee if not overridden
     */
    @Override
    public void unExecute() {
        transactionToBoDecorated.unExecute();
    }

    /**
     * Base execute just calls execute of the decoratee if not overridden
     */
    @Override
    public void execute() {
        transactionToBoDecorated.execute();
    }
}
