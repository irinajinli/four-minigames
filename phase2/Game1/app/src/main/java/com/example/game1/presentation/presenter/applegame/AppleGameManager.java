package com.example.game1.presentation.presenter.applegame;

import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.PointsCounter;
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

  // TODO: new
  private Bitmap appleBMP;
  private Bitmap basketBMP;

  /** Constructs an AppleGameManager with the specified height, width, game, and activity. */
  public AppleGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
  }


  // TODO: new
  public void setBMPFiles(Bitmap appleBMP, Bitmap basketBMP) {
    this.appleBMP = appleBMP;
    this.basketBMP = basketBMP;
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // TODO: delete this method
    GameItemsBuilder gib = new GameItemsBuilder(game.getCustomization());
    gib.createBackground();
    gib.createPointsCounter();
    gib.createBasket(basketBMP);

    gib.placeItems(this);

    // TODO: new for basket
//    this.basket = new Basket(3, 3, basketBMP);
//    place(basket);
//    basket.setPosition(Math.round(getGridWidth()) / 2, getGridHeight());

    // TODO: new for testing coordinates
//    Apple testApple = new Apple(3, 3, appleBMP);
//    place(testApple);
//    testApple.setPosition(30, 3);
//
//    Apple testApple2 = new Apple(3, 3, appleBMP);
//    place(testApple2);
//    testApple2.setPosition(8, 10);
//
//    Apple testApple3 = new Apple(3, 3, appleBMP);
//    place(testApple3);
//    testApple3.setPosition(20, 20);
//
//    Apple testApple4 = new Apple(3, 3, appleBMP);
//    place(testApple4);
//    testApple4.setPosition(15, 30);
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }

  public void setPointsCounter(PointsCounter pointsCounter) {
    this.points = pointsCounter;
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
    if (numDroppedApples >= 20) {
      gameOver();
      return false;
    }

    for (int i = 0; i < getGameItems().size(); i++) {
      GameItem currItem = getGameItems().get(i);
      if (currItem instanceof AnimatedGameItem) {
        // AnimatedGameItem currItem = (AnimatedGameItem) currItem2;
        // move each GameItemOld
        ((AnimatedGameItem) currItem).move();
      } else {}

      if (!(currItem instanceof Basket)) {
        // check if each non-Basket GameItemOld is off screen; remove if necessary
        if (currItem.getyCoordinate() > getGridHeight()) {
          dropGameItem(currItem);
        }

        // if currItem is within +/- 1 of basket
        if (checkIfCaught(currItem)) {
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

  /**
   * Checks if the specified GameItem should be caught (i.e., if it's within +/- 1 x of the basket).
   */
  private boolean checkIfCaught(GameItem gameItem) {
    return ((gameItem.getxCoordinate() == basket.getxCoordinate()
            || gameItem.getxCoordinate() == basket.getxCoordinate() - 1
            || gameItem.getxCoordinate() == basket.getxCoordinate() + 1)
        && gameItem.getyCoordinate() == basket.getyCoordinate());
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
      nextItem.setPosition(spawnCoordinate, 0);
    } else if (randint < 10) {
      // spawn new Apple
      // TODO: new constructor call; uncomment out when done testing
      Apple nextItem = new Apple(3, 3, appleBMP);
      place(nextItem);
      nextItem.setPosition(spawnCoordinate, 0);

      System.out.println("spawned apple at x " + spawnCoordinate);
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
