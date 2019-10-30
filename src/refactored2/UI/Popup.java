package refactored2.UI;

public interface Popup {
    void alert(String message);
    boolean confirm(String message);
    String input(String message);
}
