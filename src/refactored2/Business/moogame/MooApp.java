package refactored2.Business.moogame;

import refactored2.Business.Application;

import java.util.ArrayList;
import java.util.List;

public class MooApp implements Application {
    private static final String NAME = "Cows n Bulls";

    private String goal;
    private int points;
    private String outputMessage;
    private List<String> errors;
    private boolean isPlaying;

    public MooApp() {
        this.errors = new ArrayList<>();
    }

    @Override
    public void setup() {
        points = 0;
        isPlaying = true;
        goal = MooEngine.createGoal();
    }

    @Override
    public void execute(String command) {
        outputMessage = "";
        errors.clear();

        findErrors(command);

        if(errors.isEmpty()) {
            points++;

            if(command.equals(goal)) {
                isPlaying = false;
                outputMessage = "Congratulations! You got it in "+points+" tries!";
            } else
                outputMessage = "("+command+") "+MooEngine.calculateCowsAndBulls(command, goal);
        }
    }

    private void findErrors(String input) {
        if(input.length() < goal.length()) {
            errors.add(String.format("'%s' is too short", input));

        }
        if(input.length() > goal.length())
            errors.add(String.format("'%s' is too long", input));
        if(!input.matches("\\d\\d\\d\\d")) {
            errors.add("Guess must contain only numbers");
        }
    }



    @Override
    public String getEmptyOutputMessage() {
        return String.format("New game of %s:", NAME);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public String getOutputMessage() {
        return outputMessage;
    }

    @Override
    public String getErrors() {
        return errors.toString();
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }
}
