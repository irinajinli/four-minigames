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
    public TappingCircle(Object appearance, int x, int y) {
        // Call super() to set appearance, location (x, y), appearance and
        // type face.
        super(appearance);
        setPosition(x, y);
        setAppearance(appearance);
    }

    @Override
    public Result update(ImportInfo jumpingImportInfo) {
        return (new Result());
    }
    //public Result animate(ImportInfo importInfo){return new Result();}
}
