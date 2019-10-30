package refactored2.UI;

import java.util.function.Consumer;

public interface Input {
    String clear();
    void setOnSubmit(Consumer<String> onSubmit);
}
