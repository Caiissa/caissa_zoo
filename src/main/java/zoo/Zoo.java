package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import zoo.animal.Animal;
import zoo.animal.Mammel;
import zoo.enclosure.Enclosure;

public class Zoo {

  private final List<Enclosure<? extends Animal>> enclosures = new ArrayList<>();

  public void addEnclosure(Enclosure<? extends Animal> enclosure) {
    enclosures.add(enclosure);
  }

  public List<Enclosure<? extends Animal>> getEnclosures() {
    return List.copyOf(enclosures);
  }

  public Enclosure<? extends Animal> findEnclosureByName(String name) {
    return enclosures.stream()
        .filter(e -> e.getName().equals(name))
        .findFirst()
        .orElseGet(
            () -> {
              return null;
            });
  }

  public List<Animal> getAllAnimals() {
    return enclosures.stream()
        .flatMap(e -> e.getInhabitants().stream())
        .collect(Collectors.toList());
  }

  public List<Mammel> getAllMammals() {
    return getAllAnimals().stream()
        .filter(a -> a instanceof Mammel)
        .map(a -> (Mammel) a)
        .collect(Collectors.toList());
  }

  public List<Animal> getAnimalsByPredicate(Predicate<Animal> predicate) {
    return getAllAnimals().stream().filter(predicate).collect(Collectors.toList());
  }

  public Map<String, Long> countAnimalsByType() {
    return getAllAnimals().stream()
        .collect(Collectors.groupingBy(a -> a.getClass().getSimpleName(), Collectors.counting()));
  }

  public List<Enclosure<? extends Animal>> getOvercrowdedEnclosures(int overcrowded) {
    return enclosures.stream()
        .filter(e -> e.getInhabitants().size() >= overcrowded)
        .collect(Collectors.toList());
  }

  public String summary() {
    List<Animal> all = getAllAnimals();

    long mammals = all.stream().filter(a -> a instanceof Mammel).count();
    long birds = all.stream().filter(a -> a instanceof zoo.animal.Bird).count();
    long fish = all.stream().filter(a -> a instanceof zoo.animal.Fish).count();
    long reptiles = all.stream().filter(a -> a instanceof zoo.animal.Reptile).count();

    return String.format(
        "zoo.Zoo mit %d Gehegen und %d Tieren: %d Mammals, %d Birds, %d Fish, %d Reptiles",
        enclosures.size(), all.size(), mammals, birds, fish, reptiles);
  }
}
