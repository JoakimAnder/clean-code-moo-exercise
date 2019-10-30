package refactored2.Integration.Jdbc;


import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Function;

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

    public static Score toScore(ResultSet rs, Function<Integer, Game> getGame, Function<Integer, User> getUser) {
        int id = rs.getInt("id");
        int gameid = rs.getInt("game");
        int userid = rs.getInt("player");
        int points = rs.getInt("points");
        long epochSeconds = rs.getLong("timeStamp");

        Game game = getGame.apply(gameid);
        User user = getUser.apply(userid);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);

        Score score = new Score();
        score.setId(id);
        score.setGame(game);
        score.setUser(user);
        score.setPoints(points);
        score.setDateTime(dateTime);

        return score;
    }
}
