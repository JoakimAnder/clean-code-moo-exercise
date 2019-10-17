package refactored;

import refactored.UI.window.SwingWindow;
import refactored.UI.window.Window;
import refactored.game.Game;
import refactored.game.MooGame;
import refactored.menu.GameMenu;
import refactored.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Main application = new Main();
        application.start();
    }

    private Window window = SwingWindow.createWindow();
    private Menu menu = new GameMenu(window);
    Game game = MooGame.createGame();
//    Dao dao;

    int userID;

    void start() {
        login();
        runApp();
        window.quit();
    }

    void login() {
        String userName = window.popup().input("Enter your user name:");
//        userID = dao.getUserIdByName(username);
    }

    void runApp() {
        while(true) {
            game = menu.selectGame();
            if(game == Menu.NO_SELECTED)
                break;
            runGame();
        }
    }

    void runGame() {
        boolean play = true;
        while (play) {
            game.setup();
            String message = game.play();
            play = window.popup().confirm(message+"\nContinue?");
        }
    }




}
