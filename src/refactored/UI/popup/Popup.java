package refactored.UI.popup;

public interface Popup {

    void error(String message);
    boolean confirm(String message);
    String input(String message);

}
