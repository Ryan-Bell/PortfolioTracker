package Models.UndoRedo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Holds functionality for the use of undoing and redoing commands.
 * Objects that wish to be undone/redone must implement the UndoRedo interface.
 */
public class UndoRedoFunctions {
    private static UndoRedoFunctions instance;

    Deque<UndoRedo> undoStack;
    Deque<UndoRedo> redoStack;

    private UndoRedoFunctions() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public static UndoRedoFunctions getInstance() {
        if (instance == null) {
            createNewUndoRedoFunctions();
        }
        return instance;
    }

    /**
     * creates a new instance of the UndoRedoFunctions
     */
    public static void createNewUndoRedoFunctions() {
        instance = new UndoRedoFunctions();
    }

    /**
     * Undo's the last command.
     * @return obj - the undone command
     */
    public UndoRedo undo() {
        UndoRedo obj = undoStack.pop();
        obj.unExecute();
        pushOnRedoStack(obj);

        return obj;
    }

    /**
     * call to redo the last command
     */
    public UndoRedo redo() {
        UndoRedo obj = redoStack.pop();
        obj.execute();
        pushOnUndoStack(obj);

        return obj;
    }

    /**
     * Executes the command and pushes it to the undoStack.
     * @param obj - the command to execute
     */
    public void execute(UndoRedo obj) {
        obj.execute();
        pushOnUndoStack(obj);
        redoStack = new ArrayDeque<>();
    }

    /**
     * unExecutes the command and pushes it to the redoStack.
     * @param obj - the command to unExecute
     */
    public void unExecute(UndoRedo obj) {
        obj.unExecute();
        pushOnRedoStack(obj);
    }

    /**
     * pushing an UndoRedo object to the redo stack.
     * @param obj - an UndoRedo object
     */
    public void pushOnRedoStack(UndoRedo obj) {
        this.redoStack.push(obj);
    }

    /**
     * pushing an UndoRedo object to the undo stack.
     * @param obj - an UndoRedo object
     */
    public void pushOnUndoStack(UndoRedo obj) {
        this.undoStack.push(obj);
    }

    /**
     * True if the undo stack is empty.
     * @return boolean
     */
    public boolean isUndoEmpty() {
        return this.undoStack.size() == 0;
    };

    /**
     * true if the redo stack is empty.
     * @return boolean
     */
    public boolean isRedoEmpty() {
        return this.redoStack.size() == 0;
    }
}
