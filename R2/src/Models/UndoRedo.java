package Models;

/**
 * The interface to make use of the Undo Redo functions.
 */
public interface UndoRedo {

    /**
     * run a command
     */
    void execute();

    /**
     * run the opposite of the command
     */
    void unExecute();
}
