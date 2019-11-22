package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.view.common.GameItemOld;

public class TimerDisplayer extends GameItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int secondsLeft;

    public TimerDisplayer(int x, int y) {
        // Call super() to set appearance, location (x, y), appearance and
        // type face.
        super("Your seconds left: 1000");
        setPosition(x, y);
        this.secondsLeft = 10;
        paintText.setColor(Color.CYAN);
    }

    public int getSecondsLeft(){
        return secondsLeft;
    }



    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setAppearance("Your seconds left: " + this.secondsLeft);
    }

    @Override

    public Result update(ImportInfo jumpingImportInfo) {
        return (new Result());
    }

    //    @Override
//    public  animate(){
//
//    }
}
