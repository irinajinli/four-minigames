package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.presenter.common.MovementInfo;

/** A class for passing information to AppleGameManager.update. */
public class AppleMovementInfo extends MovementInfo {

  int screenWidth;

  int screenHeight;
  Basket basket;

  public AppleMovementInfo(int screenWidth, int screenHeight, Basket basket, double numSeconds) {
    super(numSeconds);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.basket = basket;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }

  public Basket getBasket() {
    return basket;
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }
}
