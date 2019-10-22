package refactored.DB.models.INeedThisForSomeReason;


import java.util.List;

public class Game {

    private int id;
    private String name;
    private List<Score> scores;

    public Game() {
    }

    public Game(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        return scores != null ? scores.equals(game.scores) : game.scores == null;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
