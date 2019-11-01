package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.game1.presentation.view.common.GameItem;

public class Background extends GameItem {

  public Background() {
    super("");
  }

  @Override
  public void move() {}

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
