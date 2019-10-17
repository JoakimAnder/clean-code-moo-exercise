package refactored.UI.window;

import refactored.UI.popup.Popup;
import refactored.UI.popup.SwingPopup;
import refactored.UI.textField.SwingTextField;
import refactored.UI.textField.TextField;

public class SwingWindow implements Window {

    private SwingSetup swing;
    private TextField input;
    private TextField output;
    private Popup popup;

    public static Window createWindow() {
        return new SwingWindow();
    }

    private SwingWindow() {
        swing = SwingSetup.createFxSetup();
        input = new SwingTextField(swing.getInput());
        output = new SwingTextField(swing.getOutput());
        popup = new SwingPopup();
    }

    @Override
    public TextField input() {
        return input;
    }

    @Override
    public TextField output() {
        return output;
    }

    @Override
    public Popup popup() {
        return popup;
    }

    @Override
    public void show(boolean doShow) {
        swing.getWindow().setVisible(doShow);
    }

    @Override
    public void quit() {
        swing.getWindow().dispose();
        System.exit(0);
    }
}
