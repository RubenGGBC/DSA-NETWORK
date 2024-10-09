package exceptions;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String collection) {
        super("The " + collection + " does not contain comparable elements.");
    }
}
