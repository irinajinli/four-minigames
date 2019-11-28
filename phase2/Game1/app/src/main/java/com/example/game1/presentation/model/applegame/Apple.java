package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.List;

public class Apple extends AnimatedGameItem {

  public Apple(int width, int height, List appearances) {
    super(width, height, appearances);
  }

  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
  }

  public void animate(double numSeconds) {
    updatePositionAndVelocity(numSeconds);
  }
}
