package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItemOld;

public class TimerDisplayer extends GameItemOld {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int secondsLeft;

    public TimerDisplayer(int x, int y) {
        // Call super() to set appearance, location (x, y), appearance and
        // type face.
        super("Your seconds left: 1000");
        setLocation(x, y);
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
    public void move() {

    }

    //    @Override
//    public  animate(){
//
//    }
}
