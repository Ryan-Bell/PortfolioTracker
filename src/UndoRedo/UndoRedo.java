package UndoRedo;

public interface UndoRedo {

    /**
     * run a concrete command
     */
    void execute();

    /**
     * undo what the command previously did
     */
    void unExecute();
}
