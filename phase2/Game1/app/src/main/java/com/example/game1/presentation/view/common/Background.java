package com.example.game1.presentation.view.common;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

public class Background extends GameItem {

  /** Construct a Background. */
  public Background() {
    super("");
  }

  //  /**
  //   * Draw this Background.
  //   *
  //   * @param canvas the canvas on which to draw
  //   * @param s the String to draw
  //   * @param x the x coordinate at which to draw
  //   * @param y the y coordinate at which to draw
  //   */
  //  @Override
  //
  //  public void drawString(Canvas canvas, String s, int x, int y) {
  //    Rect backgroundRect = new Rect(0, 0, 999999, 999999);
  //    Paint backgroundPaint = new Paint();
  //    backgroundPaint.setStyle(Paint.Style.STROKE);
  //    backgroundPaint.setStrokeWidth(5);
  //    backgroundPaint.setAntiAlias(true);
  //    backgroundPaint.setColor(Color.DKGRAY);
  //    backgroundPaint.setStyle(Paint.Style.FILL);
  //    canvas.drawRect(backgroundRect, backgroundPaint);
  //  }
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
  // public Result animate(MovementInfo importInfo){return new Result();}

}
