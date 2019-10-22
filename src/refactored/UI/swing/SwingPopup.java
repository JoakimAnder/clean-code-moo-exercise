package refactored.UI.swing;


import refactored.UI.popup.Popup;

import javax.swing.*;

public class SwingPopup implements Popup {
    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public boolean confirm(String message) {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, message);
    }

    @Override
    public String input(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
}
