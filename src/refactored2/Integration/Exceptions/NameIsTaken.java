package refactored2.Integration.Exceptions;

public class NameIsTaken extends IllegalArgumentException {
    public NameIsTaken(String name) {
        super(String.format("Name '%s' has already been taken", name));
    }
}
