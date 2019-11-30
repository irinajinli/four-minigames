package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.applegame.AppleResult;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

/** A counter for points earned. */
public class PointsCounter extends GameItem {

  /** How many points have been counted. */
  private int numPoints = 0;

  /** Construct a PointsCounter with white text. */
  public PointsCounter() {
    super("apples: 0");
  }

  /**
   * Get numPoints.
   *
   * @return number of points
   */
  public int getNumPoints() {
    return numPoints;
  }

  /**
   * Add the specified number of points to this PointsCounter.
   *
   * @param points number of points to add
   */
  public void addPoints(int points) {
    numPoints += points;
    setAppearance("apples: " + numPoints);
  }

  public AppleResult update(MovementInfo jumpingMovementInfo) {
    return (new AppleResult());
  }

//  public Result animate(MovementInfo movementInfo) {
//    return new Result();
//  }
}
