package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.view.common.GameView;

public class ImageGameItem {
    protected Bitmap image;
    protected GameView gameView;
    protected int height;
    protected int width;
    protected int xCoordinate;
    protected int yCoordinate;

    ImageGameItem(Bitmap imageBMP, int width, int height, int xCoordinate, int yCoordinate) {
        this.width = width;
        this.height = height;
        this.image = getResizedBitmap(imageBMP, width, height);
        //this.gameView = gameView;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
        canvas.drawBitmap(image, (int) xCoordinate, (int) yCoordinate, null);
    }

}
