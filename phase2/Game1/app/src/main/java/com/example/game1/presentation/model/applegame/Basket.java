package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.List;

public class Basket extends AnimatedGameItem {

  public Basket(int height, int width, List appearances) {
    super(height, width, appearances);
  }

  /**
   * Move this Basket to the specified x coordinate.
   *
   * @param x the x coordinate to move this Basket to
   */
  public void move(int x) {
    setPosition(x, getYCoordinate());
  }

  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
  }
}
