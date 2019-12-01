package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.MovementInfo;

/** A class for passing information to AppleGameManager.update. */
public class AppleMovementInfo extends MovementInfo {
  /** the basket */
  Basket basket;

  /**
   * Construct AppleMovementInfo to store informaton needed for apple game item to perform update
   *
   * @param screenWidth width of the screen
   * @param screenHeight height of the screen
   * @param basket the basket
   * @param numSeconds time period for updating coordinates
   */
  public AppleMovementInfo(int screenWidth, int screenHeight, Basket basket, double numSeconds) {
    super(numSeconds, screenHeight, screenWidth);
    this.basket = basket;
  }

  /**
   * Return the basket
   *
   * @return the basket
   */
  public Basket getBasket() {
    return basket;
  }

  /**
   * Set the basket
   *
   * @param basket
   */
  public void setBasket(Basket basket) {
    this.basket = basket;
  }
}
