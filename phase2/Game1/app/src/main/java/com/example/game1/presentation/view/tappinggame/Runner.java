package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.game1.presentation.view.common.GameItemOld;

/** Runner of the tapping game */
public class Runner extends GameItemOld {



  private int speed;

  public boolean isCanRun() {
    return canRun;
  }

  public void setCanRun(boolean canRun) {
    this.canRun = canRun;
  }

  private boolean canRun;

  /** Constructs a runner at the specified cursor location (x, y). */
  public Runner(Bitmap appearance, int x, int y) {
    super();
    setLocation(x, y);
    setAppearance(getResizedBitmap(appearance, 100, 100));
    canRun = true;
  }

  /**
   * code taken from: https://stackoverflow.com/questions/4837715/how-to-resize-a-bitmap-in-android
   *
   * @param bm
   * @param newWidth
   * @param newHeight
   * @return
   */
  public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // CREATE A MATRIX FOR THE MANIPULATION
    Matrix matrix = new Matrix();
    // RESIZE THE BIT MAP
    matrix.postScale(scaleWidth, scaleHeight);

    // "RECREATE" THE NEW BITMAP
    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    bm.recycle();
    return resizedBitmap;
  }

  public void move() {}
  /**
   * Move Runner according to the speed.
   *
   * @return
   */
  public boolean move(int width) {
    if (getX() + speed / 2 < width) {
      int newX = getX() + speed / 2;
      setLocation(newX, getY());
      canRun = true;
      return canRun;
    }
    else{
      canRun = false;
        return canRun;
    }
  }


  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }
}
