package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

public class TapCounter extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int numTaps;

  public TapCounter(int x, int y) {
    // Call super() to set appearance, location (x, yCoordinate), appearance and
    // type face.
    super("Number of Taps: ");
    setPosition(x, y);
    this.numTaps = 0;
  }

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
    setDescription("Number of Taps: " + this.numTaps);
  }

  @Override
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
}
