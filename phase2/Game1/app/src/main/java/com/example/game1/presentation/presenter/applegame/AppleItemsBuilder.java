package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;

import java.util.List;

public class AppleItemsBuilder {
  private Customization customization;
  private Basket basket;
  private PointsCounter pointsCounter;
  private LivesCounter livesCounter;

  public AppleItemsBuilder(Customization customization) {
    this.customization = customization;
  }

//  public void setTheme(AppleGameManager appleGameManager) {
//    if (customization.getColourScheme().equals(Customization.ColourScheme.DARK)) {
//      appleGameManager.setScreenBackgroundColor(appleGameManager.getSkyColorDark());
//    } else if (customization.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
//      appleGameManager.setScreenBackgroundColor(appleGameManager.getSkyColorLight());
//    }
//  }

  public void createBasket(List basketBmps, List basketBlueBmps, List basketYellowBmps) {
    this.basket = new Basket(100, 100);
    // set color of basket
    if (customization.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      this.basket = new Basket(100, 100);
    } else if (customization.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      this.basket = new Basket(100, 100);
    } else this.basket = new Basket(100, 100);
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
}
