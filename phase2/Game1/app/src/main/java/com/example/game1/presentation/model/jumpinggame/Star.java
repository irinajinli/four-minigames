package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingMovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

import java.util.List;

public class Star extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a Star with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearances the appearances of this GameItem
   */
  public Star(int width, int height, List appearances) {

    super(width, height, appearances);
  }

  @Override
  /**
   * @param jumper
   * @return result: result needed by the game manager.
   */
  public JumpingResult animate(MovementInfo jumpingMovementInfo) {
    updatePositionAndVelocity(((JumpingMovementInfo) jumpingMovementInfo).getNumOfSeconds());
    JumpingResult result = new JumpingResult();

    Jumper jumper = ((JumpingMovementInfo) jumpingMovementInfo).getJumper();
    // if star is collected by the jumper,
    // inform the jumping result to remove the star in the view
    // and increment the numStar collected in the game manager
    if (this.isOverlapping(jumper)) {
      result.setStarCollected(true);
      result.addOutItem(this);
    }
    //    else{
    //      setXCoordinate(getxCoordinate() - 15);
    //    }

    return result;
  }

  public void animate(double numOfSeconds) {
    updatePositionAndVelocity(numOfSeconds);
  }

  @Override
  public void move() {}

  @Override
  public JumpingResult update(MovementInfo jumpingMovementInfo) {
    return (new JumpingResult());
  }
}
