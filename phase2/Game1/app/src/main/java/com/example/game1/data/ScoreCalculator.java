package com.example.game1.data;

class ScoreCalculator {
    /**
     * Returns the score calculated from the given number of points, stars, and taps.
     */
    static int calculateScore(int points, int stars, int taps) {
        return points + stars + taps;
    }
}
