package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

import java.util.List;

public class Obstacle extends AnimatedGameItem {

    /** The Obstacle. */

    /**
     * Constructs a Obstacle with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearances the appearances of this GameItem
     */
    public Obstacle(int height, int width, List appearances) {
        super(height, width, appearances);
    }

    @Override
    /**
     * @param jumpingImportInfo: importInfo needed for this jumper to animate
     * @return the info needed by game manager after the animation
     */
    public JumpingResult animate(ImportInfo jumpingImportInfo) {
        updatePositionAndVelocity(((JumpingImportInfo) jumpingImportInfo).getNumOfSeconds());
        JumpingResult jumpingResult = new JumpingResult();

        //Set gameover to be true in the jumping result if jumper touches the obstacle
        Jumper jumper = ((JumpingImportInfo) jumpingImportInfo).getJumper();
        if (this.isOverlapping(jumper)) {
            jumpingResult.setGameOver(true);

            // reset obstacle's xCoordinate if it is out of the screen
        } else if (this.getXCoordinate() + this.getWidth() < 0) {
            this.setXCoordinate(((JumpingImportInfo) jumpingImportInfo).getScreenWidth() * 4 / 3);
            jumpingResult.setObstacleJumped(true);

            // randomly add new star
            if (Math.random() > 0.7) {
                jumpingResult.setNeedNewStar(true);
            }
        }
//    else{
//      setXCoordinate(getxCoordinate() - 15);
//    }
        return jumpingResult;
    }

    @Override
    public void move() {}

    @Override
    public JumpingResult update(ImportInfo importInfo) {
        return (new JumpingResult());
    }
}
