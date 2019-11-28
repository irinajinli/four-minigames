package com.example.game1.presentation.model.common;

import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.List;

public abstract class AnimatedGameItem extends GameItem {
  /** This item's velocity for x coordinate. */
  private double xVelocity;
  /** This item's velocity for y coordinate. */
  private double yVelocity;
  /** This item's acceleration for x coordinate. */
  private double xAcceleration;
  /** This item's acceleration for y coordinate. */
  private double yAcceleration;
  /** This item's customization info passed by view. */
  // private Object appearance;
  /** The current frame this object's appearance is at */
  private int currentFrame = 0;
  /** The various appearances of this object's animation (one per frame) */
  private List appearances;
  /**
   * Constructs a AnimatedGameItem with the specified height and width.
   *
   * @param xCoordinate xCoordinate of the GameItem
   * @param yCoordinate yCoordinate of the GameItem
   */
  protected AnimatedGameItem(double xCoordinate, double yCoordinate) {
    super(xCoordinate, yCoordinate);
  }

  /**
   * Constructs a AnimatedGameItem with the specified height and width.
   *
   * @param appearance the appearance of this GameItem
   */
  protected AnimatedGameItem(Object appearance) {
    super(appearance);
  }

  /**
   * Constructs a AnimatedGameItem with the specified height and width.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearances the appearances of this GameItem
   */
  protected AnimatedGameItem(int height, int width, List appearances) {
    super(height, width, appearances.get(0));
    this.appearances = appearances;
  }

  /**
   * Constructs a AnimatedGameItem with the specified height and width.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  protected AnimatedGameItem(int height, int width) {
    super(height, width);
  }

  /** Advances the current appearance of the item so that it moves onto the next "frame" */
  public void advanceFrame() {
    currentFrame += 1;
    if (currentFrame >= appearances.size()) {
      currentFrame = 0;
    }
    setAppearance(appearances.get(currentFrame));
  }

  /** @return velocity of this GameItem at x axis direction */
  public double getXVelocity() {
    return this.xVelocity;
  }

  /** @param xVelocity velocity at x axis direction */
  public void setXVelocity(double xVelocity) {

    this.xVelocity = xVelocity;
  }

  /** @return velocity of this GameItem at y axis direction */
  public double getYVelocity() {
    return yVelocity;
  }

  /** @param yVelocity velocity at y axis direction */
  public void setYVelocity(double yVelocity) {

    this.yVelocity = yVelocity;
  }

  /** @return acceleration of this GameItem at x axis direction */
  public double getXAcceleration() {
    return xAcceleration;
  }

  /** @param xAcceleration acceleration at x axis direction */
  public void setXAcceleration(double xAcceleration) {

    this.xAcceleration = xAcceleration;
  }

  /** @return acceleration of this GameItem at y axis direction */
  public double getYAcceleration() {
    return yAcceleration;
  }

  /** @param yAcceleration acceleration at y axis direction */
  public void setYAcceleration(double yAcceleration) {

    this.yAcceleration = yAcceleration;
  }

  /**
   * @param numOfSeconds number of seconds used to refresh new xCoordinate, yCoordinate, xVelocity,
   *     yVelocity based on current xAcceleration and yAcceleration. Currently numOfSeconds =
   *     GameThread.FRAME_DURATION_NS / 1000000000.
   */
  public void updatePositionAndVelocity(double numOfSeconds) {
    double newXCoordinate =
        getXCoordinate()
            + (0.5 * getXAcceleration() * numOfSeconds * numOfSeconds
                + getXVelocity() * numOfSeconds);
    setXCoordinate(newXCoordinate);

    double newYCoordinate =
        getYCoordinate()
            + (0.5 * getYAcceleration() * numOfSeconds * numOfSeconds
                + getYVelocity() * numOfSeconds);
    setYCoordinate(newYCoordinate);

    double newXVelocity = getXVelocity() + getXAcceleration() * numOfSeconds;
    setXVelocity(newXVelocity);

    double newYVelocity = getYVelocity() + getYAcceleration() * numOfSeconds;
    setYVelocity(newYVelocity);
    // @TODO maybe this call can be moved elsewhere
    advanceFrame();
  }

  public abstract Result animate(MovementInfo movementInfo);
}
