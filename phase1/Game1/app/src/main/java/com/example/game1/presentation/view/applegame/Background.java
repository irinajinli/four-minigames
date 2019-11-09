package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.game1.presentation.view.common.GameItem;

public class Background extends GameItem {

  /** Construct a Background. */
  public Background() {
    super("");
  }

  /**
   * Move this GameItem within its GameManager. Note that this doesn't do anything because
   * Backgrounds don't need to move. Will think of a better solution for phase 2.
   */
  @Override
  public void move() {}

  /**
   * Draw this Background.
   *
   * @param canvas the canvas on which to draw
   * @param s the String to draw
   * @param x the x coordinate at which to draw
   * @param y the y coordinate at which to draw
   */
  @Override

  public void drawString(Canvas canvas, String s, int x, int y) {
    Rect backgroundRect = new Rect(0, 0, 999999, 999999);
    Paint backgroundPaint = new Paint();
    backgroundPaint.setStyle(Paint.Style.STROKE);
    backgroundPaint.setStrokeWidth(5);
    backgroundPaint.setAntiAlias(true);
    backgroundPaint.setColor(Color.DKGRAY);
    backgroundPaint.setStyle(Paint.Style.FILL);
    canvas.drawRect(backgroundRect, backgroundPaint);
  }

}
