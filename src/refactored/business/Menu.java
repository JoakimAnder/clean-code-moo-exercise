package refactored.business;

import refactored.UI.window.Window;

public interface Menu<T> {
    Object NO_SELECTED = null;

    T select(Window window);
}
