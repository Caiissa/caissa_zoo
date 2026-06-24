package zoo.animal;

public sealed interface Primate extends Mammel permits Gorilla, Chimpanzee {}
