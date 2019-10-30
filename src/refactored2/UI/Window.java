package refactored2.UI;

public interface Window {
    Input getInput();
    Output getOutput();
    Popup getPopup();
    void show(boolean doShow);
    void exit();
}
