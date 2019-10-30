package refactored2.UI.Swing;

import refactored2.UI.Input;
import refactored2.UI.Output;
import refactored2.UI.Popup;
import refactored2.UI.Window;

public class SwingWindow implements Window {

    SetupWindow setup;
    SwingInput input;
    SwingOutput output;
    SwingPopup popup;

    public SwingWindow() {
        setup = SetupWindow.createSwingSetup();
        input = new SwingInput(setup.getInput());
        output = new SwingOutput(setup.getOutput());
        popup = new SwingPopup(setup.getWindow());

        setup.getButton().addActionListener(e -> {
            input.append("\n");
        });

    }


    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public Output getOutput() {
        return output;
    }

    @Override
    public Popup getPopup() {
        return popup;
    }

    @Override
    public void show(boolean doShow) {
        setup.getWindow().setVisible(doShow);
    }

    @Override
    public void exit() {
        setup.getWindow().dispose();
        System.exit(0);
    }
}
