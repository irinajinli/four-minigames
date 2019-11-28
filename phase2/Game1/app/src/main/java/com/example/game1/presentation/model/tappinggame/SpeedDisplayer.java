package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

public class SpeedDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int speed;

  public SpeedDisplayer(int x, int y) {
    // Call super() to set appearance, location (x, y), appearance and
    // type face.
    super("Your average tapping speed: ");
    setPosition(x, y);
    this.speed = 0;
    paintText.setColor(Color.CYAN);
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
    setAppearance("Your average tapping speed: " + this.speed);
  }

  @Override
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
}
