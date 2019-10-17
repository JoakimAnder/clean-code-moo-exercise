package refactored.UI.textField;

import javax.swing.text.JTextComponent;
import java.util.concurrent.BlockingQueue;

public class SwingTextField implements TextField {
    JTextComponent textComponent;
    private BlockingQueue<String> mq;

    public SwingTextField(JTextComponent textComponent) {
        this.textComponent = textComponent;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getAndClear() {
        return null;
    }

    @Override
    public void append(String text) {

    }
}
