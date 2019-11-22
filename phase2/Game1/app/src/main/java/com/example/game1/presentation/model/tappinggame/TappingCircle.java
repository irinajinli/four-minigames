package com.example.game1.presentation.model.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.view.common.GameItemOld;

/** a tapping circle */
public class TappingCircle extends GameItem {
    /** construct a tapping circle at the specified cursor location (x, y). */
    public TappingCircle(Bitmap appearance, int x, int y) {
        // Call super() to set appearance, location (x, y), appearance and
        // type face.
        super(appearance);
        setPosition(x, y);
        setAppearance(getResizedBitmap(appearance, 1000, 1000));
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
    @Override
    public Result update(ImportInfo jumpingImportInfo) {
        return (new Result());
    }
    //public Result animate(ImportInfo importInfo){return new Result();}
}
