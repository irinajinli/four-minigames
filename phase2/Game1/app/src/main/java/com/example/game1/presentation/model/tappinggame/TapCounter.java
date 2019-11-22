package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.view.common.GameItemOld;

public class TapCounter extends GameItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int numTaps;

    public TapCounter(int x, int y) {
        // Call super() to set appearance, location (x, yCoordinate), appearance and
        // type face.
        super("Number of Taps: ");
        setPosition(x, y);
        this.numTaps = 0;
        paintText.setColor(Color.CYAN);
    }

    public int getNumTaps(){
        return numTaps;
    }

    public void incrementNumTaps(){
        this.numTaps ++;
    }

    public void setNumTaps(int numTaps) {
        this.numTaps = numTaps;
        setAppearance("Number of Taps: " + this.numTaps);
    }

    @Override
    public Result update(ImportInfo jumpingImportInfo) {
        return (new Result());
    }

}
