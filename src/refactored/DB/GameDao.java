package refactored.DB;


import refactored.DB.models.INeedThisForSomeReason.Game;

public interface GameDao {
    Game getGameByName(String name);
    Game getGameById(int id);
    Game createGame(Game game);
    Game updateGame(Game game);
    boolean gameExists(String name);
    boolean gameExists(int id);
}
