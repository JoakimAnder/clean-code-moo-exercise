package refactored.UI.swing;

import javax.swing.*;
import java.awt.*;

public class SwingSetup {

    private JFrame window;
    private JTextArea output;
    private JTextField input;
    private JButton submitButton;

    static SwingSetup createSwingSetup() {
        SwingSetup window = new SwingSetup();
        window.setup();
        return window;
    }

    private SwingSetup() {
        window = new JFrame();
        output = new JTextArea();
        input = new JTextField();
        submitButton = new JButton("Send");
    }

    private void setup() {
        setupWindow();
        setupOutput();
        setupInput();
        setupButton();
    }

    private void setupWindow() {
        window.setLayout(new BorderLayout());
        window.setSize(350,800);
        window.add(createCenter(), BorderLayout.CENTER);
        window.add(createSouth(),BorderLayout.SOUTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
    }

    private JScrollPane createCenter() {
        return new JScrollPane(output);
    }

    private JPanel createSouth() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(input,BorderLayout.CENTER);
        panel.add(submitButton,BorderLayout.EAST);
        return panel;
    }

    private void setupOutput() {
        output.setEditable(false);
        output.setBackground(new Color(255,220,220));
        output.setForeground(Color.BLUE);
        output.setFont(new Font(Font.MONOSPACED,Font.BOLD, 18));
    }

    private void setupInput() {
        input.setFont(new Font("Sansserif",Font.BOLD, 18));
        input.requestFocusInWindow();
    }

    private void setupButton() {
        submitButton.setForeground(Color.RED.darker());
        submitButton.setBackground(Color.WHITE);
    }

    public JFrame getWindow() {
        return window;
    }

    public JTextArea getOutput() {
        return output;
    }

    public JTextField getInput() {
        return input;
    }

    public JButton getButton() {
        return submitButton;
    }
}
