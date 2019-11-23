package com.example.game1.presentation.presenter.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.Background;

public class GameItemsBuilder {
  private Customization customization;
  private Background background;
  private Basket basket;
  private PointsCounter pointsCounter;

  public GameItemsBuilder(Customization customization) {
    this.customization = customization;
  }

  public void createBackground() {
    if (customization.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      this.background = new Background();
      //            place(b);
      //            b.setLocation(0, 0);
    }
  }

  public void createBasket() {
    this.basket = new Basket();
    // set color of basket
//    if (customization.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
//      basket.setColor(Color.BLUE);
//    } else if (customization.getCharacterColour().equals(Customization.CharacterColour.RED)) {
//      basket.setColor(Color.RED);
//    } else if (customization.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
//      basket.setColor(Color.YELLOW);
//    }
  }

  public void createPointsCounter() {
    this.pointsCounter = new PointsCounter();
  }

  /**
   * Builds the items in the specified GameManager.
   *
   * @param appleGameManager
   */
  public void placeItems(AppleGameManager appleGameManager) {
    // background
    if (background instanceof Background) {
      appleGameManager.place(background);
      background.setPosition(0, 0);
    }

    // basket
    appleGameManager.setBasket(basket);
    appleGameManager.place(basket);
    basket.setPosition(
            appleGameManager.getGridWidth() / 2 + 1, appleGameManager.getGridHeight() - 5);

    // pointsCounter
    appleGameManager.setPointsCounter(pointsCounter);
    appleGameManager.place(pointsCounter);
    pointsCounter.setPosition(appleGameManager.getGridWidth() - 2, 2);
  }
}
