package com.example.game1.presentation.model.brickgame;

import android.graphics.Bitmap;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.brickgame.BrickImportInfo;
import com.example.game1.presentation.presenter.brickgame.BrickResult;
import com.example.game1.presentation.presenter.common.ImportInfo;

public class Brick extends GameItem {

    private final int STARTING_DAMAGE_LEVEL = 2;
    private final int APPEARANCE_CHANGE_CUTOFF = 1;
    private final int DESTROYED_CUTOFF = 0;


    private int damageLevel;
    //private Bitmap damagedAppearance;

    /** The Obstacle. */

    /**
     * Constructs a Obstacle with the specified height and width.
     *
     * @param height the height of this GameItem
     * @param width the width of this GameItem
     * @param appearance the appearance of this GameItem
     */
    public Brick(int height, int width, Bitmap appearance) {
        super(height, width, appearance);
        damageLevel = STARTING_DAMAGE_LEVEL;
    }


    /**
     * @param brickImportInfo: importInfo needed for this jumper to animate
     * @return the info needed by game manager after the animation
     */
    public BrickResult animate(ImportInfo brickImportInfo) {
        //updatePositionAndVelocity(((BrickImportInfo) brickImportInfo).getNumOfSeconds());
        BrickResult brickResult = new BrickResult();

        //Set gameover to be true in the jumping result if jumper touches the obstacle
       // Jumper jumper = ((JumpingImportInfo) jumpingImportInfo).getJumper();
       // if (this.isOverlapping(jumper)) {
       //     jumpingResult.setGameOver(true);

            // reset obstacle's xCoordinate if it is out of the screen
       // } else if (this.getxCoordinate() + this.getWidth() < 0) {
       //     this.setxCoordinate(((JumpingImportInfo) jumpingImportInfo).getScreenWidth() * 4 / 3);
       //     jumpingResult.setObstacleJumped(true);

            // randomly add new star
        //    if (Math.random() > 0.7) {
        //        jumpingResult.setNeedNewStar(true);
        //    }
        //}
//    else{
//      setxCoordinate(getxCoordinate() - 15);
//    }
        return brickResult;
    }


    /**
     * Causes this brick to take damage.
     *
     * @return True if an only if this brick is now broken
     */
    public boolean damageBrick(){
        damageLevel -= 1;
        if (damageLevel == DESTROYED_CUTOFF){
            return true;
        }
        return false;
    }

    /**
     * Returns whether the appearance of this brick needs to be changed.
     *
     * @return whether the appearance of this brick needs to be changed.
     */
    public boolean needChangeAppearance(){
        return damageLevel == APPEARANCE_CHANGE_CUTOFF;
    }

    public void move() {}

    @Override
    public BrickResult update(ImportInfo importInfo) {
        return (new BrickResult());
    }
}
