package com.example.game1.presentation.model.brickgame;


import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.brickgame.BrickResult;
import com.example.game1.presentation.presenter.common.MovementInfo;

import java.util.List;

public class Paddle extends AnimatedGameItem {
    /** The jumper. */

    /**
     * Constructs a Jumper with the specified height, width, and appearance.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearances the appearance of this GameItem
     */
    public Paddle(int height, int width, List appearances) {
        super(height, width, appearances);
    }

    @Override
    /**
     * @param jumpingMovementInfo:importInfo needed for this jumper to animate
     * @return the info needed by game manager after the animation
     */
    public BrickResult animate(MovementInfo jumpingMovementInfo) {
        BrickResult brickResult = new BrickResult();
        // Jumper land on the terrain
        /**Terrain terrain = ((JumpingMovementInfo) jumpingMovementInfo).getTerrain();
        updatePositionAndVelocity(((JumpingMovementInfo) jumpingMovementInfo).getNumSeconds());
        if (this.isOverlapping(terrain)) {
            this.setyCoordinate(terrain.getyCoordinate() - this.getHeight());
            this.setYVelocity(0);
            this.setyAcceleration(0);
        }*/
        return brickResult;
    }

    @Override
    public void move() {}

    @Override
    public BrickResult update(MovementInfo jumpingMovementInfo) {
        return (new BrickResult());
    }
}
