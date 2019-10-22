package refactored.business.moo.donotaskmewhy;

import refactored.DB.models.INeedThisForSomeReason.User;
import refactored.UI.window.Window;
import refactored.business.Game;
import refactored.business.utilities.QueueAdapter;
import refactored.business.utilities.SimpleQueue;

public class GameController implements Game {
    private static final String QUIT_GAME_COMMAND = "q";
    private static final String NAME = "Cows n' Bulls";
    private Engine engine = new Engine();
    private User user;
    private Window window;
    private SimpleQueue<String> commandQ;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void setup(Window window, User user) {
        this.window = window;
        this.user = user;
        this.commandQ = new QueueAdapter<>();
        setupInput();
        setupOutput();
    }

    private void setupInput() {
        window.input().setOnSubmit(s -> {
            if(s.trim().isEmpty())
                return;
            commandQ.push(s);
        });
    }

    private void setupOutput() {
        window.output().getAndClear();
        window.output().append(String.format("Player: %s \t Game: MooGame\n\n Press '%s' to quit game\n", user.getName(), QUIT_GAME_COMMAND));
    }

    @Override
    public String play() {
        while (engine.isPlaying()) {
            String input = commandQ.pull();
            if(input.equals(QUIT_GAME_COMMAND))
                return "User aborted game";

            String parse = engine.parse(input);
            window.output().append(parse+"\n");
        }

        return null;
    }

}
