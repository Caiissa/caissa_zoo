package zoo.animal;

public sealed interface Mammel extends Animal permits Primate, Rodent, Cat {}
