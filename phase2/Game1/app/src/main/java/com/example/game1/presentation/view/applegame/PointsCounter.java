package com.example.game1.presentation.view.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;

/** A counter for points earned. */
public class PointsCounter extends GameItem {

  /** How many points have been counted. */
  private int numPoints = 0;

  /** Construct a PointsCounter with white text. */
  public PointsCounter() {
    super("apples: 0");
    paintText.setColor(Color.WHITE);
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

  public Result update(ImportInfo jumpingImportInfo) {
    return (new Result());
  }

  public Result animate(ImportInfo importInfo) {
    return new Result();
  }
}
