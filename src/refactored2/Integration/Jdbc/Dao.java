package refactored2.Integration.Jdbc;

import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface Dao {
    void addUser(String name);
    void addScore(int gameid, int userid, int points, LocalDateTime timestamp);
    List<Score> getScores(int gameid);
    Game getGame(String name);
    Game getGame(int id);
    User getUser(String name);
    User getUser(int id);
}
