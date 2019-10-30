package refactored2.Integration;

import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;

import java.util.List;

public interface DBController {
    boolean isUsedGameName(String name);
    boolean isUsedUserName(String name);
    User addUser(User newUser);
    User login(User user);
    Game getGame(String name);
    void addScore(Score score);
    List<Score> getScores(Game game);
}
