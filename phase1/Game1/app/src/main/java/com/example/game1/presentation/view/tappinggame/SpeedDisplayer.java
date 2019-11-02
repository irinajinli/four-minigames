package com.example.game1.presentation.view.tappinggame;

import android.graphics.Color;

public class SpeedDisplayer extends TappingItem {
    /** construct a Tap Counter at the specified cursor location (xCoordinate, yCoordinate). */
    private int speed;

    public SpeedDisplayer(int xCoordinate, int yCoordinate) {
        // Call super() to set appearance, location (xCoordinate, yCoordinate), appearance and
        // type face.
        super("Your average tapping speed: ", xCoordinate, yCoordinate);
        this.speed = 0;

        paintText.setColor(Color.CYAN);
    }

    public int getSpeed(){
        return speed;
    }



    public void setSpeed(int speed) {
        this.speed = speed;
        this.appearance = "Your average tapping speed: " + this.speed;
    }
}
