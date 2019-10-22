package refactored.DB.jdbc;

import javafx.util.Pair;
import refactored.DB.GameDao;
import refactored.DB.GameNotFound;
import refactored.DB.models.INeedThisForSomeReason.Game;
import refactored.DB.models.INeedThisForSomeReason.Score;
import refactored.DB.models.INeedThisForSomeReason.User;

import java.util.List;
import java.util.stream.Collectors;


public class JDBCGameDao implements GameDao {

    public static void main(String[] args) {
        GameDao dao = new JDBCGameDao();
        Game game = dao.getGameById(1);
        System.out.println(game);
        System.out.println(game.getScores());
    }
    private Connection connection;
    private static PreparedStatement getGameByName;
    private static PreparedStatement getGameById;
    private static PreparedStatement createGame;
    private static PreparedStatement updateGame;
    private static PreparedStatement gameExistsByName;
    private static PreparedStatement gameExistsById;

    private static PreparedStatement getScores;
    private static PreparedStatement addScore;
    private static PreparedStatement removeScore;


    public JDBCGameDao() {
        connection = Connection.createConnection("jdbc:mysql://localhost/MooDB","root","root");

        gameExistsByName = connection.prepareStatement("SELECT COUNT(*) as count FROM games WHERE name = ?");
        gameExistsById = connection.prepareStatement("SELECT COUNT(*) as count FROM games WHERE id = ?");
        getGameByName = connection.prepareStatement("SELECT * FROM games WHERE name = ?");
        getGameById = connection.prepareStatement("SELECT * FROM games WHERE id = ?");
        createGame = connection.prepareStatement("INSERT INTO games (name) VALUES (?)");
        updateGame = connection.prepareStatement("UPDATE games SET name = ? WHERE id = ?");


        getScores = connection.prepareStatement("SELECT s.id as scoreID, s.score, p.name, p.id FROM scores s, players p WHERE s.game = ? AND s.player = p.id");
        addScore = connection.prepareStatement("INSERT INTO scores (game, player, score) VALUES (?, ?, ?)");
        removeScore = connection.prepareStatement("DELETE FROM scores WHERE id = ? ");
    }

    String validateName(String name) {
        String nameCopy = name;

        for (int i = 1; gameExists(nameCopy); i++) {
            nameCopy = name+i;
        }

        return nameCopy;
    }

    @Override
    public Game getGameByName(String name) {
        ResultSet rs = getGameByName
                .setString(1, name)
                .executeQuery();
        if(!rs.next())
            throw new GameNotFound();
        Game game = JDBCConverter.toGame(rs);

        rs = getScores.setInt(1, game.getId()).executeQuery();
        game.setScores(JDBCConverter.toScores(rs));

        return game;
    }

    @Override
    public Game getGameById(int id) {
        ResultSet rs = getGameById
                .setInt(1, id)
                .executeQuery();
        if(!rs.next())
            throw new GameNotFound();
        Game game = JDBCConverter.toGame(rs);

        rs = getScores.setInt(1, game.getId()).executeQuery();
        game.setScores(JDBCConverter.toScores(rs));

        return game;
    }

    @Override
    public Game createGame(Game game) {
        String name = validateName(game.getName());
        createGame
                .setString(1, name)
                .executeUpdate();

        return getGameByName(game.getName());
    }

    @Override
    public Game updateGame(Game newGame) {
        Game oldGame = getGameById(newGame.getId());

        if(newGame.equals(oldGame))
            return newGame;

        if(!newGame.getName().equals(oldGame.getName())) {
            String name = validateName(newGame.getName());
            updateGame.setString(1, name)
                    .setInt(2, newGame.getId())
                    .executeUpdate();
        }

        if(newGame.getScores() != null) {
            List<Score> newScores = newGame.getScores();
            List<Score> oldScores = oldGame.getScores();
            List<Score> scoresToRemove = oldScores.stream().filter(newScores::contains).collect(Collectors.toList());
            List<Score> scoresToAdd = newScores.stream().filter(oldScores::contains).collect(Collectors.toList());

            for (Score s : scoresToAdd) {
                addScore.setInt(1, newGame.getId())
                        .setInt(2, s.getUser().getId())
                        .setInt(3, s.getScore())
                        .executeUpdate();
            }
            for (Score s : scoresToRemove) {
                removeScore.setInt(1, s.getId())
                        .executeUpdate();
            }
        }

        return getGameById(newGame.getId());
    }

    @Override
    public boolean gameExists(String name) {
        ResultSet rs = gameExistsByName.setString(1, name).executeQuery();
        return rs.getInt("count") > 0;
    }

    @Override
    public boolean gameExists(int id) {
        ResultSet rs = gameExistsById.setInt(1, id).executeQuery();
        return rs.getInt("count") > 0;
    }
}
