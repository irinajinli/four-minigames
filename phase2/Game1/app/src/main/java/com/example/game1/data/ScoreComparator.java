package com.example.game1.data;

import com.example.game1.presentation.model.User;

import java.util.Comparator;

public class ScoreComparator implements Comparator<User> {
    /**
     * Compares its two arguments for order.
     * <p>
     * Returns a negative integer, zero, or a positive integer
     * as user1 is less than, equal to, or greater than user2 in terms
     * of the top score in their history.
     *
     * @param user1 the first User to compare
     * @param user2 the second User to compare
     * @return a negative integer, zero, or a positive integer
     * as user1 is less than, equal to, or greater than user2
     */
    @Override
    public int compare(User user1, User user2) {
        int score1 = calculateScore(user1.getStatsOfTopGame().getPoints(),
                user1.getStatsOfTopGame().getStars(),
                user1.getStatsOfTopGame().getTaps());
        int score2 = calculateScore(user2.getStatsOfTopGame().getPoints(),
                user2.getStatsOfTopGame().getStars(),
                user2.getStatsOfTopGame().getTaps());
        return score1 - score2;
    }

    /**
     * Returns the score calculated from the given number of points, stars, and taps.
     */
    private int calculateScore(int points, int stars, int taps) {
        return points + stars + taps;
    }
}