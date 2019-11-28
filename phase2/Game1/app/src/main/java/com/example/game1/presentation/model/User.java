package com.example.game1.presentation.model;

/** The model object for a user. This object is also used by the domain and data layer. */
public class User {

  private String userName;
  private String password;

  /** This user's customization choices */
  private Customization customization;

  /** The highest individual statistics in this user's history */
  private Statistics topIndividualStats;

  /** The statistics of the top game in this user's history (i.e. the game with the highest score */
  private Statistics statsOfTopGame;

  /** The statistics of this user's current game */
  private Statistics statsOfCurrentGame;

  /** The last completed level in this user's current game */
  private int lastCompletedLevel;

  /**
   * Constructs a new User with the given username and password.
   */
  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    // Set default values for the user's customization choices, statistics, and last completed level
    customization = new Customization();
    statsOfCurrentGame = new Statistics();
    statsOfTopGame = new Statistics();
    topIndividualStats = new Statistics();
    lastCompletedLevel = 0;
  }

  public String getUserName() {
    return userName;
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

  public Statistics getStatsOfCurrentGame() {
    return statsOfCurrentGame;
  }

  public Statistics getStatsOfTopGame() {
    return statsOfTopGame;
  }

  public Statistics getTopIndividualStats() {
    return topIndividualStats;
  }

  public int getLastCompletedLevel() {
    return lastCompletedLevel;
  }

  public void setLastCompletedLevel(int lastCompletedLevel) {
    this.lastCompletedLevel = lastCompletedLevel;
  }
}
