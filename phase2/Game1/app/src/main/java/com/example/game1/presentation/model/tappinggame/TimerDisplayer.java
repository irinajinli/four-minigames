package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;


public class TimerDisplayer extends GameItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int secondsLeft;

    public TimerDisplayer(int x, int y) {
        // Call super() to set appearance, location (x, y)
        super("Your seconds left: 10");
        setPosition(x, y);
        this.secondsLeft = 10;

    }

    public int getSecondsLeft(){
        return secondsLeft;
    }



    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        setAppearance("Your seconds left: " + this.secondsLeft);
    }

    @Override

    public Result update(MovementInfo jumpingMovementInfo) {
        return (new Result());
    }


}
