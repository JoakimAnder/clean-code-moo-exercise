package refactored.UI.textField;

import java.util.function.Consumer;

public interface TextField {

    void setOnSubmit(Consumer<String> onSubmit);

    String getText();
    String getAndClear();
    void append(String text);

}
