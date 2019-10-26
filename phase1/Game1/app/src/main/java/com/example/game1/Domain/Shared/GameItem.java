package com.example.game1.Domain.Shared;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * An item which can be in a GameManager.
 */
public abstract class GameItem {
    /**
     * This item's Paint.
     */
    public Paint paintText = new Paint();
    /**
     * How this item appears on the screen.
     */
    private String appearance;
    /**
     * The GameManager this item is in.
     */
    private GameManager manager;
    /**
     * This item's x coordinate.
     */
    private int x;
    /**
     * This item's y coordinate.
     */
    private int y;

    /**
     * Constructs a GameItem with the specified appearance.
     *
     * @param appearance the appearance of this GameItem
     */
    public GameItem(String appearance) {
        this.appearance = appearance;
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
    }

    /**
     * Sets the location of this GameItem in the specified GameManager.
     *
     * @param manager the GameItem
     * @param x       the x coordinate
     * @param y       the y coordinate
     */
    public void setLocation(GameManager manager, int x, int y) {
        this.x = x;
        this.y = y;
        this.manager = manager;
        manager.place(this);
    }

    /**
     * Changes the location of this GameItem within its GameManager.
     *
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void changeLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the current x coordinate of this GameItem.
     *
     * @return the x coordinate of this GameItem
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the current y coordinate of this GameItem.
     *
     * @return the y coordinate of this GameItem
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the appearance of this GameItem.
     *
     * @return the appearance of this GameItem
     */
    public String getAppearance() {
        return appearance;
    }

    /**
     * Sets the appearance of this GameItem.
     *
     * @param appearance the appearance of this GameItem
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    /**
     * Returns the GameManager this GameItem is inside.
     *
     * @return the GameManager this GameItem is inside
     */
    public GameManager getManager() {
        return manager;
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
     * Moves this GameItem within its GameManager.
     */
    public abstract void move();

    /**
     * Draws the GameItem at a location on the specified Canvas using a String.
     *
     * @param canvas the canvas on which to draw
     * @param s      the String to draw
     * @param x      the x coordinate at which to draw
     * @param y      the y coordinate at which to draw
     */
    public abstract void drawString(Canvas canvas, String s, int x, int y);
}
