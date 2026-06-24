package zoo.animal;

public sealed interface Bird extends Animal permits Penguin, Crow {}
