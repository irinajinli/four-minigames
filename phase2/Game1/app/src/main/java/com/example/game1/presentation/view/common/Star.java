package com.example.game1.presentation.view.common;

import android.graphics.Canvas;
import android.graphics.Color;

public class Star extends GameItem {

  public Star() {
    super("*");
    paintText.setColor(Color.CYAN);
  }

  @Override
  public void move() {
    // moving forward
    int newY = getY() + 1;
    changeLocation(getX(), newY);
  }

  @Override
  public void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
  }
}
