package com.example.game1.presentation.model;

import com.example.game1.presentation.model.Customization;

/**
 * The model object for a game.
 * This object will be used by the domain layer and maps to the data objects in the data layer.
 */
public class Game {

  public enum GameName {APPLE, TAPPING, JUMPING}

  private GameName name;
  private int level;
  private int numPoints;
  private int numStars;
  private int numTaps;
  private Customization customization;
  private boolean finished;

  /**
   * Create a new game with default customization choice.
   */
  public Game(GameName name) {
    this.name = name;
    setLevel();
    numPoints = 0;
    numStars = 0;
    numTaps = 0;
    this.customization = new Customization();
    finished = false;
  }

  private void setLevel() {
    switch(name) {
      case APPLE:
        setLevel(1);
        break;
      case JUMPING:
        setLevel(2);
        break;
      case TAPPING:
        setLevel(3);
        break;
      default:
      setLevel(0);
    }
  }

  public GameName getName() {
    return name;
  }

  public void setName(GameName name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getNumPoints() {
    return numPoints;
  }

  public void setNumPoints(int numPoints) {
    this.numPoints = numPoints;
  }

  public int getNumStars() {
    return numStars;
  }

  public void setNumStars(int numStars) {
    this.numStars = numStars;
  }

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }

  public Customization getCustomization() {
    return customization;
  }

  public void setCustomization(Customization customization) {
    this.customization = customization;
  }

  public boolean finished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }
}
