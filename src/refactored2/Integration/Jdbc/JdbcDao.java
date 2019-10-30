package refactored2.Integration.Jdbc;



import refactored2.Integration.Exceptions.*;
import refactored2.Integration.models.Game;
import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao implements Dao {
    private static final String addUser = "INSERT INTO players (name) values (?)";
    private static final String addScore = "INSERT INTO scores (game, player, points, timestamp) VALUES (?, ?, ?, ?)";
    private static final String getScores = "SELECT * FROM scores WHERE game = ?";
    private static final String getGameByName = "SELECT * FROM games WHERE name = ?";
    private static final String getUserByName = "SELECT * FROM players WHERE name = ?";
    private static final String getGameById = "SELECT * FROM games WHERE id = ?";
    private static final String getUserById = "SELECT * FROM players WHERE id = ?";
    private static Connection connection;

    private static PreparedStatement getGameByNameStatement;
    private static PreparedStatement getUserByNameStatement;
    private static PreparedStatement addUserStatement;
    private static PreparedStatement getScoresStatement;
    private static PreparedStatement getGameByIdStatement;
    private static PreparedStatement getUserByIdStatement;
    private static PreparedStatement addScoreStatement;


    public static JdbcDao createDao() {
        if (connection == null) {
            connection = Connection.createConnection("jdbc:mysql://localhost/MooDB","root","root");

            getGameByNameStatement = connection.prepareStatement(getGameByName);
            getUserByNameStatement = connection.prepareStatement(getUserByName);
            addUserStatement = connection.prepareStatement(addUser);
            getScoresStatement = connection.prepareStatement(getScores);
            getGameByIdStatement = connection.prepareStatement(getGameById);
            getUserByIdStatement = connection.prepareStatement(getUserById);
            addScoreStatement = connection.prepareStatement(addScore);
        }
        return new JdbcDao();
    }

    @Override
    public void addScore(int gameid, int userid, int points, LocalDateTime timestamp) {
        Game game = getGame(gameid);
        User user = getUser(userid);
        addScoreStatement
                .setInt(1, game.getId())
                .setInt(2, user.getId())
                .setInt(3, points)
                .setLong(4, timestamp.toEpochSecond(ZoneOffset.UTC))
                .executeUpdate();

    }

    @Override
    public void addUser(String name) {
        try {
            getUser(name);
            throw new UserAlreadyExists();
        } catch (UserNotFound e) {
            addUserStatement.setString(1, name).executeUpdate();
        }
    }

    @Override
    public List<Score> getScores(int gameid) {
        Game game = getGame(gameid);
        ResultSet rs = getScoresStatement.setInt(1, gameid).executeQuery();
        List<Score> scores = new ArrayList<>();

        while(rs.next()) {
            Score score = JDBCConverter.toScore(rs, id -> game, id -> getUser(id));
            scores.add(score);
        }

        return scores;
    }

    @Override
    public Game getGame(String name) {
        ResultSet rs = getGameByNameStatement.setString(1, name).executeQuery();
        return getGame(rs);
    }

    @Override
    public Game getGame(int id) {
        ResultSet rs = getGameByIdStatement.setInt(1, id).executeQuery();
        return getGame(rs);
    }

    private Game getGame(ResultSet rs) {
        if(rs.next())
            return JDBCConverter.toGame(rs);
        else
            throw new GameNotFound();
    }

    @Override
    public User getUser(String name) {
        ResultSet rs = getUserByNameStatement.setString(1, name).executeQuery();
        return getUser(rs);
    }

    @Override
    public User getUser(int id) {
        ResultSet rs = getUserByIdStatement.setInt(1, id).executeQuery();
        return getUser(rs);
    }

    public User getUser(ResultSet rs) {
        if(rs.next())
            return JDBCConverter.toUser(rs);
        else
            throw new UserNotFound();
    }


}
