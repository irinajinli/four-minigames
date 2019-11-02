package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Runner of the tapping game
 */
public class Runner extends TappingItem {
    /**
     * Constructs a runner at the specified cursor location (xCoordinate, yCoordinate).
     */
    public Runner(Bitmap appearance, int xCoordinate, int yCoordinate) {
        super(appearance, xCoordinate, yCoordinate);
        this.appearance = getResizedBitmap(appearance, 100, 100);
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
}
