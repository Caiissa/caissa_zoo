package zoo.enclosure;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import zoo.animal.Animal;

public class Enclosure<T extends Animal> {

  private final String name;

  // Es soll keine Duplikate haben und die Reinfolge ist nicht wichtig, deswegen habe ich mich für
  // eine HashSet entschieden
  private final Set<T> inhabitants = new HashSet<>();

  public Enclosure(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public boolean add(T animal) {
    return inhabitants.add(animal);
  }

  public boolean remove(T animal) {
    return inhabitants.remove(animal);
  }

  public List<T> getInhabitants() {
    return List.copyOf(inhabitants);
  }

  public int size() {
    return inhabitants.size();
  }

  public Optional<T> findAnimalByName(String animalName) {
    return getInhabitants().stream().filter(t -> animalName.equals(t.name())).findFirst();
  }

  @Override
  public String toString() {
    return "Enclosure[name=" + name + ", inhabitants=" + inhabitants.size() + "]";
  }
}
