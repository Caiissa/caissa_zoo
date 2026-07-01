package zoo.command;

import java.util.logging.Logger;
import zoo.animal.Animal;
import zoo.enclosure.Enclosure;

public class RemoveAnimalCommand<T extends Animal> implements Command<Enclosure<T>> {

  private static final Logger log = Logger.getLogger(RemoveAnimalCommand.class.getName());

  private final T animal;
  private boolean executed = false;

  public RemoveAnimalCommand(T animal) {
    this.animal = animal;
  }

  @Override
  public void execute(Enclosure<T> enclosure) {
    if (enclosure.remove(animal)) {
      executed = true;
      log.info(animal.name() + " wurde aus " + enclosure.getName() + " entfernt");
    } else {
      log.warning(animal.name() + " konnte nicht aus " + enclosure.getName() + " entfernt werden");
    }
  }

  @Override
  public void undo(Enclosure<T> enclosure) {
    if (!executed) {
      log.warning("Undo nicht moeglich: execute() wurde noch nicht ausgefuehrt.");
    }

    if (enclosure.add(animal)) {
      executed = false;
      log.info(animal.name() + " wurde wieder hinzugefuegt.");
    } else {
      log.warning(() -> animal.name() + " konnte nicht hinzugefuegt werden.");
    }
  }

  @Override
  public String description() {
    return "Entfernt das Tier '" + animal.name() + "' zum Gehege hinzu.";
  }
}
