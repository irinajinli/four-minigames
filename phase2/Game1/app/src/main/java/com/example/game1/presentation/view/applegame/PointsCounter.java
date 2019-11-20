package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItemOld;
import com.example.game1.presentation.view.common.GameView;

/** A counter for points earned. */
public class PointsCounter extends GameItemOld {

  /** How many points have been counted. */
  private int numPoints = 0;

  /** Construct a PointsCounter with white text. */
  public PointsCounter() {
    super("0");
    paintText.setColor(Color.WHITE);
  }

  /** Move this GameItemOld within its GameManager. */
  @Override
  public void move() {
    // TODO: empty method? fix?
  }

  /**
   * Draw this PointsCounter.
   *
   * @param canvas the canvas on which to draw
   * @param s the String to draw
   * @param x the x coordinate at which to draw
   * @param y the y coordinate at which to draw
   */
  @Override
  public void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
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
    setAppearance(numPoints + ""); // concatenate to make String
  }
}
