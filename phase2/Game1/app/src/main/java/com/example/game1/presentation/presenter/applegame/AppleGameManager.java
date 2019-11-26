package com.example.game1.presentation.presenter.applegame;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.jumpinggame.Star;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.model.applegame.Apple;
import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;

import java.util.List;
import java.util.Random;

public class AppleGameManager extends GameManager {
  /**
   * A GameManager for an Apple minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling Apples.
   */

  // TODO: new for colors
  private int skyColor;

  private int skyColorDark = Color.BLACK;
  private int skyColorLight = Color.LTGRAY;

  private double numOfSeconds;

  private Basket basket;
  private PointsCounter points;
  private LivesCounter livesCounter;
  private int numCaughtStars = 0;

  // TODO: new
  private List<Bitmap> appleBmps;
  private List<Bitmap> starBmps;
  private List<Bitmap> basketBmps;
  private List<Bitmap> basketBlueBmps;
  private List<Bitmap> basketYellowBmps;

  public static final int APPLE_WIDTH = 100;
  public static final int APPLE_HEIGHT = 100;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;
  public static final int BASKET_WIDTH = 100;
  public static final int BASKET_HEIGHT = 100;

  /** Constructs an AppleGameManager with the specified height, width, game, and activity. */
  public AppleGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
  }

  // TODO: new
  public void setBMPFiles(
      List<Bitmap> appleBmps,
      List<Bitmap> starBmps,
      List<Bitmap> basketBmps,
      List<Bitmap> basketBlueBmps,
      List<Bitmap> basketYellowBmps) {
    this.appleBmps = appleBmps;
    this.starBmps = starBmps;
    this.basketBmps = basketBmps;
    this.basketBlueBmps = basketBlueBmps;
    this.basketYellowBmps = basketYellowBmps;
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // TODO: delete this method?
    AppleItemsBuilder builder = new AppleItemsBuilder(game.getCustomization());
    builder.createPointsCounter();
    builder.createLivesCounter();
    builder.createBasket(basketBmps, basketBlueBmps, basketYellowBmps);
    builder.setTheme(this);
    builder.placeItems(this);
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }

  public void setPointsCounter(PointsCounter pointsCounter) {
    this.points = pointsCounter;
  }

  public void setLivesCounter(LivesCounter livesCounter) {
    this.livesCounter = livesCounter;
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
    if (livesCounter.getLivesRemaining() <= 0) {
      gameOver();
      return false;
    }

    for (int i = 0; i < getGameItems().size(); i++) {
      GameItem currItem = getGameItems().get(i);
      if (currItem instanceof Apple) {
        // AnimatedGameItem currItem = (AnimatedGameItem) currItem2;
        // move each GameItemOld
        ((AnimatedGameItem) currItem).move();
      } else {
      }

      if (currItem instanceof Star) {
        ((Star) currItem).animate(numOfSeconds);
      }

      if (!(currItem instanceof Basket)) { // TODO is this if statement necessary?
        // check if each non-Basket GameItemOld is off screen; remove if necessary
        if (currItem.getYCoordinate() > getGridHeight()) {
          dropGameItem(currItem);
          livesCounter.subtractLife();
        }

        // check if currItem should be caught
        if (currItem.isOverlapping(basket)) {
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
  }

  /**
   * Checks if the specified GameItem should be caught (i.e., if it's within +/- 1 x of the basket).
   */
  private boolean checkIfCaught(GameItem gameItem) {
    return ((gameItem.getXCoordinate() == basket.getXCoordinate()
            || gameItem.getXCoordinate() == basket.getXCoordinate() - 1
            || gameItem.getXCoordinate() == basket.getXCoordinate() + 1)
        && gameItem.getYCoordinate() == basket.getYCoordinate());
  }

  /** Spawns a new Apple or Star in a random location at the top of the screen. */
  private void spawnNew() {
    // get a random x-coordinate to spawn the new Apple/Star at
    Random randCoordinate = new Random();
    int spawnCoordinate = randCoordinate.nextInt(getGridWidth());

    // decide whether to spawn an Apple or a Star or nothing
    Random randItem = new Random();
    int randint = randItem.nextInt(200);
    if (randint < 2) {
      // spawn new Star
      Star nextItem = new Star(80, 80, starBmps);
      nextItem.setYVelocity(250);
      place(nextItem);
      nextItem.setPosition(spawnCoordinate, 0);

    } else if (randint < 9) {
      // spawn new Apple
      // TODO: new constructor call; uncomment out when done testing
      Apple nextItem = new Apple(80, 80, appleBmps);
      place(nextItem);
      nextItem.setPosition(spawnCoordinate, 0);

      System.out.println("spawned apple at x " + spawnCoordinate);
    }

    // else do nothing
  }

  /** Ends this minigame. */
  public void gameOver() {
    game.getStatistics().setPoints(game.getStatistics().getPoints() + points.getNumPoints());
    game.getStatistics().setStars(game.getStatistics().getStars() + numCaughtStars);
    super.gameOver();
  }

  // TODO: new
  public void setSkyColors(int skyColorDark, int skyColorLight, int skyColorDefault) {
    this.skyColorDark = skyColorDark;
    this.skyColorLight = skyColorLight;
    this.skyColor = skyColorDefault;
  }

  public Object getSkyColor() {
    return skyColor;
  }

  public void setSkyColor(int skyColor) {
    this.skyColor = skyColor;
  }

  public int getSkyColorDark() {
    return skyColorDark;
  }

  public int getSkyColorLight() {
    return skyColorLight;
  }

  public void setNumOfSeconds(double numOfSeconds) {
    this.numOfSeconds = numOfSeconds;
  }
}
