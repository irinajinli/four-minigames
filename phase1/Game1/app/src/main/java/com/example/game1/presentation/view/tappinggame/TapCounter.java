package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

public class TapCounter extends TappingItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int numTaps;

    public TapCounter(int xCoordinate, int yCoordinate) {
        // Call super() to set appearance, location (xCoordinate, yCoordinate), appearance and
        // type face.
        super("Number of Taps: ", xCoordinate, yCoordinate);
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
        this.appearance = "Number of Taps: " + this.numTaps;
    }
}
