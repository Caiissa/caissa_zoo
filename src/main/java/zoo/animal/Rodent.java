package zoo.animal;

public sealed interface Rodent extends Mammel permits Capybara, Beaver {}
