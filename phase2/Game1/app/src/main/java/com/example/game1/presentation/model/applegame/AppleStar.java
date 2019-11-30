package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.presenter.applegame.AppleMovementInfo;
import com.example.game1.presentation.presenter.applegame.AppleResult;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.presenter.jumpinggame.JumpingMovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

import java.util.List;

public class AppleStar extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a Star with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearances the appearances of this GameItem
   */
  public AppleStar(int width, int height, List appearances) {

    super(width, height, appearances);
  }

  /**
   * @param appleMovementInfo
   * @return result: result needed by the game manager.
   */
  public AppleResult update(MovementInfo appleMovementInfo) {
    updatePositionAndVelocity(((appleMovementInfo).getNumSeconds()));
    AppleResult result = new AppleResult();

    if (appleMovementInfo instanceof AppleMovementInfo) {

      AppleMovementInfo ami = (AppleMovementInfo) appleMovementInfo;
      Basket basket = ami.getBasket();
      // if star is collected by the jumper,
      // inform the jumping result to remove the star in the view
      // and increment the numStar collected in the game manager
      if (this.isOverlapping(basket)) {
        result.setStarCollected(true);
        result.addOldItem(this);
      }
    }
    return result;
  }

  public AppleResult animate(MovementInfo movementInfo) {
    return  (new AppleResult());
  }


}
