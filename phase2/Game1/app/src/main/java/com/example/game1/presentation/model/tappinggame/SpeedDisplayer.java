package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

public class SpeedDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int speed;

  public SpeedDisplayer(int x, int y) {
    // Call super() to set location (x, y)
    super("Your average tapping speed: ");
    setPosition(x, y);
    this.speed = 0;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
    setDescription("Your average tapping speed: " + this.speed);
  }

  @Override
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
}
