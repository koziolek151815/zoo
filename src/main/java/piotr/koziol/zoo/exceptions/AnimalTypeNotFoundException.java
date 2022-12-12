package piotr.koziol.zoo.exceptions;

public class AnimalTypeNotFoundException extends RuntimeException {
    public AnimalTypeNotFoundException() {
        super("Animal type not found");
    }
}
