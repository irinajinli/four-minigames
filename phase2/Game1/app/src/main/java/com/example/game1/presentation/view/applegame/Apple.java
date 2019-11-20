package com.example.game1.presentation.view.applegame;

import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItemOld;

public class Apple extends GameItemOld {

  /** Construct a red apple. */
  public Apple() {
    super("( )");
    paintText.setColor(Color.RED);
  }

  /** Move this GameItemOld within its GameManager. */
  @Override
  public void move() {
    int newY = getY() + 1;
    // TODO: later, decide what to do when apple goes off screen/gets to bottom
    changeLocation(getX(), getY() + 1);
  }

//  /**
//   * Draw this Apple.
//   *
//   * @param canvas the canvas on which to draw
//   * @param s the String to draw
//   * @param x the x coordinate at which to draw
//   * @param y the y coordinate at which to draw
//   */
//  @Override
//  public void drawString(Canvas canvas, String s, int x, int y) {
//    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
//  }
}
