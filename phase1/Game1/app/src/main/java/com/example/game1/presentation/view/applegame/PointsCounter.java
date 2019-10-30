package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.GameView;

public class PointsCounter extends GameItem {

  private int numPoints = 0;

  public PointsCounter() {
    super("0");
    paintText.setColor(Color.WHITE);
  }

  @Override
  public void move() {
    // TODO: empty method? fix?
  }

  @Override
  public void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
  }

  public int getNumPoints() {
    return numPoints;
  }

  public void addPoints(int points) {
    numPoints += points;
    setAppearance(numPoints + ""); // concatenate to make String
  }
}
