package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;

/** An apple */
public class Apple extends AnimatedGameItem {

  /**
   * Construct an apple with specified width and height
   *
   * @param width
   * @param height
   */
  public Apple(int width, int height) {
    super(height, width);
  }

  @Override
  /**
   * Perform update based on the information given by the movement info
   *
   * @param appleMovementInfo information needed for the apple to execute update
   * @return update result information
   */
  public AppleResult update(MovementInfo appleMovementInfo) {

    // Update x and y coordinates of this apple based on specified time period
    updatePositionAndVelocity(((appleMovementInfo).getNumSeconds()));

    AppleResult result = new AppleResult();
    if (appleMovementInfo instanceof AppleMovementInfo) {
      AppleMovementInfo ami = (AppleMovementInfo) appleMovementInfo;
      // Get basket from apple movement info
      Basket basket = ami.getBasket();

      // If this apple is collected by the basket, update the result and put this apple to the list
      // of game items that will be removed later.
      if (this.isOverlapping(basket)) {
        result.setAppleCollected(true);
        result.addOldItem(this);
      }
      // If this apple is out of the screen, update the result and put this apple to the list of
      // game items that will be removed later
      if (this.getYCoordinate() >= ami.getScreenHeight()) {
        result.setAppleDropped(true);
        result.addOldItem(this);
      }
    }

    return result;
  }
}
