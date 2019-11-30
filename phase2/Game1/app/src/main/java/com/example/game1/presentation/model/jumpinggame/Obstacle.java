package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingMovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

import java.util.List;

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
  /**
   * @param jumpingMovementInfo: importInfo needed for this jumper to animate
   * @return the info needed by game manager after the animation
   */
  public JumpingResult animate(MovementInfo jumpingMovementInfo) {
    updatePositionAndVelocity(((JumpingMovementInfo) jumpingMovementInfo).getNumSeconds());
    JumpingResult jumpingResult = new JumpingResult();

    // Set gameover to be true in the jumping result if jumper touches the obstacle
    Jumper jumper = ((JumpingMovementInfo) jumpingMovementInfo).getJumper();
    if (this.isOverlapping(jumper)) {
      jumpingResult.setGameOver(true);

      // reset obstacle's xCoordinate if it is out of the screen
    } else if (this.getXCoordinate() + this.getWidth() < 0) {
      this.setXCoordinate(((JumpingMovementInfo) jumpingMovementInfo).getScreenWidth() * 4 / 3);
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
  public JumpingResult update(MovementInfo movementInfo) {
    return (new JumpingResult());
  }
}
