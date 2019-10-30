package refactored2;


import refactored2.Business.Application;
import refactored2.Business.GameController;
import refactored2.Business.moogame.MooApp;
import refactored2.Business.moogame.MooGameController;
import refactored2.UI.Swing.SwingWindow;
import refactored2.UI.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new SwingWindow();
        Application app = new MooApp();
        GameController controller = new MooGameController(window, app);
        controller.setup();
        controller.run();
    }

}
