## Aufgabe 3: Reflektion

### 1. Generics

**Wo helfen Generics, Fehler zur Compile-Zeit zu vermeiden?**

`Enclosure<T extends Animal>` stellt sicher, dass nur `Animal`-Subtypen als Gehege-Bewohner zugelassen sind. 
Spezialisierungen wie `Terrarium extends Enclosure<Reptile>` schränken den erlaubten Typ weiter ein.

**Beispiel:**

Dadurch kann kein `Cat` Objekt in das `Terrarium`.

```java
Terrarium terrarium = new Terrarium("Reptilienhaus");
terrarium.add(new Tiger("Gerry")); //Compilerfehler
```

### 2. Logging

**Warum ist systematisches Logging sinnvoller als `IO.println`?**

- **Filterbarkeit:** Log-Level erlauben gezieltes
  Ein-/Ausblenden von Ausgaben ohne Codeänderung.
- **Nachvollziehbar:** Der Logger schreiben automatisch Zeitstempel, Klassen- und
  Methodennamen mit, wodurch der Kontext besser erfasst werden kann.


**Verwendung der Log-Level:**

- `INFO`: Jeder Methodenaufruf mit Parametern (z.B. `addEnclosure(Enclosure<? extends Animal> enclosure)`)
- `FINE`: Zustandszusammenfassung nach Ausführung (z.B. "Das Terrarium hat zwei Schildkröten")
- `WARNING`: Tier oder Gehege nicht gefunden (`findEnclosureByName` → kein Treffer)
- `SEVERE`: Bei Schwierigen Inkonsistenz

### 3. Streams

**Wo haben Streams geholfen?**

- `flatMap` in `getAllAnimals()`: Das Zusammenführen einer Liste von Listen
  (Gehege → Bewohner) ist mit Streams ein Einzeiler, mit Schleifen bräuchte man
  eine äußere und eine innere `for`-Schleife und manuelle List-Verkettung.

**Wo wurde es unübersichtlich?**

- `summary()`: Mehrere separate `filter`-Durchläufe über dieselbe Liste.
- Wildcards (`? extends Animal`) bei Gehege-Listen erschweren die Stream-Nutzung,
da Java die Typ-Informationen an dieser Stelle nicht mehr vollständig kennt.





