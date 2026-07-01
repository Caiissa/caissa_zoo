package zoo.command;

import java.util.logging.Logger;
import zoo.animal.Animal;
import zoo.enclosure.Enclosure;

public class AddAnimalCommand<T extends Animal> implements Command<Enclosure<T>> {

  private static final Logger log = Logger.getLogger(AddAnimalCommand.class.getName());

  private final T animal;
  private boolean executed = false;

  public AddAnimalCommand(T animal) {
    this.animal = animal;
  }

  @Override
  public void execute(Enclosure<T> enclosure) {
    if (enclosure.add(animal)) {
      executed = true;
      log.info(animal.name() + " wurde in " + enclosure.getName() + " hinzugefuegt");
    } else {
      log.warning(
          animal.name() + " konnte nicht in " + enclosure.getName() + " hinzugefuegt werden");
    }
  }

  @Override
  public void undo(Enclosure<T> enclosure) {
    if (!executed) {
      log.warning("Undo nicht moeglich: execute() wurde noch nicht ausgefuehrt.");
    }

    if (enclosure.remove(animal)) {
      executed = false;
      log.info(animal.name() + " wurde wieder entfernt.");
    } else {
      log.warning(() -> animal.name() + " konnte nicht entfernt werden.");
    }
  }

  @Override
  public String description() {
    return "Fügt das Tier '" + animal.name() + "' zum Gehege hinzu.";
  }
}
