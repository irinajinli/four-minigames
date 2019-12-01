package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;

public class JumpingStar extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a JumpingStar with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public JumpingStar(int width, int height) {

    super(width, height);
  }


  @Override
  public JumpingResult update(MovementInfo jumpingMovementInfo) {
    updatePositionAndVelocity(jumpingMovementInfo.getNumSeconds());
    JumpingResult result = new JumpingResult();
    if (jumpingMovementInfo instanceof JumpingMovementInfo) {
      GameItem starCollector = ((JumpingMovementInfo) jumpingMovementInfo).getJumper();
      // if star is collected by the jumper,
      // inform the jumping result to remove the star in the view
      // and increment the numStar collected in the game manager
      if (this.isOverlapping(starCollector)) {
        result.setStarCollected(true);
        result.addOldItem(this);
      }
    }
    return result;
  }
}
