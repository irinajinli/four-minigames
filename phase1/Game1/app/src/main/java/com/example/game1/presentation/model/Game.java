package com.example.game1.presentation.model;


/**
 * The model object for a game.
 * This object will be used by the domain layer and maps to the data objects in the data layer.
 */
public class Game {

  public enum GameName {APPLE, TAPPING, JUMPING}

  private static final int APPLE_LEVEL = 1;
  private static final int TAPPING_LEVEL = 2;
  private static final int JUMPING_LEVEL = 3;

  private GameName name;
  private int level;
  private int numPoints;
  private int numStars;
  private int numTaps;
  private Customization customization;

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
  }

  private void setLevel() {
    switch(name) {
      case APPLE:
        setLevel(APPLE_LEVEL);
        break;
      case TAPPING:
        setLevel(TAPPING_LEVEL);
        break;
      case JUMPING:
        setLevel(JUMPING_LEVEL);
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

  private void setLevel(int level) {
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
}
