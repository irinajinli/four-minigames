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
    public void setXVelocity(double xVelocity) {

        this.xVelocity = xVelocity;
    }

    /**
     *
     * @return velocity of this GameItem at x axis direction
     */
    public double getXVelocity() {
        return this.xVelocity;
    }


    /**
     *
     * @param yVelocity velocity at y axis direction
     */
    public void setYVelocity(double yVelocity) {

        this.yVelocity = yVelocity;
    }


    /**
     *
     * @return velocity of this GameItem at y axis direction
     */
    public double getYVelocity() {
        return yVelocity;
    }



    /**
     *
     * @param xAcceleration acceleration at x axis direction
     */
    public void setXAcceleration(double xAcceleration) {

        this.xAcceleration = xAcceleration;
    }

    /**
     *
     * @return acceleration of this GameItem at x axis direction
     */
    public double getXAcceleration() {
        return xAcceleration;
    }

    /**
     *
     * @param yAcceleration acceleration at y axis direction
     */
    public void setYAcceleration(double yAcceleration) {

        this.yAcceleration = yAcceleration;
    }



    /**
     *
     * @return acceleration of this GameItem at y axis direction
     */
    public double getYAcceleration() {
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
        double newXCoordinate = getXCoordinate()
                + (0.5 * getXAcceleration() * numOfSeconds * numOfSeconds
                + getXVelocity() * numOfSeconds);
        setXCoordinate(newXCoordinate);

        double newYCoordinate = getYCoordinate()
                + (0.5 * getYAcceleration() * numOfSeconds * numOfSeconds
                + getYVelocity() * numOfSeconds);
        setYCoordinate(newYCoordinate);

        double newXVelocity = getXVelocity() + getXAcceleration() * numOfSeconds;
        setXVelocity(newXVelocity);

        double newYVelocity = getYVelocity() + getYAcceleration() * numOfSeconds;
        setYVelocity(newYVelocity);
    }

    public abstract Result animate(ImportInfo importInfo);

}
