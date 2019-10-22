package refactored;

import refactored.DB.models.INeedThisForSomeReason.User;
import refactored.UI.swing.SwingWindow;
import refactored.UI.window.Window;
import refactored.business.Game;
import refactored.business.select.GameMenu;
import refactored.business.select.LoginMenu;
import refactored.business.Menu;

public class Main {
    public static void main(String[] args) {
        Main application = new Main();
        application.start();
    }

    private Window window = SwingWindow.createWindow();
    private GameMenu gameMenu = new GameMenu();
    private LoginMenu loginMenu = new LoginMenu();
    Game game;
    User user;

//    Dao dao = new JDBCDao();

    void start() {
        window.show(true);
        login();
        window.quit();
    }

    void login() {
        user = loginMenu.select(window);
        if (user == Menu.NO_SELECTED)
            return;
        selectGame();
    }

    void selectGame() {
        game = gameMenu.select(window);
        if (game == Menu.NO_SELECTED)
            login();
        runGame();
    }

    void runGame() {
        boolean play = true;
        while (play) {
            game.setup(window, user);
            String message = game.play();
            play = window.popup().confirm(message+"\nContinue?");
        }
        selectGame();
    }




}
