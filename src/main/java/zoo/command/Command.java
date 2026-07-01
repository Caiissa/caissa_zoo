package zoo.command;

public interface Command<T> {
  void execute(T target);

  void undo(T target);

  String description();
}
