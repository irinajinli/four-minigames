package com.example.game1.presentation.model.common;

import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;

public abstract class AnimatedGameItem extends GameItem {
    /** This item's velocity for x coordinate. */
    private double xVelocity;
    /** This item's velocity for y coordinate. */
    private double yVelocity;
    /** This item's acceleration for x coordinate. */
    private double xAcceleration;
    /** This item's acceleration for y coordinate. */
    private double yAcceleration;
    /** This item's customization info passed by view. */
    //private Object appearance;

    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param appearance the appearance of this GameItem
     */
    protected AnimatedGameItem(Object appearance){
        super(appearance);
    }

    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearance the appearance of this GameItem
     */
    protected AnimatedGameItem(int height, int width, Object appearance){
        super(height, width, appearance);
    }
    /**
     * Constructs a AnimatedGameItem with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     */
    protected AnimatedGameItem(int height, int width){
        super(height, width);
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
    public void setyAcceleration(double yAcceleration) {

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
     * @param numOfSeconds number of seconds used to refresh new xCoordinate, yCoordinate,
     *                     xVelocity, yVelocity based on current xAcceleration and yAcceleration.
     *                     Currently numOfSeconds = GameThread.FRAME_DURATION_NS / 1000000000.
     */
    public void updatePositionAndVelocity(double numOfSeconds) {
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

    public abstract Result animate(ImportInfo importInfo);

}
