package zoo.animal;

public sealed interface Animal permits Mammel, Fish, Reptile, Bird {}
