package piotr.koziol.zoo.exceptions;

public class ZoneAlreadyExistsException extends RuntimeException {
    public ZoneAlreadyExistsException() {
        super("Such zone already exists");
    }
}
