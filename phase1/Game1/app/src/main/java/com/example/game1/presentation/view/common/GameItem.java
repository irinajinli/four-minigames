package com.example.game1.presentation.view.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.game1.presentation.view.tappinggame.TappingGameView;

/** An item which can be in a GameManager. */
public abstract class GameItem {
  /** This item's Paint. */
  public Paint paintText = new Paint();



  /** How this item appears on the screen. */
  private Object appearance;

  /** This item's x coordinate. */
  private int x;
  /** This item's y coordinate. */
  private int y;

  /**
   * Constructs a GameItem with the specified appearance.
   *
   * @param appearance the appearance of this GameItem
   */
  public GameItem(Object appearance) {
    this.appearance = appearance;
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
  }
  /**
   * Constructs a GameItem.
   *
   */
  public GameItem() {
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
  }


  /**
   * Sets the location of this GameItem in the specified GameManager.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Changes the location of this GameItem within its GameManager.
   *
   * @param x the new x coordinate
   * @param y the new y coordinate
   */
  public void changeLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the current x coordinate of this GameItem.
   *
   * @return the x coordinate of this GameItem
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the current y coordinate of this GameItem.
   *
   * @return the y coordinate of this GameItem
   */
  public int getY() {
    return y;
  }

  /**
   * Returns the appearance of this GameItem.
   *
   * @return the appearance of this GameItem
   */
  public Object getAppearance() {
    return appearance;
  }

  /**
   * Sets the appearance of this GameItem.
   *
   * @param appearance the appearance of this GameItem
   */
  public void setAppearance(Object appearance) {
    this.appearance = appearance;
  }

  public void setColor(int color) {
    paintText.setColor(color);
  }

  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void draw(Canvas canvas) {


    if(appearance.getClass() == String.class) {
      drawString(canvas, (String)appearance, x, y);
      //canvas.drawText((String) appearance, x * TappingGameView.charWidth, y * TappingGameView.charHeight, paintText);
    } else if (appearance.getClass() == Bitmap.class){
      canvas.drawBitmap((Bitmap) appearance, x* TappingGameView.charWidth, y* TappingGameView.charHeight, paintText);
    }
  }

  /** Move this GameItem within its GameManager. */
  public abstract void move();

  /**
   * Draw the GameItem at a location on the specified Canvas using a String.
   *
   * @param canvas the canvas on which to draw
   * @param s the String to draw
   * @param x the x coordinate at which to draw
   * @param y the y coordinate at which to draw
   */
  public void drawString(Canvas canvas, String s, int x, int y){
    canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, paintText);
  }
}
