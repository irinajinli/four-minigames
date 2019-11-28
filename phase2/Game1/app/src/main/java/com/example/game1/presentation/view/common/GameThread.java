package com.example.game1.presentation.view.common;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
  public static final long TARGET_FPS = 30;
  public static final long FRAME_DURATION_NS = (long) (Math.pow(10, 9) / TARGET_FPS);
  private SurfaceHolder surfaceHolder;
  private GameView gameView;
  private boolean isRunning;
  private Canvas canvas;
  // public static final long FRAME_DURATION_NS = 300 * 1000000;

  public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.gameView = gameView;
  }

  @Override
  public void run() {
    canvas = null;
    long startTime, frameStartTime, delay;
    int frameCount = 0;
    startTime = System.nanoTime();
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
        System.out.println("FPS: " + TARGET_FPS / ((System.nanoTime() - startTime) / 1000000000));
        frameCount = 0;
        startTime = System.nanoTime();
      }

      try {
        // Thread.sleep((FRAME_DURATION_NS/1000000));
        // frameCount += 1;
        // System.out.println("@@@@@ Frame Count" + frameCount);
      } catch (Exception e) {
      }
    }
  }

  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }
}
