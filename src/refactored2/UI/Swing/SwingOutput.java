package refactored2.UI.Swing;

import refactored2.UI.Output;

import javax.swing.*;

public class SwingOutput implements Output {

    private JTextArea output;

    public SwingOutput(JTextArea output) {
        this.output = output;
    }

    @Override
    public String clear() {
        String clearedText = output.getText();
        output.setText("");
        return clearedText;
    }

    @Override
    public void print(String message) {
        output.append(message);
    }

    @Override
    public void printLine(String message) {
        print(message+"\n");
    }

    @Override
    public void set(String message) {
        output.setText(message);
    }

    @Override
    public boolean isEmpty() {
        return output.getText().trim().isEmpty();
    }
}
