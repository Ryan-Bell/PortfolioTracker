package UndoRedo;

import java.util.Stack;

public class UndoRedoFunctions {
    Stack<UndoRedo> undoStack;
    Stack<UndoRedo> redoStack;

    public UndoRedoFunctions() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void undo() {
        UndoRedo obj = undoStack.pop();
        obj.unExecute();
        pushOnRedoStack(obj);
    }

    public void redo() {
        UndoRedo obj = redoStack.pop();
        obj.execute();
        pushOnUndoStack(obj);
    }

    public void execute(UndoRedo obj) {
        obj.execute();
        pushOnUndoStack(obj);
        redoStack = new Stack<>();
    }

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
