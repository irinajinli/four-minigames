package com.example.game1.presentation.presenter.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.LivesCounter;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.Background;

import java.util.List;

public class GameItemsBuilder {
  private Customization customization;
  // TODO: delete background
  private Background background;
  private Basket basket;
  private PointsCounter pointsCounter;
  private LivesCounter livesCounter;

  public GameItemsBuilder(Customization customization) {
    this.customization = customization;
  }

  public void setTheme(AppleGameManager appleGameManager) {
      if (customization.getColourScheme().equals(Customization.ColourScheme.DARK)) {
          appleGameManager.setSkyColor(appleGameManager.getSkyColorDark());
      } else if (customization.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
          appleGameManager.setSkyColor(appleGameManager.getSkyColorLight());
      }
  }

  public void createBasket(List basketBmps, List basketBlueBmps, List basketYellowBmps) {
    this.basket = new Basket(100, 100, basketBmps);
    // set color of basket
    if (customization.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      this.basket = new Basket(100, 100, basketBlueBmps);
    } else if (customization.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      this.basket = new Basket(100, 100, basketYellowBmps);
    }
    else
      this.basket = new Basket(100, 100, basketBmps);
  }

  public void createPointsCounter() {
    this.pointsCounter = new PointsCounter();
  }

  public void createLivesCounter() {
    this.livesCounter = new LivesCounter();
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
            appleGameManager.getGridWidth() / 2, appleGameManager.getGridHeight() - 300);

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
