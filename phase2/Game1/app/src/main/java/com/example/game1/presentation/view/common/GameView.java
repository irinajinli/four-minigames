package com.example.game1.presentation.view.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

import java.util.List;

/** The fish tank view. */
public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

  // TODO: make variables private where possible

  /** The width of a character. */
  public static float charWidth;
  /** The height of a character. */
  public static float charHeight;
  /** The fish tank contents. */
  public GameManager gameManager;
  /** The part of the program that manages time. */
  public GameThread thread;
  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  /** The activity class corresponding this view */
  public AppCompatActivity activity;
  /** This item's Paint. */
  public Paint paintText = new Paint();

  /**
   * Create a new fish tank in the context environment.
   *
   * @param context the environment.
   */
  public GameView(Context context) {
    super(context);
    this.activity = (AppCompatActivity) context;
    getHolder().addCallback(this);
    setFocusable(true);
  }

  public static float getCharWidth() {
    return charWidth;
  }

  public static float getCharHeight() {
    return charHeight;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  @Override
  public abstract void surfaceCreated(SurfaceHolder holder);

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();
        gameManager.stopMusic();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update this GameView's GameManager. */
  public void update() {
    boolean updated = gameManager.update();
    // stop thread if update fails
    if (!updated) {
      thread.setRunning(false);
    }
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      if (gameManager instanceof JumpingGameManager){
        canvas.drawColor(((JumpingGameManager) gameManager).getSkyColor());}
      // gameManager.draw(canvas);
      List<GameItem> items = gameManager.getGameItems();
      for (GameItem item : items) {

        drawItem(canvas, item);
      }
    }
  }

  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item) {

    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    Object appearance = item.getAppearance();
    double xCoordinate = item.getxCoordinate();
    double yCoordinate = item.getyCoordinate();
    if (appearance.getClass() == String.class) {

      canvas.drawText(
              (String) appearance,
              (float) xCoordinate * GameView.charWidth,
              (float) yCoordinate * GameView.charHeight,
              paintText);

      // canvas.drawText((String) appearance, x * TappingGameView.charWidth, y *
      // TappingGameView.charHeight, paintText);
    } else if (appearance.getClass() == Bitmap.class) {
      canvas.drawBitmap(
              (Bitmap) appearance,
              (int) Math.round(xCoordinate),
              (int) Math.round(yCoordinate),
              paintText);
    }
  }
//  }
}
