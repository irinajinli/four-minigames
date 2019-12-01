package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;

/** A Star that is used in the apple game */
public class AppleStar extends AnimatedGameItem {

  /**
   * Constructs an AppleStar with the specified width and height.
   *
   * @param width the width of this GameItem
   * @param height the height of this GameItem
   */
  public AppleStar(int width, int height) {

    super(height, width);
  }

  @Override
  /**
   * @param appleMovementInfo
   * @return result: result needed by the game manager.
   */
  public AppleResult update(MovementInfo appleMovementInfo) {
    // Update x and y coordinates of this apple based on specified time period
    updatePositionAndVelocity(((appleMovementInfo).getNumSeconds()));
    AppleResult result = new AppleResult();

    if (appleMovementInfo instanceof AppleMovementInfo) {
      AppleMovementInfo ami = (AppleMovementInfo) appleMovementInfo;
      Basket basket = ami.getBasket();
      // If this star is collected by the basket, update the result and put this star to the list
      // of game items that will be removed later.
      if (this.isOverlapping(basket)) {
        result.setStarCollected(true);
        result.addOldItem(this);
      }
    }
    return result;
  }
}
