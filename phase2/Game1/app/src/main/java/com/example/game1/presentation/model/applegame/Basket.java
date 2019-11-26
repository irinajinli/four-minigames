package com.example.game1.presentation.model.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.List;

public class Basket extends AnimatedGameItem {

  /** Construct a light gray basket. */
  public Basket() {
    super("|___|");
    paintText.setColor(Color.LTGRAY);
  }

  public Basket(int height, int width, List appearances) {
    super(height, width, appearances);
  }

//  /**
//   * Draw this Basket.
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

  /** Move this GameItemOld within its GameManager. */
  @Override
  public void move() {
    // do nothing!
    // this move method shouldn't be used
    // figure out a better way to do this
  }

  /**
   * Move this Basket to the specified x coordinate.
   *
   * @param x the x coordinate to move this Basket to
   */
  public void move(int x) {
    setPosition(x, getYCoordinate());
  }

  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
  public Result animate(MovementInfo movementInfo){return new Result();}

}
