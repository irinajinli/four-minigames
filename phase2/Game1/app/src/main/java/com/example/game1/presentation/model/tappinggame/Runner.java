package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;
import com.example.game1.presentation.model.jumpinggame.TappingResult;

/** Runner of the tapping game */
public class Runner extends AnimatedGameItem {

//  // private int speed;
//  private boolean canRun;

  /**
   * Constructs a Jumper with the specified height, width, and appearance.
   *
   * @param xCoordinate xCoordinate of the GameItem
   * @param yCoordinate yCoordinate of the GameItem
   */
  public Runner(double xCoordinate, double yCoordinate, int width, int height) {

    super(xCoordinate, yCoordinate, width, height);
  }

//  public boolean isCanRun() {
//    return canRun;
//  }
//
//  public void setCanRun(boolean canRun) {
//    this.canRun = canRun;
//  }

  /** Constructs a runner at the specified cursor location (x, y). */

  /**
   * public Runner(List appearances, int x, int y) { super(x, y, appearances); setPosition(x, y);
   * setDescription(appearance); canRun = true; }
   */
  public void move() {}

  /**
   * Move Runner according to the speed.
   *
   * @return
   */
  public TappingResult update(MovementInfo tappingMovementInfo) {
    TappingResult result = new TappingResult();
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      setXVelocity(((TappingMovementInfo) tappingMovementInfo).getTappingSpeed());
      if ((getXCoordinate() + getXVelocity())
          < (tappingMovementInfo.getScreenWidth() - getWidth())) {
        double newX = getXCoordinate() + getXVelocity();
        setPosition(newX, getYCoordinate());
        result.setCanRun(true);
      } else {
        result.setCanRun(true);
      }

    }
    return result;
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
  }
}
