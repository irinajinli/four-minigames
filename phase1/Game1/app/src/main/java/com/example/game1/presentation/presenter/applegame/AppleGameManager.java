package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.Star;

import java.util.Random;

public class AppleGameManager extends GameManager {

  Basket basket;
  PointsCounter points;

  public AppleGameManager() {
    super(10, 10);
  }

  public AppleGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.APPLE);
  }

  public void createGameItems() {
    Apple a1 = new Apple();
    Apple a2 = new Apple();
    Apple a3 = new Apple();
    place(a1);
    a1.setLocation(0, 15);
    place(a2);
    a2.setLocation(10, 0);
    place(a3);
    a3.setLocation(20, 8);

    basket = new Basket();
    place(basket);
    basket.setLocation(getGridWidth() / 2 + 1, getGridHeight() - 5);

    points = new PointsCounter();
    place(points);
    points.setLocation(getGridWidth() - 2, 2);
  }

  public void update() {

    for (int i = 0; i < getGameItems().size(); i++) {
      GameItem currItem = getGameItems().get(i);

      // move each GameItem
      currItem.move();

      if (!(currItem instanceof Basket)) {
        // check if each non-Basket GameItem is off screen; remove if necessary
        if (currItem.getY() > getGridHeight()) removeItem(currItem);
        // check if currItem has been caught; remove if necessary
        if (currItem.getX() == basket.getX() && currItem.getY() == basket.getY()) {
          removeItem(currItem);
          // TODO: figure out how to use res value for points values
          if (currItem instanceof Apple) points.addPoints(1);
          else if (currItem instanceof Star) points.addPoints(10);
        }
      }
    }
    spawnNew();
  }

  private void spawnNew() {
    // get a random x-coordinate to spawn the new Apple/Star at
    Random randCoordinate = new Random();
    int spawnCoordinate = randCoordinate.nextInt(getGridWidth());

    // decide whether to spawn an Apple or a Star or nothing
    Random randItem = new Random();
    int randint = randItem.nextInt(100);
    if (randint < 1) {
      // spawn new Star
      Star nextItem = new Star();
      place(nextItem);
      nextItem.setLocation(spawnCoordinate, 0);
    } else if (randint < 15) {
      // spawn new Apple
      Apple nextItem = new Apple();
      place(nextItem);
      nextItem.setLocation(spawnCoordinate, 0);
    }

    // else do nothing
  }

  /**
   * Move this AppleGameManager's Basket to the specified x coordinate.
   *
   * @param x
   */
  public void moveBasket(int x) {
    basket.move(x);
  }
}