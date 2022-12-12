package piotr.koziol.zoo.exceptions;

public class ZoneNotFoundException extends RuntimeException {
    public ZoneNotFoundException() {
        super("Zone not found");
    }
}
