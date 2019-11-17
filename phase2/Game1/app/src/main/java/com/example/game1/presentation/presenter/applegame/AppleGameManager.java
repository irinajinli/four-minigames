package com.example.game1.presentation.presenter.applegame;

import android.graphics.Color;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.common.MainThread;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.Star;

import java.util.Random;

public class AppleGameManager extends GameManager {
  /**
   * A GameManager for an Apple minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling Apples.
   */
  private Basket basket;

  private PointsCounter points;
  private int numDroppedApples = 0;
  private int numCaughtStars = 0;

  /** Constructs an AppleGameManager with a height and width of 10. */
  public AppleGameManager() {
    super(10, 10);
  }

  /**
   * Constructs an AppleGameManager with the specified height and width.
   *
   * @param height the height of the AppleGameManager
   * @param width the width of the AppleGameManager
   */
  public AppleGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.APPLE);
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();
    if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      Background b = new Background();
      place(b);
      b.setLocation(0, 0);
    }

    basket = new Basket();
    place(basket);
    basket.setLocation(getGridWidth() / 2 + 1, getGridHeight() - 5);

    // set colour of basket according to Customization
    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      basket.setColor(Color.BLUE);
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
      basket.setColor(Color.RED);
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      basket.setColor(Color.YELLOW);
    }

    points = new PointsCounter();
    place(points);
    points.setLocation(getGridWidth() - 2, 2);

//    Apple a1 = new Apple();
//    Apple a2 = new Apple();
//    Apple a3 = new Apple();
//    Apple a4 = new Apple();
//    place(a1);
//    a1.setLocation(0, 15);
//    place(a2);
//    a2.setLocation(10, 0);
//    place(a3);
//    a3.setLocation(20, 8);
//    place(a4);
//    a4.setLocation(15, 30);

    Star s1 = new Star();
    place(s1);
    s1.setLocation(10, 35);
  }

  /**
   * Move this AppleGameManager's Basket to the specified x coordinate.
   *
   * @param x the x coordinate to move this Basket to
   */
  public void moveBasket(int x) {
    basket.move(x);
  }

  /** Moves, removes, and catches GameItems. */
  public boolean update() {

    // check if the game is over
    if (numDroppedApples >= 15) {
//      MainThread.isRunning = false;
      // tell GameView to change GameView.thread.isRunning to false
      gameOver();
      return false;
    }

    for (int i = 0; i < getGameItems().size(); i++) {
      GameItem currItem = getGameItems().get(i);

      // move each GameItem
      currItem.move();

      if (!(currItem instanceof Basket)) {
        // check if each non-Basket GameItem is off screen; remove if necessary
        if (currItem.getY() > getGridHeight()) {
          dropGameItem(currItem);
        }

        // check if currItem has been caught; remove if necessary
        if ((currItem.getX() == basket.getX() || currItem.getX() == basket.getX() - 1 ||
                currItem.getX() == basket.getX() + 1) && currItem.getY() == basket.getY()) {
            // if currItem is within +/- 1 of basket
          removeItem(currItem);
          if (currItem instanceof Apple) {
            catchApple();
          } else if (currItem instanceof Star) {
            catchStar();
          }
        }
      }
    }
    spawnNew();

    return true;
  }

  /** Catches an Apple. */
  private void catchApple() {
    points.addPoints(1);
  }

  /** Catches a Star. */
  private void catchStar() {
    numCaughtStars += 1;
  }

  /** Drops the specified Apple. */
  private void dropGameItem(GameItem currItem) {
    removeItem(currItem);
    if (currItem instanceof Apple) numDroppedApples += 1;
  }

  /** Spawns a new Apple or Star in a random location at the top of the screen. */
  private void spawnNew() {
    // get a random x-coordinate to spawn the new Apple/Star at
    Random randCoordinate = new Random();
    int spawnCoordinate = randCoordinate.nextInt(getGridWidth());

    // decide whether to spawn an Apple or a Star or nothing
    Random randItem = new Random();
    int randint = randItem.nextInt(200);
    if (randint < 1) {
      // spawn new Star
      Star nextItem = new Star();
      place(nextItem);
      nextItem.setLocation(spawnCoordinate, 0);
    } else if (randint < 10) {
      // spawn new Apple
      Apple nextItem = new Apple();
      place(nextItem);
      nextItem.setLocation(spawnCoordinate, 0);
    }

    // else do nothing
  }

  /** Ends this minigame. */
  public void gameOver() {
    game.setNumPoints(game.getNumPoints() + points.getNumPoints());
    game.setNumStars(game.getNumStars() + numCaughtStars);
    super.gameOver();
  }
}
