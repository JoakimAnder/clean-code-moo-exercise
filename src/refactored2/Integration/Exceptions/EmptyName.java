package refactored2.Integration.Exceptions;

public class EmptyName extends IllegalArgumentException {
    public EmptyName() {
        super("Can not register an empty name");
    }
}
