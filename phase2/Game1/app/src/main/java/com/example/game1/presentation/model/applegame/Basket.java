package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;

/** A Basket */
public class Basket extends AnimatedGameItem {

  /**
   * Construct a basket with specifed width and height
   *
   * @param height height of this basket
   * @param width width of this basket
   */
  public Basket(int height, int width) {
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
