package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.GameView;

/** A counter for points earned. */
public class PointsCounter extends GameItem {

  /** How many points have been counted. */
  private int numPoints = 0;

  /** Constructs a PointsCounter with white text. */
  public PointsCounter() {
    super("0");
    paintText.setColor(Color.WHITE);
  }

  @Override
  public void move() {
    // TODO: empty method? fix?
  }

  /**
   * Draws this PointsCounter.
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
   * Gets numPoints.
   *
   * @return number of points
   */
  public int getNumPoints() {
    return numPoints;
  }

  /**
   * Adds the specified number of points.
   *
   * @param points number of points to add
   */
  public void addPoints(int points) {
    numPoints += points;
    setAppearance(numPoints + ""); // concatenate to make String
  }
}
