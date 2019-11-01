package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.game1.presentation.view.common.GameView;

/** Hacky way to manage threading and updates. Based on Fish Tank's MainThread. */
public class MainThread extends Thread {

  /** Whether the thread is running. */
  public static boolean isRunning;

  /** The canvas on which to draw the fish tank. */
  public static Canvas canvas;
  /** Where the fish tank items are drawn. */
  private GameView gameView;
  /** The canvas container. */
  private SurfaceHolder surfaceHolder;

  /**
   * Construct the thread.
   *
   * @param surfaceHolder the canvas container.
   * @param view where the fish tank items are drawn.
   */
  public MainThread(SurfaceHolder surfaceHolder, GameView view) {
    this.surfaceHolder = surfaceHolder;
    this.gameView = view;
  }

  @Override
  public void run() {
    while (isRunning) {
      canvas = null;

      try {
        canvas = this.surfaceHolder.lockCanvas();
        synchronized (surfaceHolder) {
          this.gameView.update();
          this.gameView.draw(canvas);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (canvas != null) {
          try {
            surfaceHolder.unlockCanvasAndPost(canvas);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      try {
        sleep(300);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void setRunning(boolean isRunning) {
    MainThread.isRunning = isRunning;
  }
}
