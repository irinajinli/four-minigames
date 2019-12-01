package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.MovementInfo;

/** A class for passing information to AppleGameManager.update. */
public class AppleMovementInfo extends MovementInfo {
  Basket basket;

  public AppleMovementInfo(int screenWidth, int screenHeight, Basket basket, double numSeconds) {
    super(numSeconds, screenHeight, screenWidth);
    this.basket = basket;
  }
  

  public Basket getBasket() {
    return basket;
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }
}
