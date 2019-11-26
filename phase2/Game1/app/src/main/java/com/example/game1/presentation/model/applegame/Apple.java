package com.example.game1.presentation.model.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.List;

public class Apple extends AnimatedGameItem {

  /** Construct a red apple. */
  public Apple() {
    super("( )");
    paintText.setColor(Color.RED);
  }

  public Apple(int width, int height, List appearances) {
    super(width, height, appearances);
  }

  /** Move this GameItemOld within its GameManager. */
  @Override
  public void move() {
    double newY = getYCoordinate() + 15;
    setPosition(getXCoordinate(), newY);
  }

  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
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
