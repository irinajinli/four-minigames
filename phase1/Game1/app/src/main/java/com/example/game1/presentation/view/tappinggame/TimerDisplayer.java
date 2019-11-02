package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

public class TimerDisplayer extends TappingItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int secondsLeft;

    public TimerDisplayer(int xCoordinate, int yCoordinate) {
        // Call super() to set appearance, location (xCoordinate, yCoordinate), appearance and
        // type face.
        super("Your seconds left: " + 10, xCoordinate, yCoordinate);
        this.secondsLeft = 10;

        paintText.setColor(Color.CYAN);
    }

    public int getSecondsLeft(){
        return secondsLeft;
    }



    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        this.appearance = "Your seconds left: " + this.secondsLeft;
    }
}
