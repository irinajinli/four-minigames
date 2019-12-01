package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;
import com.example.game1.presentation.model.jumpinggame.TappingResult;

public class StarDisplayer extends GameItem {
  /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
  private int numStar;

  public int getNumStar() {
    return numStar;
  }

  public StarDisplayer(double xCoordinate, double yCoordinate) {
    // Call super() to set appearance, location (x, y), appearance and
    // type face.
    super(xCoordinate, yCoordinate, "You current star number: ");
    this.numStar = 0;
  }

  //  public int getNumStar() {
  //    return numStar;
  //  }

  public void setNumStar(int numStar) {
    this.numStar = numStar;
    setDescription("You current star number: " + this.numStar);
  }

  @Override
  public TappingResult update(MovementInfo tappingMovementInfo) {
    // TappingResult result = new TappingResult();
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      if (this.numStar < ((TappingMovementInfo) tappingMovementInfo).getTappingSpeed()) {
        this.numStar = ((TappingMovementInfo) tappingMovementInfo).getTappingSpeed();
      }
      setDescription("You current star number: " + this.numStar);
    }
    return new TappingResult();
  }
}
