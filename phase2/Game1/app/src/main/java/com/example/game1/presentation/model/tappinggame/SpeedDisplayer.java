package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;
import com.example.game1.presentation.model.jumpinggame.TappingResult;

public class SpeedDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int tappingSpeed;

  public SpeedDisplayer(double xCoordinate, double yCoordinate) {
    // Call super() to set location (x, y)
    super(xCoordinate, yCoordinate, "Your average tapping speed: ");
    this.tappingSpeed = 0;
  }

//  public int getSpeed() {
//    return tappingSpeed;
//  }

//  public void setSpeed(int speed) {
//    this.tappingSpeed = speed;
//    setDescription("Your average tapping speed: " + this.tappingSpeed);
//  }

  @Override
  public TappingResult update(MovementInfo tappingMovementInfo) {
    //TappingResult result = new TappingResult();
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      this.tappingSpeed = ((TappingMovementInfo) tappingMovementInfo).getTappingSpeed();
      setDescription("Your average tapping speed: " + this.tappingSpeed);
    }
    return new TappingResult();
  }
}
