package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;

/** A Basket */
public class Basket extends AnimatedGameItem {

  /**
   * Construct a basket with specifed width and height
   *
   * @param width
   * @param height
   */
  public Basket(int width, int height) {
    super(height, width);
  }

  /**
   * Move this Basket to the specified x coordinate.
   *
   * @param xCoordinate the x coordinate to move this Basket to
   */
  public void move(int xCoordinate) {
    setXCoordinate(xCoordinate);
  }
}
