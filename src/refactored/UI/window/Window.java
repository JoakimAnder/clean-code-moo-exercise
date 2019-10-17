package refactored.UI.window;

import refactored.UI.popup.Popup;
import refactored.UI.textField.TextField;

public interface Window {

    TextField input();
    TextField output();

    Popup popup();

    void show(boolean doShow);
    void quit();
}
