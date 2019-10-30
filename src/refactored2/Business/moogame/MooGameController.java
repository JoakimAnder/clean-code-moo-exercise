package refactored2.Business.moogame;


import refactored2.Business.Application;
import refactored2.Business.GameController;
import refactored2.Integration.DBController;
import refactored2.Integration.Jdbc.JdbcController;
import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;
import refactored2.UI.Window;

import java.util.*;

public class MooGameController implements GameController {
    private static final String EXIT_COMMAND = "q";

//    @Inject
    private DBController db = new JdbcController();
    private Window window;
    private Application application;
    private Stack<String> commands;

    private User user;
    private Game game;

    public MooGameController(Window window, Application application) {
        this.window = window;
        this.application = application;
        commands = new Stack<>();
    }

    @Override
    public void setup() {
        window.getInput().setOnSubmit(this::parseInput);
        game = db.getGame(application.getName());
    }

    private void parseInput(String input) {
        input = input.trim();
        if(input.isEmpty())
            return;
        if(input.equals(EXIT_COMMAND)) {
            if(window.getPopup().confirm("User aborted application, do you want to exit?"))
                window.exit();
        }
        commands.push(input);
        window.getInput().clear();
    }

    @Override
    public void run() {
        window.show(true);
        login();

        boolean playGame = true;

        while (playGame) {
            startGame();
            playGame = window.getPopup().confirm("Play again?");
        }

        run();
    }

    private void login() {
        user = null;
        while (user == null) {
            window.getOutput().set("Enter username.");

            User namedUser = new User();
            waitUntilNewCommand();
            String username = commands.pop();
            namedUser.setName(username);

            if (db.isUsedUserName(username)) {
                user = db.login(namedUser);
            } else {
                if (window.getPopup().confirm(String.format("User with name '%s' was not found, create a new user?", username)))
                    user = db.addUser(namedUser);
            }
        }
    }

    private void waitUntilNewCommand() {
        while (commands.empty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void startGame() {
        window.getOutput().clear();
        application.setup();

        loopGame();

        addScoreToDb();

        showTopTenList();
    }

    private void loopGame() {
        while(application.isPlaying()) {
            if(window.getOutput().isEmpty())
                window.getOutput().set(application.getEmptyOutputMessage()+"\n");

            waitUntilNewCommand();
            calculateOutput();

        }
    }

    private void calculateOutput() {
        application.execute(commands.pop());
        if(application.hasErrors())
            window.getPopup().alert(application.getErrors());
        else
            window.getOutput().printLine(application.getOutputMessage());
    }

    private void addScoreToDb() {
        Score score = new Score();
        score.setGame(game);
        score.setUser(user);
        score.setPoints(application.getPoints());

        db.addScore(score);
    }

    private void showTopTenList() {
        List<Score> scores = db.getScores(game);
        List<PlayerAverage> playerAverages = PlayerAverage.mapScoresToPlayerAverages(scores);
        printScores(playerAverages);
    }

    private void printScores(List<PlayerAverage> playerAverages) {
        window.getOutput().printLine("\tPlayer\tAverage");
        boolean userPrinted = false;
        for(PlayerAverage pa : playerAverages) {
            if(pa.getUser().equals(user)) {
                window.getOutput().printLine("__________________________");
                printScore(pa);
                window.getOutput().printLine("__________________________");
                userPrinted = true;
            } else {
                if(pa.getPosition() > 10) {
                    if (userPrinted) break;
                }
                else
                    printScore(pa);
            }
        }
    }

    private void printScore(PlayerAverage pa) {
        window.getOutput().printLine(String.format(
                "%3d %-10s%5.2f",
                pa.getPosition(),
                pa.getUser().getName(),
                pa.getAveragePoints()));
    }


}
