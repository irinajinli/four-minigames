package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

/**
 * A StarDisplayer
 */
public class StarDisplayer extends GameItem {
  /** number of Star Earned*/
  private int numStar;

  /**
   * Construct a StarDisplayer according to specified xCoordinate, yCoordinate
   * @param xCoordinate
   * @param yCoordinate
   */
  public StarDisplayer(double xCoordinate, double yCoordinate) {
    super(xCoordinate, yCoordinate, "You current star number: ");
    this.numStar = 0;
  }

  /**
   * Get the number of star in this StarDisplayer
   * @return the number of star in this StarDisplayer
   */
  public int getNumStar() {
    return numStar;
  }

  @Override
  /**
   * Perform update based on the information given by the movement info
   *
   * @param tappingMovementInfo
   * @return result needed by the tapping game manager.
   */
  public Result update(MovementInfo tappingMovementInfo) {
    if (tappingMovementInfo instanceof TappingMovementInfo) {
      if (this.numStar < ((TappingMovementInfo) tappingMovementInfo).getTappingSpeed()) {
        this.numStar = ((TappingMovementInfo) tappingMovementInfo).getTappingSpeed();
      }
      setDescription("You current star number: " + this.numStar);
    }
    return new Result();
  }
}
