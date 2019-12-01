package com.example.game1.presentation.view.common;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/** Manages the thread for a minigame */
public class GameThread extends Thread {
  private static final long TARGET_FPS = 30;
  /** The duration in nanoseconds of a single game frame */
  public static final long FRAME_DURATION_NS = (long) (Math.pow(10, 9) / TARGET_FPS);
  private SurfaceHolder surfaceHolder;
  private GameView gameView;
  private boolean isRunning;
  private Canvas canvas;
  // private final long FRAME_DURATION_NS = 300 * 1000000;

  /**
   * Constructs the thread for a new minigame
   *
   * @param surfaceHolder the holder holding the canvas for the game
   * @param gameView the game view on which the game is running
   */
  public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.gameView = gameView;
  }

  @Override
  /** Runs this game thread. */
  public void run() {
    canvas = null;
    // long startTime;
    long frameStartTime, delay;
    int frameCount = 0;
    // startTime = System.nanoTime();
    while (isRunning) {
      canvas = null;
      frameStartTime = System.nanoTime();
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

      delay = Math.max(0, frameStartTime + FRAME_DURATION_NS - System.nanoTime());
      // System.out.println("delay " + delay/1000000);
      try {
        Thread.sleep(delay / 1000000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      frameCount += 1;
      if (frameCount == TARGET_FPS) {
        // System.out.println("FPS: " + TARGET_FPS / ((System.nanoTime() - startTime) /
        // 1000000000));
        frameCount = 0;
        // startTime = System.nanoTime();
      }

      try {
        // Thread.sleep((FRAME_DURATION_NS/1000000));
        // frameCount += 1;
        // System.out.println("@@@@@ Frame Count" + frameCount);
      } catch (Exception e) {
      }
    }
  }

  /**
   * Sets whether or not this thread is running
   *
   * @param isRunning sets the thread to running iff isRunning is true
   */
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }
}
