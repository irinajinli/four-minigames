package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

public class StarDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int numStar;

  public StarDisplayer(int x, int y) {
    // Call super() to set appearance, location (x, y), appearance and
    // type face.
    super("You current star number: ");
    setPosition(x, y);
    this.numStar = 0;
  }

  public int getNumStar() {
    return numStar;
  }

  public void setNumStar(int numStar) {
    this.numStar = numStar;
    setDescription("You current star number: " + this.numStar);
  }

  @Override
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
}
