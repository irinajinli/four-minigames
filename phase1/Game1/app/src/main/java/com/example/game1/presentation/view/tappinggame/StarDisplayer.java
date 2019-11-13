package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;

public class StarDisplayer extends GameItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int numStar;

    public StarDisplayer(int x, int y) {
        // Call super() to set appearance, location (x, y), appearance and
        // type face.
        super("You current star number: ");
        setLocation(x, y);
        this.numStar = 0;
        paintText.setColor(Color.CYAN);
    }

    public int getNumStar(){
        return numStar;
    }



    public void setNumStar(int numStar) {
        this.numStar = numStar;
        setAppearance("You current star number: " + this.numStar);
    }

    @Override
    public void move() {

    }
}
