package refactored2.Integration.models;

import java.time.LocalDateTime;

public class Score {
    private int id;
    private Game game;
    private User user;
    private int points;
    private LocalDateTime dateTime;

    public Score() {
        dateTime = LocalDateTime.now();
    }

    public Score(Game game, User user, int points) {
        this.game = game;
        this.user = user;
        this.points = points;
        dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
