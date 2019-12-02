package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;

/** A Apple Item Builder */
public class AppleItemsBuilder {
  /** Basket */
  private Basket basket;
  /** Points Counter */
  private PointsCounter pointsCounter;
  /** LivesCounter */
  private LivesCounter livesCounter;
  /** the width of the basket */
  private int basketWidth;
  /** the height of the basket */
  private int basketHeight;

  /** Create a basket */
  public void createBasket() {
    this.basket = new Basket(basketHeight, basketWidth);
  }

  /** Create a PointsCounter */
  public void createPointsCounter() {
    this.pointsCounter = new PointsCounter();
  }

  /** Create a LivesCounter */
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

  /**
   * @param basketWidth the width of the basket
   * @param basketHeight the height of the basket
   */
  public void setBasketSize(int basketWidth, int basketHeight) {
    this.basketWidth = basketWidth;
    this.basketHeight = basketHeight;
  }
}
