package Models.UndoRedo;

import Models.Transaction.Transaction;

/**
 * The interface to make use of the Undo Redo functions.
 */
public interface UndoRedo extends Transaction {

    /**
     * run the opposite of the command
     */
    void unExecute();
}
