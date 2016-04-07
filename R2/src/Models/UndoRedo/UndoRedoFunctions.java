package Models.UndoRedo;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Deque;

/**
 * Holds functionality for the use of undoing and redoing commands.
 * Objects that wish to be undone/redone must implement the UndoRedo interface.
 */
public class UndoRedoFunctions {
    UndoRedoFunctions instance;

    Deque<UndoRedo> undoStack;
    Deque<UndoRedo> redoStack;

    private UndoRedoFunctions() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public UndoRedoFunctions getInstance() {
        if (this.instance == null) {
            this.instance = new UndoRedoFunctions();
            return this.instance;
        } else {
            return this.instance;
        }
    }

    /**
     * call to undo the last command
     */
    public void undo() {
        try {
            UndoRedo obj = undoStack.pop();
            obj.unExecute();
            pushOnRedoStack(obj);
        } catch (EmptyStackException e) {
            System.out.println("No more commands to undo");
        }
    }

    /**
     * call to redo the last command
     */
    public void redo() {
        try {
            UndoRedo obj = redoStack.pop();
            obj.execute();
            pushOnUndoStack(obj);
        } catch (EmptyStackException e) {
            System.out.println("No more commands to redo");
        }
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

    public void pushOnRedoStack(UndoRedo obj) {
        this.redoStack.push(obj);
    }

    public void pushOnUndoStack(UndoRedo obj) {
        this.undoStack.push(obj);
    }
}
