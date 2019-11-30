package com.example.game1.presentation.model.common;

/** An item which can be in a GameManager. */
public abstract class GameItem {

  /** This item's Paint. */
//  public Paint paintText = new Paint();
  /** Description of this item. */
  private String description;
  /** the width of this item */
  private int width;
  /** the height of this item */
  private int height;
  /** This item's position: x coordinate. */
  private double xCoordinate;

  // variables to be deleted
  /** This item's position: y coordinate. */
  private double yCoordinate;

  /**
   * Constructs a GameItemOld with the specified description.
   *
   * @param description the description of this GameItemOld
   */
  public GameItem(String description) {
    this.description = description;
  }

  /**
   * Constructs a GameItem with the specified coordinates
   *
   * @param xCoordinate xCoordinate of the GameItem
   * @param yCoordinate yCoordinate of the GameItem
   */
  public GameItem(double xCoordinate, double yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  /**
   * /** Constructs a GameItem with the specified description.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public GameItem(int height, int width) {
    this.height = height;
    this.width = width;
  }

  /**
   * Constructs a GameItem with the specified description, height, width.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param description the description of this GameItem
   */
  public GameItem(int height, int width, String description) {
    this.description = description;
    this.height = height;
    this.width = width;
  }

  /**
   * Get the description of this GameItem.
   *
   * @return the description of this GameItem
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of this GameItem.
   *
   * @param description the description of this GameItem
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the position of this GameItem.
   *
   * @param xCoordinate the x coordinate
   * @param yCoordinate the y coordinate
   */
  public void setPosition(double xCoordinate, double yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  /**
   * Get the xCoordinate of this GameItem
   *
   * @return the xCoordinate of this GameItem
   */
  public double getXCoordinate() {
    return this.xCoordinate;
  }

  /**
   * Set the xCoordinate of this GameItem
   *
   * @param xCoordinate the x coordinate
   */
  public void setXCoordinate(double xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  /**
   * Get the yCoordinate of this GameItem
   *
   * @return the yCoordinate of this GameItem
   */
  public double getYCoordinate() {
    return this.yCoordinate;
  }

  /**
   * Set the yCoordinate of this GameItem
   *
   * @param yCoordinate the y coordinate
   */
  public void setYCoordinate(double yCoordinate) {
    this.yCoordinate = yCoordinate;
  }

  /**
   * Get the width of this GameItem
   *
   * @return the width of this GameItem
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height of this GameItem
   *
   * @return the height of this GameItem
   */
  public int getHeight() {
    return this.height;
  }

  public boolean isOverlapping(GameItem other) {
    double thisItemLeftBoundary = this.xCoordinate;
    double thisItemRightBoundary = this.xCoordinate + this.width;
    double otherItemLeftBoundary = other.getXCoordinate();
    double otherItemRightBoundary = other.getXCoordinate() + other.getWidth();

    double thisItemLowerBoundary = this.yCoordinate + this.height;
    double thisItemUpperBoundary = this.yCoordinate;
    double otherItemLowerBoundary = other.getYCoordinate() + other.getHeight();
    double otherItemUpperBoundary = other.getYCoordinate();

    return !(thisItemRightBoundary < otherItemLeftBoundary
        || otherItemRightBoundary < thisItemLeftBoundary
        || thisItemLowerBoundary < otherItemUpperBoundary
        || otherItemLowerBoundary < thisItemUpperBoundary);
  }

  public abstract Result update(MovementInfo movementInfo);
}
