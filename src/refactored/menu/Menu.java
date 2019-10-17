package refactored.menu;

import refactored.game.Game;

public interface Menu {
    Game NO_SELECTED = null;

    Game selectGame();
}
