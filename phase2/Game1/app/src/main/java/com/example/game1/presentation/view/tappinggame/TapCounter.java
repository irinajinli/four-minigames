package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItemOld;

public class TapCounter extends GameItemOld {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int numTaps;

    public TapCounter(int x, int y) {
        // Call super() to set appearance, location (x, yCoordinate), appearance and
        // type face.
        super("Number of Taps: ");
        setLocation(x, y);
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

    public void move(){}
}
