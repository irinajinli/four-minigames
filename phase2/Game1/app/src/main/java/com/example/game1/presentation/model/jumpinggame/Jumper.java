package com.example.game1.presentation.model.jumpinggame;


import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

public class Jumper extends AnimatedGameItem {
    /** The jumper. */

    /**
     * Constructs a Jumper with the specified height, width, and appearance.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearance the appearance of this GameItem
     */
    public Jumper(int height, int width, Object appearance) {
        super(height, width, appearance);
    }

    @Override
    /**
     * @param jumpingImportInfo: importInfo needed for this jumper to animate
     * @return the info needed by game manager after the animation
     */
    public JumpingResult animate(ImportInfo jumpingImportInfo) {
        JumpingResult jumpingResult = new JumpingResult();
        // Jumper land on the terrain
        Terrain terrain = ((JumpingImportInfo) jumpingImportInfo).getTerrian();
        updatePositionAndVelocity(((JumpingImportInfo) jumpingImportInfo).getNumOfSeconds());
        if (this.isOverlapping(terrain)) {
            this.setYCoordinate(terrain.getyCoordinate() - this.getHeight());
            this.setYVelocity(0);
            this.setYAcceleration(0);
        }
        return jumpingResult;
    }

    @Override
    public void move() {}

    @Override
    public JumpingResult update(ImportInfo jumpingImportInfo) {
        return (new JumpingResult());
    }
}
