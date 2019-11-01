package com.example.game1.presentation.view.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;

public class TimeCounter extends GameItem {
    /** How many points have been counted. */
    private int secondLeft = 10;

    /** Constructs a PointsCounter with white text. */
    public TimeCounter() {
        super("10");
        paintText.setColor(Color.BLUE);
    }

    @Override
    public void move() {
        // TODO: empty method? fix?
    }

    /**
     * Draws this TimeCounter.
     *
     * @param canvas the canvas on which to draw
     * @param s the String to draw
     * @param x the x coordinate at which to draw
     * @param y the y coordinate at which to draw
     */
    @Override
    public void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x , y, super.paintText);
    }
    /**
     * Gets secondLeft.
     *
     * @return secondLeft
     */
    public int getSecondLeft() {
        return secondLeft;
    }

    /**
     * Set new seconds left
     *
     * @param secondLeft new second left
     */
    public void setSecond(int secondLeft) {
        this.secondLeft = secondLeft;
        setAppearance("Seconds Left: " + this.secondLeft + ""); // concatenate to make String
    }
}
