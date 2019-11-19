package com.example.game1.presentation.view.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

public abstract class AnimatedGameItem extends GameItemStatic {
    /** This item's velocity for x coordinate. */
    private double xVelocity;
    /** This item's velocity for y coordinate. */
    private double yVelocity;
    /** This item's acceleration for x coordinate. */
    private double xAcceleration;
    /** This item's acceleration for y coordinate. */
    private double yAcceleration;
    /** This item's customization info passed by view. */
    private Object appearance;

    //variable to be deleted
    GameView gameView;

    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     */
    AnimatedGameItem(int height, int width, GameView gameView){
        super(height, width);
        this.gameView = gameView;
    }

    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearance the appearance of this GameItem
     */
    AnimatedGameItem(int height, int width, Object appearance, GameView gameView){
        super(height, width, appearance);
        this.gameView = gameView;
    }

    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     *  @param jpg
     *
     */


    //construct to be deleted
    AnimatedGameItem(int height, int width, JumpingGameManager jpg, GameView gameView){
        super(height, width, jpg);
        this.gameView = gameView;
    }

    AnimatedGameItem(int height, int width, Object appearance, JumpingGameManager jgm, GameView gameView){
        super(height, width, appearance, jgm);
        this.gameView = gameView;
    }


    /**
     *
     * @param xVelocity velocity at x axis direction
     */
    public void setxVelocity(double xVelocity) {

        this.xVelocity = xVelocity;
    }

    /**
     *
     * @return velocity of this GameItem at x axis direction
     */
    public double getxVelocity() {
        return this.xVelocity;
    }


    /**
     *
     * @param yVelocity velocity at y axis direction
     */
    public void setyVelocity(double yVelocity) {

        this.yVelocity = yVelocity;
    }


    /**
     *
     * @return velocity of this GameItem at y axis direction
     */
    public double getyVelocity() {
        return yVelocity;
    }



    /**
     *
     * @param xAcceleration acceleration at x axis direction
     */
    public void setAccelerationX(double xAcceleration) {

        this.xAcceleration = xAcceleration;
    }

    /**
     *
     * @return acceleration of this GameItem at x axis direction
     */
    public double getxAcceleration() {
        return xAcceleration;
    }

    /**
     *
     * @param yAcceleration acceleration at y axis direction
     */
    public void setAccelerationY(double yAcceleration) {

        this.yAcceleration = yAcceleration;
    }



    /**
     *
     * @return acceleration of this GameItem at y axis direction
     */
    public double getyAcceleration() {
        return yAcceleration;
    }

    /** Move this GameItem within its GameManager. */
    public abstract void move();

    /**
     *
     * @param numOfSeconds number of seconds used to calculate new xCoordinate, yCoordinate,
     *                     xVelocity, yVelocity based on current xAcceleration and yAcceleration.
     */
    public void update(double numOfSeconds) {
        //double numOfSeconds = GameThread.FRAME_DURATION_NS / 1000000000.; // get amount of time in seconds

        double newXCoordinate = getxCoordinate()
                + (0.5 * getxAcceleration() * numOfSeconds * numOfSeconds
                + getxVelocity() * numOfSeconds);
        setxCoordinate(newXCoordinate);

        double newYCoordinate = getyCoordinate()
                + (0.5 * getyAcceleration() * numOfSeconds * numOfSeconds
                + getyVelocity() * numOfSeconds);
        setyCoordinate(newYCoordinate);

        double newxVelocity = getxVelocity() + getxAcceleration() * numOfSeconds;
        setxVelocity(newxVelocity);

        double newyVelocity = getyVelocity() + getyAcceleration() * numOfSeconds;
        setyVelocity(newyVelocity);
    }

    // Methods to be deleted
    /**
     * code taken from: https://stackoverflow.com/questions/4837715/how-to-resize-a-bitmap-in-android
     *
     * @param bm
     * @param newWidth
     * @param newHeight
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;


    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(((Bitmap)appearance), (int) getxCoordinate(), (int)getyCoordinate(), null);
    }
}
