package refactored2.UI.Swing;

import refactored2.UI.Popup;

import javax.swing.*;

public class SwingPopup implements Popup {
    private JFrame parent;
    public SwingPopup(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void alert(String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    @Override
    public boolean confirm(String message) {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(parent, message);
    }

    @Override
    public String input(String message) {
        return JOptionPane.showInputDialog(parent, message);
    }

}
