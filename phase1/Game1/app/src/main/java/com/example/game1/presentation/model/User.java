package com.example.game1.presentation.model;

/**
 * The model object for a user.
 * This object is used by the domain and data layer.
 */
public class User {
    private String userName;
    private String password;

    /**
     * An instance of Customization, which stores the customization choices of the user
     */
    private Customization customization;

    /**
     * The statistics of the top game in this user's history
     */
    private int topPoints;
    private int topStars;
    private int topTaps;

    /**
     * The stats of the user's current game
     */
    private int currentPoints;
    private int currentStars;
    private int currentTaps;

    /**
     * The last completed level in the user's current game
     */
    private int lastCompletedLevel;

    /**
     * Constructs a new User with the given user name and password.
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        // Set default value for the user's customization choices, statistics, and last completed
        // level
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

    public void setPasssword(String password) {
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
