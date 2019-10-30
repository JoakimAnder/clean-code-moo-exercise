package refactored2.Business.moogame;

import refactored2.Integration.models.Score;
import refactored2.Integration.models.User;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerAverage {
    private User user;
    private int position;
    private double averagePoints;

    private PlayerAverage(User user, double averagePoints) {
        this.user = user;
        this.averagePoints = averagePoints;
    }

    public static List<PlayerAverage> mapScoresToPlayerAverages(List<Score> scores) {
        Map<User, List<Integer>> playerPoints = bunchPlayersWithTheirPoints(scores);
        Map<User, Double> averageScores = calculateTheirAverages(playerPoints);
        List<PlayerAverage> playerAverages = mapToConvenientClass(averageScores);
        return givePositions(playerAverages);
    }

    private static Map<User, List<Integer>> bunchPlayersWithTheirPoints(List<Score> scores) {
        Map<User, List<Integer>> playerPoints = new HashMap<>();
        for (Score score : scores) {
            playerPoints.putIfAbsent(score.getUser(), new ArrayList<>());
            playerPoints.get(score.getUser()).add(score.getPoints());
        }
        return playerPoints;
    }

    private static Map<User, Double> calculateTheirAverages(Map<User, List<Integer>> playerPoints) {
        Map<User, Double> averageScores = new HashMap<>();
        for (User key : playerPoints.keySet()) {
            List<Integer> points = playerPoints.get(key);
            double totalScore = points.stream().reduce(0, Integer::sum);
            double averageScore = totalScore / points.size();
            averageScores.put(key, averageScore);
        }
        return averageScores;
    }

    private static List<PlayerAverage> mapToConvenientClass(Map<User, Double> averageScores) {
        List<PlayerAverage> playerAverages = averageScores.entrySet().stream()
                .map(entry -> new PlayerAverage(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return playerAverages;
    }

    private static List<PlayerAverage> givePositions(List<PlayerAverage> playerAverages) {
        playerAverages.sort(Comparator.comparingDouble(PlayerAverage::getAveragePoints));
        PlayerAverage lastPa = null;
        for (int i = 0; i < playerAverages.size(); i++) {
            PlayerAverage pa = playerAverages.get(i);
            if (lastPa != null && lastPa.getAveragePoints() == pa.getAveragePoints())
                pa.setPosition(lastPa.getPosition());
            else
                pa.setPosition(i+1);
            lastPa = pa;
        }

        return playerAverages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }
}
