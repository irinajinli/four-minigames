package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

/** Runner of the tapping game */
public class Runner extends AnimatedGameItem {



  private int speed;

  public boolean isCanRun() {
    return canRun;
  }

  public void setCanRun(boolean canRun) {
    this.canRun = canRun;
  }

  private boolean canRun;

  /**
   * Constructs a Jumper with the specified height, width, and appearance.
   *
   * @param xCoordinate xCoordinate of the GameItem
   * @param yCoordinate yCoordinate of the GameItem
   */
  public Runner(double xCoordinate, double yCoordinate) {
    super(xCoordinate, yCoordinate);
  }

  /** Constructs a runner at the specified cursor location (x, y). */
  /**public Runner(List appearances, int x, int y) {
    super(x, y, appearances);
    setPosition(x, y);
    setAppearance(appearance);
    canRun = true;
  }*/



  public void move() {}
  /**
   * Move Runner according to the speed.
   *
   * @return
   */
  public boolean move(int width) {

    if ((getXCoordinate() + speed)  < (width - 100)) {
      double newX = getXCoordinate() + speed ;
      setPosition(newX, getYCoordinate());
      canRun = true;
      if (speed > 0){
        //advanceFrame();
      }
      return canRun;
    }
    else{
      canRun = false;
      return canRun;
    }
  }


  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
  public Result animate(MovementInfo movementInfo){return new Result();}
}
