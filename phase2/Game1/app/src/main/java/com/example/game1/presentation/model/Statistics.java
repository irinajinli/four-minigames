package com.example.game1.presentation.model;

/**
 * The model object for the statistics of a game
 */
public class Statistics {
    private int points;
    private int stars;
    private int taps;

    Statistics() {
        points = 0;
        stars = 0;
        taps = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getTaps() {
        return taps;
    }

    public void setTaps(int taps) {
        this.taps = taps;
    }
}
