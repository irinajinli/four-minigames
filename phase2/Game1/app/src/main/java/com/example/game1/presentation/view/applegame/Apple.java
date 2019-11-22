package com.example.game1.presentation.view.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;
import com.example.game1.presentation.view.common.GameItemOld;

public class Apple extends AnimatedGameItem {

  /** Construct a red apple. */
  public Apple() {
    super("( )");
    paintText.setColor(Color.RED);
  }

  /** Move this GameItemOld within its GameManager. */
  @Override
  public void move() {
    double newY = getyCoordinate() + 1;
    // TODO: later, decide what to do when apple goes off screen/gets to bottom
    setPosition(getxCoordinate(), getyCoordinate() + 1);
  }

  public Result update(ImportInfo jumpingImportInfo) {
    return (new Result());
  }
  public Result animate(ImportInfo importInfo){return new Result();}

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
