package refactored.game;

import java.util.ArrayList;
import java.util.List;

public class MooGame implements Game {
    private static final int INPUT_LENGTH = 4;
    Integer[] goal;


    public static Game createGame() {
        return new MooGame();
    }

    @Override
    public void setup() {
        makeGoal();
    }

    private void makeGoal() {
        List<Integer> goal = new ArrayList<>();
        for (int i = 0; i < INPUT_LENGTH; i++) {
            int randomNum = (int) (Math.random() * 10);
            while (goal.contains(randomNum)) {
                randomNum = (int) (Math.random() * 10);
            }
            goal.add(randomNum);
        }
        this.goal = goal.toArray(new Integer[INPUT_LENGTH]);
    }

    @Override
    public String play() {

        return null;
    }
}
