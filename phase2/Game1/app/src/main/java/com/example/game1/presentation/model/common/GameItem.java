package com.example.game1.presentation.model.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameView;
import com.example.game1.presentation.view.tappinggame.TappingGameView;

/**
 * An item which can be in a GameManager.
 */
public abstract class GameItem {



    /** How this item appears on the screen. */
    private Object appearance;
    /** the width of this item */
    private int width;
    /** the height of this item */
    private int height;
    /** This item's position: x coordinate. */
    private double xCoordinate;
    /** This item's position: y coordinate. */
    private double yCoordinate;

    // variables to be deleted
    /** This item's Paint. */
    public Paint paintText = new Paint();
    JumpingGameManager jgm;



    /**
     * Constructs a GameItemOld with the specified appearance.
     * @param height the height of this GameItemOld
     * @param width the width of this GameItemOld
     *
     */
    public GameItem(int height, int width) {
        this.height = height;
        this.width = width;
        // to be deleted
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
    }


    /**
     * Constructs a GameItemOld with the specified appearance, height, width.
     * @param height the height of this GameItemOld
     * @param width the width of this GameItemOld
     * @param appearance the appearance of this GameItemOld
     */
    public GameItem(int height, int width, Object appearance) {
        this.height = height;
        this.width = width;
        this.appearance = appearance;
        // to be deleted
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
    }


    // constructors to be deleted
    /**
     * Constructs a GameItemOld with the specified appearance, height, width.
     * @param height the height of this GameItemOld
     * @param width the width of this GameItemOld
     * @param appearance the appearance of this GameItemOld
     */
    public GameItem(int height, int width, Object appearance, JumpingGameManager jgm) {
        this.height = height;
        this.width = width;
        this.appearance = appearance;
        // to be deleted
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
        this.jgm = jgm;
    }

    /**
     * Constructs a GameItemOld with the specified height, width.
     * @param height the height of this GameItemOld
     * @param width the width of this GameItemOld
     * @param jgm the JumpingGameManager that manages this object
     */
    public GameItem(int height, int width, JumpingGameManager jgm) {
        this.height = height;
        this.width = width;
        // to be deleted
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setTextSize(36);
        this.jgm = jgm;
    }






    /**
     * Sets the appearance of this GameItemOld.
     *
     * @param appearance the appearance of this GameItemOld
     */
    public void setAppearance(Object appearance) {
        this.appearance = appearance;
    }

    /**
     * Get the appearance of this GameItemOld.
     *
     * @return the appearance of this GameItemOld
     */
    public Object getAppearance() {
        return appearance;
    }

    /**
     * Sets the position of this GameItemOld.
     *
     * @param xCoordinate the x coordinate
     * @param yCoordinate the y coordinate
     */
    public void setPosition(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     *Set the xCoordinate of this GameItemOld
     *
     * @param xCoordinate the x coordinate
     */
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     *Set the yCoordinate of this GameItemOld
     *
     * @param yCoordinate the y coordinate
     */
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Get the xCoordinate of this GameItemOld
     * @return the xCoordinate of this GameItemOld
     */
    public double getxCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Get the yCoordinate of this GameItemOld
     * @return the yCoordinate of this GameItemOld
     */
    public double getyCoordinate() {
        return this.yCoordinate;
    }

    /**
     * Get the width of this GameItemOld
     * @return the width of this GameItemOld
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of this GameItemOld
     * @return the height of this GameItemOld
     */
    public int getHeight() {
        return this.height;
    }

    public boolean isOverlapping(GameItem other) {
        double thisItemLeftBoundary = this.xCoordinate;
        double thisItemRightBoundary = this.xCoordinate + this.width;
        double otherItemLeftBoundary = other.getxCoordinate();
        double otherItemRightBoundary = other.getxCoordinate() + other.getWidth();

        double thisItemLowerBoundary = this.yCoordinate;
        double thisItemUpperBoundary = this.yCoordinate + this.height;
        double otherItemLowerBoundary = other.getyCoordinate();
        double otherItemUpperBoundary = other.getyCoordinate() + other.getHeight();

        return !(thisItemRightBoundary < otherItemLeftBoundary
                || otherItemRightBoundary < thisItemLeftBoundary
                || thisItemUpperBoundary < otherItemLowerBoundary
                || otherItemUpperBoundary < thisItemLowerBoundary);
    }


    // to be deleted
    /**
     * Draw this GameItemOld.
     *
     * @param canvas the canvas on which to draw this item.
     */
    public void draw(Canvas canvas) {


        if(appearance.getClass() == String.class) {
            drawString(canvas, (String)appearance, (int)Math.round(xCoordinate), (int)Math.round(yCoordinate));
            //canvas.drawText((String) appearance, x * TappingGameView.charWidth, y * TappingGameView.charHeight, paintText);
        } else if (appearance.getClass() == Bitmap.class){
            canvas.drawBitmap((Bitmap) appearance, (int)Math.round(xCoordinate)* TappingGameView.charWidth, (int)Math.round(yCoordinate)* TappingGameView.charHeight, paintText);
        }
    }
    /**
     * Draw the GameItemOld at a location on the specified Canvas using a String.
     *
     * @param canvas the canvas on which to draw
     * @param s the String to draw
     * @param x the x coordinate at which to draw
     * @param y the y coordinate at which to draw
     */
    public void drawString(Canvas canvas, String s, int x, int y){
        canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, paintText);
    }

    public void setColor(int color) {
        paintText.setColor(color);
    }

}