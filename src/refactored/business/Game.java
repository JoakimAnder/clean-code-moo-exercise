package refactored.business;

import refactored.DB.models.INeedThisForSomeReason.User;
import refactored.UI.window.Window;

public interface Game {
    String getName();
    void setup(Window window, User user);
    String play();
}
