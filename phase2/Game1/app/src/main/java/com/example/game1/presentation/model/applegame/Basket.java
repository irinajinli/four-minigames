package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

public class Basket extends AnimatedGameItem {

  public Basket(int height, int width) {
    super(height, width);
  }

  /**
   * Move this Basket to the specified x coordinate.
   *
   * @param x the x coordinate to move this Basket to
   */
  public void move(int x) {
    setPosition(x, getYCoordinate());
  }

  public AppleResult update(MovementInfo jumpingMovementInfo) {
    return (new AppleResult());
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
  }
}
