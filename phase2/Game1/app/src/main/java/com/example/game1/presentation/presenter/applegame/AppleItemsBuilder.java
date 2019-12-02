package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;


public class AppleItemsBuilder {

  private Basket basket;
  private PointsCounter pointsCounter;
  private LivesCounter livesCounter;
  private int basketWidth;
  private int basketHeight;




  public void createBasket() {
    this.basket = new Basket(basketHeight, basketWidth);
  }

  public void createPointsCounter() {
    this.pointsCounter = new PointsCounter();
  }

  public void createLivesCounter() {
    this.livesCounter = new LivesCounter(10);
  }

  /**
   * Builds the items in the specified GameManager.
   *
   * @param appleGameManager
   */
  public void placeItems(AppleGameManager appleGameManager) {

    // basket
    appleGameManager.setBasket(basket);
    appleGameManager.place(basket);
    basket.setPosition(appleGameManager.getGridWidth() / 2, appleGameManager.getGridHeight() - 300);

    // pointsCounter
    appleGameManager.setPointsCounter(pointsCounter);
    appleGameManager.place(pointsCounter);
    pointsCounter.setPosition(appleGameManager.getGridWidth() - 250, 100);

    // livesCounter
    appleGameManager.setLivesCounter(livesCounter);
    appleGameManager.place(livesCounter);
    livesCounter.setPosition(appleGameManager.getGridWidth() - 250, 150);
  }

  public void setBasketSize(int basketWidth, int basketHeight){
    this.basketWidth = basketWidth;
    this.basketHeight = basketHeight;
  }
}
