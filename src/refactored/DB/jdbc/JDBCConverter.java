package refactored.DB.jdbc;

import javafx.util.Pair;
import refactored.DB.models.INeedThisForSomeReason.Game;
import refactored.DB.models.INeedThisForSomeReason.Score;
import refactored.DB.models.INeedThisForSomeReason.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConverter {

    public static User toUser(ResultSet rs) {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        User user = new User();
        user.setId(id);
        user.setName(name);

        return user;
    }

    public static Game toGame(ResultSet rs) {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        Game game = new Game();
        game.setId(id);
        game.setName(name);

        return game;
    }

    public static List<Score> toScores(ResultSet rs) {
        List<Score> scores = new ArrayList<>();
        Map<Integer, User> users = new HashMap<>();

        while(rs.next()) {
            User user = toUser(rs);
            int id = user.getId();
            Integer score = rs.getInt("score");

            if(users.containsKey(id)) {
                user = users.get(id);
            } else {
                users.put(id, user);
            }


            scores.add(new Score(user, score));
        }

        return scores;
    }
}
