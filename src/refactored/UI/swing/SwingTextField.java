package refactored.UI.swing;

import refactored.UI.textField.TextField;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class SwingTextField implements TextField {
    private static final KeyStroke SUBMIT_KEY = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

    private JTextComponent textComponent;
    private Consumer<String> onSubmit = s -> {};

    public SwingTextField(JTextComponent textComponent) {
        this.textComponent = textComponent;
        textComponent.registerKeyboardAction(e -> onSubmit.accept(getAndClear()), SUBMIT_KEY, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public Consumer<String> getOnSubmit() {
        return onSubmit;
    }

    @Override
    public void setOnSubmit(Consumer<String> onSubmit) {
        this.onSubmit = onSubmit;
    }

    @Override
    public String getText() {
        return textComponent.getText();
    }

    @Override
    public String getAndClear() {
        String text = getText();
        textComponent.setText("");
        return text;
    }

    @Override
    public void append(String text) {
        textComponent.setText(getText().concat(text));
    }
}
