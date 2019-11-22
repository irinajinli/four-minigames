package com.example.game1.presentation.model.tappinggame;

import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItemOld;

public class StarDisplayer extends GameItemOld {
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
