package com.example.game1.presentation.model;

public class User {
  private String userName;
  private String password;
  private Customization customization;
  private int totalPoints;
  private int totalStars;
  private int totalTaps;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    this.customization = new Customization();
    totalPoints = 0;
    totalStars = 0;
    totalTaps = 0;
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

  public int getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(int totalPoints) {
    this.totalPoints = totalPoints;
  }

  public int getTotalStars() {
    return totalStars;
  }

  public void setTotalStars(int totalStars) {
    this.totalStars = totalStars;
  }

  public int getTotalTaps() {
    return totalTaps;
  }

  public void setTotalTaps(int totalTaps) {
    this.totalTaps = totalTaps;
  }
}
