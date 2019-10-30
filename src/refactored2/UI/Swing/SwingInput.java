package refactored2.UI.Swing;

import refactored2.UI.Input;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;


public class SwingInput implements Input {
    private static final KeyStroke SUBMIT_KEY = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

    JTextComponent input;
    Consumer<String> onSubmit = s -> {};

    public SwingInput(JTextComponent input) {
        this.input = input;
        input.registerKeyboardAction(e -> onSubmit.accept(input.getText()), SUBMIT_KEY, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void append(String message) {
        input.setText(input.getText()+message);
    }

    @Override
    public String clear() {
        String clearedText = input.getText();
        input.setText("");
        return clearedText;
    }

    @Override
    public void setOnSubmit(Consumer<String> onSubmit) {
        this.onSubmit = onSubmit;
    }
}
