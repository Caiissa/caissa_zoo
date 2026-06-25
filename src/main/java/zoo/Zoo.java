package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import zoo.animal.Animal;
import zoo.animal.Mammel;
import zoo.enclosure.Enclosure;

public class Zoo {

  private static final Logger log = Logger.getLogger(Zoo.class.getName());

  private final List<Enclosure<? extends Animal>> enclosures = new ArrayList<>();

  public void addEnclosure(Enclosure<? extends Animal> enclosure) {
    log.info("addEnclosure called: " + enclosure.getName());
    enclosures.add(enclosure);
    log.fine("Enclosure: " + enclosure.size());
  }

  public List<Enclosure<? extends Animal>> getEnclosures() {
    log.info("getEnclosures called");
    List<Enclosure<? extends Animal>> result = List.copyOf(enclosures);
    log.fine("result: " + result.size());
    return result;
  }

  public Enclosure<? extends Animal> findEnclosureByName(String name) {
    log.info("findEnclosureByName called: " + name);
    return enclosures.stream()
        .filter(e -> e.getName().equals(name))
        .findFirst()
        .orElseGet(
            () -> {
              log.fine("not found: " + name);
              return null;
            });
  }

  public List<Animal> getAllAnimals() {
    log.fine("getAllAnimals called");
    List<Animal> result = enclosures.stream()
            .flatMap(e -> e.getInhabitants().stream())
            .collect(Collectors.toList());
    log.fine("result: " + result.size());
    return result;
  }

  public List<Mammel> getAllMammals() {
    log.info("getAllMammals called");
    List<Mammel> result = getAllAnimals().stream()
            .filter(a -> a instanceof Mammel)
            .map(a -> (Mammel) a)
            .collect(Collectors.toList());
    log.fine("result: " + result.size());
    return result;
  }

  public List<Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
    log.info("getAnimalsByPredicate called");
    List<Animal> result = getAllAnimals().stream()
            .filter(predicate)
            .collect(Collectors.toList());
    log.fine("result: " + result.size());
    return result;
  }

  public Map<String, Long> countAnimalsByType() {
    log.info("countAnimalsByType called");
    Map<String, Long> result = getAllAnimals().stream()
            .collect(Collectors.groupingBy(
                    a -> a.getClass().getSimpleName(),
                    Collectors.counting()
            ));
    log.fine("result: " + result.size());
    return result;
  }

  public List<Enclosure<? extends Animal>> getOvercrowdedEnclosures(int overcrowded) {
    log.info("countAnimalsByType called: Overcrowded " + overcrowded);
    List<Enclosure<? extends Animal>> result = enclosures.stream()
            .filter(e -> e.size() > overcrowded)
            .collect(Collectors.toList());
    log.fine("result: " + result.size());
    return result;
  }

  public String summary() {
    log.info("summery called");

    List<Animal> all = getAllAnimals();

    long mammals = all.stream().filter(a -> a instanceof Mammel).count();
    long birds = all.stream().filter(a -> a instanceof zoo.animal.Bird).count();
    long fish = all.stream().filter(a -> a instanceof zoo.animal.Fish).count();
    long reptiles = all.stream().filter(a -> a instanceof zoo.animal.Reptile).count();

    String summary = String.format(
        "zoo.Zoo mit %d Gehegen und %d Tieren: %d Mammals, %d Birds, %d Fish, %d Reptiles",
        enclosures.size(), all.size(), mammals, birds, fish, reptiles);
    log.fine("Summary: " + summary);
    return summary;
  }
}
