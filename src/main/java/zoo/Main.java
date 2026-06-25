package zoo;

import zoo.animal.*;
import zoo.enclosure.*;

import java.util.Map;
import java.util.logging.*;

public class Main {

  public static void main(String[] args) {

    // Zoo befüllen
    Zoo zoo = new Zoo();

    Aquarium aquarium = new Aquarium("Großes Aquarium");
    aquarium.add(new Goldfish("Trixi"));
    aquarium.add(new Goldfish("Toni"));
    aquarium.add(new Clownfish("Nemo"));

    Terrarium terrarium = new Terrarium("Reptilienhaus");
    terrarium.add(new Turtel("Gerry"));
    terrarium.add(new Snake("Pete"));

    MammalHouse mammalHouse = new MammalHouse("Saeugetierhaus");
    mammalHouse.add(new Gorilla("Ellie"));
    mammalHouse.add(new Chimpanzee("Earl"));
    mammalHouse.add(new Lion("Leo"));

    CatHouse lionHouse = new CatHouse("Katzengehege");
    lionHouse.add(new Lion("Simba"));
    lionHouse.add(new Tiger("Mufasa"));

    zoo.addEnclosure(aquarium);
    zoo.addEnclosure(terrarium);
    zoo.addEnclosure(mammalHouse);
    zoo.addEnclosure(lionHouse);

    // Abfragen demonstrieren
    System.out.println("\n--- Zusammenfassung ---");
    System.out.println(zoo.summary());

    System.out.println("\n--- Alle Saeugetiere ---");
    zoo.getAllMammals().forEach(m -> System.out.println("  " + m));

    System.out.println("\n--- Tiere nach Praedikat (Name enthaelt 'e') ---");
    zoo.getAnimalsByPredicate(a -> a.name().toLowerCase().contains("e"))
            .forEach(a -> System.out.println("  " + a));

    System.out.println("\n--- Tiertypen-Zaehlung ---");
    Map<String, Long> counts = zoo.countAnimalsByType();
    counts.forEach((type, count) -> System.out.println("  " + type + ": " + count));

    System.out.println("\n--- Ueberfuellte Gehege (> 2 Tiere) ---");
    zoo.getOvercrowdedEnclosures(2)
            .forEach(e -> System.out.println("  " + e));

    System.out.println("\n--- Gehege suchen: 'Aquarium XYZ' (nicht vorhanden) ---");
    var notFound = zoo.findEnclosureByName("Aquarium XYZ");
    System.out.println("  Ergebnis: " + notFound);
  }
}
