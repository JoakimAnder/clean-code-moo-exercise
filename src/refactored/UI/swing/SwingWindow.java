package refactored.UI.swing;

import refactored.UI.popup.Popup;
import refactored.UI.textField.TextField;
import refactored.UI.window.Window;

public class SwingWindow implements Window {

    private SwingSetup swing;
    private SwingTextField input;
    private SwingTextField output;
    private SwingPopup popup;

    public static Window createWindow() {
        return new SwingWindow();
    }

    private SwingWindow() {
        swing = SwingSetup.createSwingSetup();
        input = new SwingTextField(swing.getInput());
        output = new SwingTextField(swing.getOutput());
        popup = new SwingPopup();

        swing.getButton().addActionListener(e -> {
            input.getOnSubmit().accept(input.getAndClear());
        });
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
