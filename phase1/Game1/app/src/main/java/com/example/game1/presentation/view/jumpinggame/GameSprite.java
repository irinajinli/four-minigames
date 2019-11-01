package com.example.game1.presentation.view.jumpinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

public class GameSprite {
  private Bitmap image;
  private GameObject gameObject;
  GameView gameView;

  // delete this once debugged
  public GameSprite(){

  }
  public GameSprite(Bitmap image, GameObject gameObject, GameView gameView) {
    this.image = getResizedBitmap(image, gameObject.getWidth(), gameObject.getHeight());
    this.gameObject = gameObject;
    this.gameView = gameView;
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

  public void draw(Canvas canvas) {
    canvas.drawBitmap(image, (int)gameObject.getPositionX(), (int)gameObject.getPositionY(), null);
  }
}
