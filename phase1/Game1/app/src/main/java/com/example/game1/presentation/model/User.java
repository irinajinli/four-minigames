package com.example.game1.presentation.model;

public class User {
    private String userName;
    private String password;
    private Customization customization;

    /**
     * The stats of the top game in this user's entire history (top points + stars)
     */
    private int topPoints;
    private int topStars;
    private int topTaps;

    /**
     * The stats of the current game
     */
    private int currentPoints;
    private int currentStars;
    private int currentTaps;

    private int lastCompletedLevel;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.customization = new Customization();
        topPoints = 0;
        topStars = 0;
        topTaps = 0;
        currentPoints = 0;
        currentStars = 0;
        currentTaps = 0;
        lastCompletedLevel = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customization getCustomization() {
        return customization;
    }

    public void setCustomization(Customization customization) {
        this.customization = customization;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getCurrentStars() {
        return currentStars;
    }

    public void setCurrentStars(int currentStars) {
        this.currentStars = currentStars;
    }

    public int getCurrentTaps() {
        return currentTaps;
    }

    public void setCurrentTaps(int currentTaps) {
        this.currentTaps = currentTaps;
    }

    public int getLastCompletedLevel() {
        return lastCompletedLevel;
    }

    public void setLastCompletedLevel(int lastCompletedLevel) {
        this.lastCompletedLevel = lastCompletedLevel;
    }

    public int getTopPoints() {
        return topPoints;
    }

    public void setTopPoints(int topPoints) {
        this.topPoints = topPoints;
    }

    public int getTopStars() {
        return topStars;
    }

    public void setTopStars(int topStars) {
        this.topStars = topStars;
    }

    public int getTopTaps() {
        return topTaps;
    }

    public void setTopTaps(int topTaps) {
        this.topTaps = topTaps;
    }
}
