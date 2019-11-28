package com.example.game1.presentation.model;

/** The model object for a game. */
public class Game {

  /** The possible game types */
  public enum GameName {APPLE, TAPPING, JUMPING, BRICK}

  /** The levels that each game type corresponds to */
  private static final int APPLE_LEVEL = 1;
  private static final int TAPPING_LEVEL = 2;
  private static final int JUMPING_LEVEL = 3;
  private static final int BRICK_LEVEL = 4;

  private GameName name;
  private int level;
  private Statistics statistics;
  private Customization customization;

  /**
   * Constructs a new Game.
   */
  public Game(GameName name) {
    this.name = name;
    setLevel();
    // Set default values for the game's statistics and customization
    statistics = new Statistics();
    customization = new Customization();
  }

  /** Sets the game's level according to its type */
  private void setLevel() {
    switch (name) {
      case APPLE:
        setLevel(APPLE_LEVEL);
        break;
      case TAPPING:
        setLevel(TAPPING_LEVEL);
        break;
      case JUMPING:
        setLevel(JUMPING_LEVEL);
        break;
      case BRICK:
        setLevel(BRICK_LEVEL);
        break;
      default:
        setLevel(0);
    }
  }

  public int getLevel() {
    return level;
  }

  private void setLevel(int level) {
    this.level = level;
  }

  public Statistics getStatistics() {
    return statistics;
  }

  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

  public Customization getCustomization() {
    return customization;
  }

  public void setCustomization(Customization customization) {
    this.customization = customization;
  }
}
