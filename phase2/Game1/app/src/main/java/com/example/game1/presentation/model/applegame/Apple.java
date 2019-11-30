package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

public class Apple extends AnimatedGameItem {

  public Apple(int width, int height) {
    super(width, height);
  }

  public AppleResult update(MovementInfo appleMovementInfo) {

    updatePositionAndVelocity(((appleMovementInfo).getNumSeconds()));
    AppleResult result = new AppleResult();
    if (appleMovementInfo instanceof AppleMovementInfo) {

      AppleMovementInfo ami = (AppleMovementInfo) appleMovementInfo;
      Basket basket = ami.getBasket();

    if (this.isOverlapping(basket)) {
      result.setAppleCollected(true);
      result.addOldItem(this);
    }

    if (this.getYCoordinate() >= ami.getScreenHeight()){
      result.addOldItem(this);
      result.setAppleDropped(true);
    }
}

    return result;
  }

  public Result animate(MovementInfo movementInfo) {
    return new Result();
  }

  //public void animate(double numSeconds) {
//    updatePositionAndVelocity(numSeconds);
//  }
}
