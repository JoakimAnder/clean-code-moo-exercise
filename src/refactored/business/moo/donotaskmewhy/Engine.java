package refactored.business.moo.donotaskmewhy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Engine {
    private static final int INPUT_LENGTH = 4;
    private static final int NUMBER_CEILING = 10;
    private static final int NUMBER_FLOOR = 0;
    private static final boolean CAN_NOT_CONTAIN_DUPLICATES = true;

    private boolean isPlaying = false;
    private int guesses;
    private String goal;

    private String output;
    private String input;

    public void setup() {
        isPlaying = true;
        guesses = 0;
        makeGoal();
    }

    private void makeGoal() {
        validateGoal();
        this.goal = "";

        List<Integer> goal = new ArrayList<>(INPUT_LENGTH);
        for (int i = 0; i < INPUT_LENGTH; i++) {
            int randomNum = randomNumberBetween(NUMBER_FLOOR, NUMBER_CEILING);
            if(CAN_NOT_CONTAIN_DUPLICATES) {
                while (goal.contains(randomNum)) {
                    randomNum = randomNumberBetween(NUMBER_FLOOR, NUMBER_CEILING);
                }
            }
            goal.add(randomNum);
        }
        this.goal = goal.stream()
                .map(Object::toString)
                .reduce(this.goal, String::concat);
    }

    void validateGoal() {
        if(CAN_NOT_CONTAIN_DUPLICATES && INPUT_LENGTH > Math.abs(NUMBER_FLOOR - NUMBER_CEILING))
            throw new RuntimeException("Goal is not valid: INPUT_LENGTH is too long");
    }

    int randomNumberBetween(int floor, int ceiling) {
        return (int) (Math.random() * (ceiling - floor)) + floor;
    }

    String parse(String rawInput) {
        input = rawInput.trim().toLowerCase();
        output = "";

        if(input.equals(goal))
            isPlaying = false;
        calculateOutput();
        return output;
    }

    void calculateOutput() {
        editOutputIfInvalid();
        if(output.isEmpty()) {
            guesses++;
            output = calculateCowsAndBulls();
        }
    }

    void editOutputIfInvalid() {
        if(input.length() < INPUT_LENGTH)
            output = String.format("'%s' is too short", input);
        if(input.length() > INPUT_LENGTH)
            output = String.format("'%s' is too long", input);
        if(!input.matches("dddd")) {
            output = "Guess must contain only numbers";
        }
    }

    String calculateCowsAndBulls() {
        String bulls = "";
        String cows = "";

        boolean[] bullMarks = markBulls();
        String cutGoal = getGoalWithoutBulls(bullMarks);
        for (int i = 0; i < INPUT_LENGTH; i++) {
            if(bullMarks[i])
                bulls.concat("B");
            else {
                char c = input.charAt(i);
                int index = cutGoal.indexOf(c);
                if(index != -1) {
                    cows.concat("C");
                    cutGoal = cutGoal.replace(c+"", "");
                }
            }
        }

        return beautify(bulls, cows);
    }

    boolean[] markBulls() {
        boolean[] bulls = new boolean[INPUT_LENGTH];
        for (int i = 0; i < INPUT_LENGTH; i++) {
            bulls[i] = goal.charAt(i) == input.charAt(i);
        }
        return bulls;
    }

    String getGoalWithoutBulls(boolean[] bulls) {
        StringBuilder cutGoal = new StringBuilder();

        for (int i = 0; i < bulls.length; i++) {
            if(bulls[i])
                cutGoal.append(goal.charAt(i));
        }

        return cutGoal.toString();
    }

    String beautify(String... strings) {
        return Stream.of(strings)
                .reduce((s1, s2) -> s1 + "," + s2)
                .orElse("");
    }

    boolean isPlaying() {
        return isPlaying;
    }
}
