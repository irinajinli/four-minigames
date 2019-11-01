package com.example.game1.presentation.view.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.GameView;

public class TapCounter extends GameItem {
    /** How many points have been counted. */
    private int numTaps = 0;

    /** Constructs a PointsCounter with white text. */
    public TapCounter() {
        super("0");
        paintText.setColor(Color.BLACK);
    }

    @Override
    public void move() {
        // TODO: empty method? fix?
    }

    /**
     * Draws this TapCounter.
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
     * Gets numPoints.
     *
     * @return number of points
     */
    public int getNumPoints() {
        return numTaps;
    }

    /**yes
     * Adds the specified number of points.
     *
     * @param Taps number of points to add
     */
    public void addPoints(int Taps) {
        numTaps += Taps;
        setAppearance("Number of Taps: " + numTaps + ""); // concatenate to make String
    }
}
