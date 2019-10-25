package com.example.game1.Domain;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * An item which can be in a AppleGameManager.
 */

abstract public class GameItem {
    /**
     * How this item appears on the screen.
     */
    private String appearance;

    /**
     * The AppleGameManager this item is in.
     */
    private AppleGameManager tank;

    /**
     * This item's x coordinate.
     */
    private int x;

    /**
     * This item's y coordinate.
     */
    private int y;

    /**
     * This item's Paint.
     */
    Paint paintText = new Paint();


    /**
     * Constructs a GameItem with the specified appearance.
     *
     * @param appearance the appearance of this GameItem
     */
    GameItem(String appearance) {
        this.appearance = appearance;
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
    }

    /**
     * Sets the location of this GameItem in the specified AppleGameManager.
     *
     * @param tank the GameItem
     * @param x    the x coordinate
     * @param y    the y coordinate
     */
    void setLocation(AppleGameManager tank, int x, int y) {
        this.x = x;
        this.y = y;
        this.tank = tank;
        tank.place(this);
    }

    /**
     * Changes the location of this GameItem within its AppleGameManager.
     *
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    void changeLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the current x coordinate of this GameItem.
     *
     * @return the x coordinate of this GameItem
     */
    int getX() {
        return x;
    }

    /**
     * Returns the current y coordinate of this GameItem.
     *
     * @return the y coordinate of this GameItem
     */
    int getY() {
        return y;
    }

    /**
     * Sets the appearance of this GameItem.
     *
     * @param appearance the appearance of this GameItem
     */
    void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    /**
     * Returns the appearance of this GameItem.
     *
     * @return the appearance of this GameItem
     */
    String getAppearance() {
        return appearance;
    }

    /**
     * Returns the AppleGameManager this GameItem is inside.
     *
     * @return the AppleGameManager this GameItem is inside
     */
    AppleGameManager getTank() {
        return tank;
    }

    /**
     * Draws this GameItem.
     *
     * @param canvas the canvas on which to draw this item.
     */
    public void draw(Canvas canvas) {
        drawString(canvas, appearance, x, y);
    }

    /**
     * Moves this GameItem within its AppleGameManager.
     */
    abstract void move();

    /**
     * Draws the GameItem at a location on the specified Canvas using a String.
     *
     * @param canvas the canvas on which to draw
     * @param s      the String to draw
     * @param x      the x coordinate at which to draw
     * @param y      the y coordinate at which to draw
     */
    abstract void drawString(Canvas canvas, String s, int x, int y);
}
