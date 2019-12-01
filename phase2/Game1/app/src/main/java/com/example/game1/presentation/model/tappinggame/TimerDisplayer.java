package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

public class TimerDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int secondsLeft;

  public TimerDisplayer(double xCoordinate, double yCoordinate) {
    // Call super() to set appearance, location (x, y)
    super(xCoordinate, yCoordinate, "Your seconds left: 10");
//    setPosition(xCoordinate, yCoordinate);
    this.secondsLeft = 10;
  }

  public int getSecondsLeft() {
    return secondsLeft;
  }

  public void setSecondsLeft(int secondsLeft) {
    this.secondsLeft = secondsLeft;
    setDescription("Your seconds left: " + this.secondsLeft);
  }

  @Override
  public Result update(MovementInfo tappingMovementInfo) {
    //TappingResult result = new TappingResult();
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      this.secondsLeft = ((TappingMovementInfo) tappingMovementInfo).getSecondsLeft();
      setDescription("Your seconds left: " + this.secondsLeft);
    }
    return new Result();
  }
}
