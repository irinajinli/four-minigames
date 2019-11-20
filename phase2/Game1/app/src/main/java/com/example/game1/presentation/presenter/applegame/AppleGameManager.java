package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItemOld;
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

  /**
   * Constructs an AppleGameManager with the specified height and width.
   *
   * @param height the height of the AppleGameManager
   * @param width the width of the AppleGameManager
   */
  public AppleGameManager(int height, int width, Game game) {
    super(height, width, game);
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // TODO: delete this method
    GameItemsBuilder gib = new GameItemsBuilder(game.getCustomization());
    gib.createBackground();
    gib.createBasket();
    gib.createPointsCounter();
    gib.placeItems(this);
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
    if (numDroppedApples >= 15) {
      //      MainThread.isRunning = false;
      // tell GameView to change GameView.thread.isRunning to false
      gameOver();
      return false;
    }

    for (int i = 0; i < getGameItems().size(); i++) {
      GameItemOld currItem = getGameItems().get(i);

      // move each GameItemOld
      currItem.move();

      if (!(currItem instanceof Basket)) {
        // check if each non-Basket GameItemOld is off screen; remove if necessary
        if (currItem.getY() > getGridHeight()) {
          dropGameItem(currItem);
        }

        // check if currItem has been caught; remove if necessary
        if ((currItem.getX() == basket.getX()
                || currItem.getX() == basket.getX() - 1
                || currItem.getX() == basket.getX() + 1)
            && currItem.getY() == basket.getY()) {
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
  private void dropGameItem(GameItemOld currItem) {
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
