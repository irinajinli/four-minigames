package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;
import com.example.game1.presentation.model.jumpinggame.TappingResult;

public class TapCounter extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int numTaps;

  public TapCounter(double xCoordinate, double yCoordinate) {
    // Call super() to set appearance, location (x, yCoordinate), appearance and
    // type face.
    super(xCoordinate, yCoordinate, "Number of Taps: ");
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
  public TappingResult update(MovementInfo tappingMovementInfo) {
    //TappingResult result = new TappingResult();
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      this.numTaps = ((TappingMovementInfo) tappingMovementInfo).getNumTaps();
      setDescription("Number of Taps: " + this.numTaps);
    }
    return new TappingResult();
  }

}
