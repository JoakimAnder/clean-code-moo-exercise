package refactored.business.select;

import refactored.UI.window.Window;
import refactored.business.Game;
import refactored.business.Menu;
import refactored.business.moo.donotaskmewhy.GameController;

public class GameMenu implements Menu<Game> {

    @Override
    public Game select(Window window) {
        String chosenGame = window.popup().input("Choose a Game:");

        switch (chosenGame) {
            case "1": return new GameController();
            default: return (Game) Menu.NO_SELECTED;
        }
    }
}
