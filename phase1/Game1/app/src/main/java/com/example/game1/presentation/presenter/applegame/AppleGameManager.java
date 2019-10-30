package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.MainThread;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.Star;

import java.util.Random;

public class AppleGameManager extends GameManager {
  /**
   * A GameManager for the Apple minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling Apples.
   */
  Basket basket;

  PointsCounter points;
  private int numDroppedApples = 0;

  public AppleGameManager() {
    /** Constructs an AppleGameManager with a height and width of 10. */
    super(10, 10);
  }

  public AppleGameManager(int height, int width) {
    /**
     * Constructs an AppleGameManager with the specified height and width.
     *
     * @param height the height of the AppleGameManager
     * @param width the width of the AppleGameManager
     */
    super(height, width);
    this.game = new Game(Game.GameName.APPLE);
  }

  public void createGameItems() {
    /** Creates GameItems required at the beginning of the minigame. */
    /**
     * Updates the GameItems in this GameManager. Moves GameItems and accounts for those that are
     * dropped and caught.
     */
    Apple a1 = new Apple();
    Apple a2 = new Apple();
    Apple a3 = new Apple();
    Apple a4 = new Apple();
    place(a1);
    a1.setLocation(0, 15);
    place(a2);
    a2.setLocation(10, 0);
    place(a3);
    a3.setLocation(20, 8);
    a4.setLocation(15, 30);

    basket = new Basket();
    place(basket);
    basket.setLocation(getGridWidth() / 2 + 1, getGridHeight() - 5);

    points = new PointsCounter();
    place(points);
    points.setLocation(getGridWidth() - 2, 2);
  }

  public void update() {
    /** Moves, removes, and catches GameItems. */
    for (int i = 0; i < getGameItems().size(); i++) {
      GameItem currItem = getGameItems().get(i);

      // move each GameItem
      currItem.move();

      if (!(currItem instanceof Basket)) {
        // check if each non-Basket GameItem is off screen; remove if necessary
        if (currItem.getY() > getGridHeight() && currItem instanceof Apple) {
          dropApple((Apple) currItem);
        }

        // check if the game is over
        if (numDroppedApples >= 1) {
          MainThread.isRunning = false;
          gameOver();
        }

        // check if currItem has been caught; remove if necessary
        if (currItem.getX() == basket.getX() && currItem.getY() == basket.getY()) {
          removeItem(currItem);
          // TODO: figure out how to use res value for points values
          if (currItem instanceof Apple) {
            points.addPoints(1);
            game.setNumPoints(game.getNumPoints() + 1);
          } else if (currItem instanceof Star) {
            points.addPoints(10);
            game.setNumPoints(game.getNumPoints() + 10);
            game.setNumStars(game.getNumStars() + 1);
          }
        }
      }
    }
    spawnNew();
  }

  private void dropApple(Apple currItem) {
    /** Drops the specified Apple. */
    removeItem(currItem);
    numDroppedApples += 1;
  }

  private void spawnNew() {
    /** Spawns a new Apple or Star in a random location at the top of the screen. */

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
    /**
     * Move this AppleGameManager's Basket to the specified x coordinate.
     *
     * @param x the x coordinate to move this Basket to
     */
    basket.move(x);
  }
}
