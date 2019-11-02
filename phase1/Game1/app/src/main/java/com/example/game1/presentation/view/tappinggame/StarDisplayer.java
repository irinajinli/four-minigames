package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

public class StarDisplayer extends TappingItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int numStar;

    public StarDisplayer(int xCoordinate, int yCoordinate) {
        // Call super() to set appearance, location (xCoordinate, yCoordinate), appearance and
        // type face.
        super("You current star number: ", xCoordinate, yCoordinate);
        this.numStar = 0;

        paintText.setColor(Color.CYAN);
    }

    public int getNumStar(){
        return numStar;
    }



    public void setNumStar(int numStar) {
        this.numStar = numStar;
        this.appearance = "You current star number: " + this.numStar;
    }
}
