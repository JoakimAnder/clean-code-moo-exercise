package refactored2.Business.moogame;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MooEngine {
    private static final int INPUT_LENGTH = 4;
    private static final int NUMBER_CEILING = 10;
    private static final int NUMBER_FLOOR = 0;
    private static final boolean CAN_NOT_CONTAIN_DUPLICATES = true;

    private String input;
    private String goal;

    private MooEngine(String input, String goal) {
        this.input = input;
        this.goal = goal;
    }

    public static String createGoal() {
        validateGoalParameters();

        List<Integer> goalComponents = new ArrayList<>(INPUT_LENGTH);
        for (int i = 0; i < INPUT_LENGTH; i++) {
            int randomNum = randomNumberBetween(NUMBER_FLOOR, NUMBER_CEILING);
            if(CAN_NOT_CONTAIN_DUPLICATES) {
                while (goalComponents.contains(randomNum)) {
                    randomNum = randomNumberBetween(NUMBER_FLOOR, NUMBER_CEILING);
                }
            }
            goalComponents.add(randomNum);
        }
        return goalComponents.stream()
                .map(Object::toString)
                .reduce("", String::concat);
    }

    private static void validateGoalParameters() {
        if(CAN_NOT_CONTAIN_DUPLICATES && INPUT_LENGTH > Math.abs(NUMBER_FLOOR - NUMBER_CEILING))
            throw new RuntimeException("Goal is not valid: INPUT_LENGTH is too long");
    }

    private static int randomNumberBetween(int floor, int ceiling) {
        return (int) (Math.random() * (ceiling - floor)) + floor;
    }

    public static String calculateCowsAndBulls(String input, String goal) {
        MooEngine engine = new MooEngine(input, goal);
        return engine.calculate();
    }

    private String calculate() {
        String cows = "";
        String bulls = "";

        boolean[] bullMarks = markBulls();
        String cutGoal = getGoalWithoutBulls(bullMarks);

        for (int i = 0; i < goal.length(); i++) {
            if(bullMarks[i])
                bulls = bulls.concat("B");
            else {
                char c = input.charAt(i);
                int index = cutGoal.indexOf(c);
                if(index != -1) {
                    cows = cows.concat("C");
                    cutGoal = cutGoal.replace(c+"", "");
                }
            }
        }

        return beautify(bulls, cows);
    }

    boolean[] markBulls() {
        boolean[] bulls = new boolean[goal.length()];
        for (int i = 0; i < goal.length(); i++) {
            bulls[i] = goal.charAt(i) == input.charAt(i);
        }
        return bulls;
    }

    String getGoalWithoutBulls(boolean[] bulls) {
        StringBuilder cutGoal = new StringBuilder();

        for (int i = 0; i < bulls.length; i++) {
            if(!bulls[i])
                cutGoal.append(goal.charAt(i));
        }
        return cutGoal.toString();
    }

    String beautify(String... strings) {
        return Stream.of(strings)
                .reduce((s1, s2) -> s1 + "," + s2)
                .orElse("");
    }
}
