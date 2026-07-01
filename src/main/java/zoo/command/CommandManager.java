package zoo.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

// Muss noch verwendet werdem

public class CommandManager<T> {

  private static final Logger log = Logger.getLogger(CommandManager.class.getName());

  private final Deque<Command<T>> undoStack = new ArrayDeque<>();
  private final Deque<Command<T>> redoStack = new ArrayDeque<>();

  public void executeCommand(Command<T> command, T target) {
    log.info("executeCommand: " + command.description());

    command.execute(target);

    undoStack.push(command);
    redoStack.clear();

    log.fine("Target: " + target);
  }

  public void undo(T target) {
    log.info("undo");

    if (undoStack.isEmpty()) {
      log.warning("Undo nicht möglich.");
      return;
    }

    Command<T> command = undoStack.pop();
    command.undo(target);
    redoStack.push(command);

    log.fine("Target: " + target);
  }

  public void redo(T target) {
    log.info("redo");

    if (redoStack.isEmpty()) {
      log.warning("Redo nicht möglich.");
      return;
    }

    Command<T> command = redoStack.pop();
    command.execute(target);
    undoStack.push(command);

    log.fine("Target: " + target);
  }
}
