package com.example.game1.presentation.view.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;

public class Star extends AnimatedGameItem {

  public Star() {
    super("*");
    paintText.setColor(Color.CYAN);
  }

  public Star(int height, int width, Object appearance) {
    super(height, width, appearance);
  }

  @Override
  public void move() {
    // moving forward
    double newY = getYCoordinate() + 15;
    setPosition(getXCoordinate(), newY);
  }

  //  @Override
  //  public void drawString(Canvas canvas, String s, int x, int y) {
  //    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
  //  }

  public Result update(ImportInfo jumpingImportInfo) {
    return (new Result());
  }

  public Result animate(ImportInfo importInfo) {
    return new Result();
  }
}
