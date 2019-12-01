package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;

public class Obstacle extends AnimatedGameItem {

  /** The Obstacle. */

  /**
   * Constructs a Obstacle with the specified height and width.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public Obstacle(int height, int width) {
    super(height, width);
  }



  @Override
  public JumpingResult update(MovementInfo jumpingMovementInfo) {
    updatePositionAndVelocity(jumpingMovementInfo.getNumSeconds());
    JumpingResult jumpingResult = new JumpingResult();

    // Set gameover to be true in the jumping result if jumper touches the obstacle
    if (jumpingMovementInfo instanceof JumpingMovementInfo) {
      GameItem character = ((JumpingMovementInfo) jumpingMovementInfo).getJumper();
      if (this.isOverlapping(character)) {
        jumpingResult.setGameOver(true);

        // reset obstacle's xCoordinate if it is out of the screen
      } else if (this.getXCoordinate() + this.getWidth() < 0) {
        this.setXCoordinate((jumpingMovementInfo).getScreenWidth() * 4 / 3);
        jumpingResult.setObstacleJumped(true);

        // randomly add new star
        if (Math.random() > 0.7) {
          jumpingResult.setNeedNewStar(true);
        }
      }
    }
    //    else{
    //      setXCoordinate(getxCoordinate() - 15);
    //    }
    return jumpingResult;

    //    return (new JumpingResult());
  }
}
