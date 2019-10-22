package refactored.DB.models.INeedThisForSomeReason;

import java.time.LocalDateTime;

public class Score {
    private int id;
    private int score;
    private User user;
    private LocalDateTime timeOfSubmit;

    public Score(User user, Integer score) {
        this.user = user;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeOfSubmit() {
        return timeOfSubmit;
    }

    public void setTimeOfSubmit(LocalDateTime timeOfSubmit) {
        this.timeOfSubmit = timeOfSubmit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        if (id != score1.id) return false;
        if (score != score1.score) return false;
        if (!user.equals(score1.user)) return false;
        return timeOfSubmit.equals(score1.timeOfSubmit);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
