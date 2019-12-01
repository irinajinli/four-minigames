package com.example.game1.presentation.presenter.applegame;



import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.Statistics;
import com.example.game1.presentation.model.applegame.Apple;
import com.example.game1.presentation.model.applegame.AppleMovementInfo;
import com.example.game1.presentation.model.applegame.AppleResult;
import com.example.game1.presentation.model.applegame.AppleStar;
import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.model.common.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppleGameManager extends GameManager {
  public static final int APPLE_WIDTH = 100;
  public static final int APPLE_HEIGHT = 100;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;
  public static final int BASKET_WIDTH = 100;
  public static final int BASKET_HEIGHT = 100;
  /** A GameManager for an Apple minigame. */


  private double numSeconds;

  private Basket basket;
  private PointsCounter points;
  private LivesCounter livesCounter;
  private int numTaps;
  private int numCaughtStars = 0;


  /** Constructs an AppleGameManager with the specified height, width, game, and activity. */
  public AppleGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
  }

  void setBasket(Basket basket) {
    this.basket = basket;
  }

  void setPointsCounter(PointsCounter pointsCounter) {
    this.points = pointsCounter;
  }

  void setLivesCounter(LivesCounter livesCounter) {
    this.livesCounter = livesCounter;
  }


  public int getNumTaps() {
    return numTaps;
  }

  public void incrementNumTaps() {
    numTaps += 1;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    AppleItemsBuilder builder = new AppleItemsBuilder(game.getCustomization());
    builder.createPointsCounter();
    builder.createLivesCounter();
    builder.createBasket();
    builder.placeItems(this);
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
    if (livesCounter.getLivesRemaining() <= 0) {
      gameOver();
      return false;
    }

    // oldItems list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    // note, right now, stars are the only object that get removed
    Result result;

    List<GameItem> gameItems = getGameItems();
    AppleMovementInfo appleMovementInfo =
        new AppleMovementInfo(getScreenWidth(), getScreenHeight(), basket, getNumSeconds());
    for (GameItem item : gameItems) {
      result = item.update(appleMovementInfo);

      if (result.getOldItems() != null) {
        for (GameItem oldItem : result.getOldItems()) {
          oldItems.add(oldItem);
        }
      }

      if (result instanceof AppleResult) {
        AppleResult ar = (AppleResult) result;
        if (ar.isAppleCollected()) {
          catchApple();
        }
        if (ar.isAppleDropped()) {
          livesCounter.subtractLife();
        }
        if (ar.isStarCollected()){
          catchStar();
        }
      }

    }

    for (GameItem oldItem : oldItems) {
      removeItem(oldItem);
    }
    spawnNew();

    return true;
  }

  private void catchApple() {
    points.addPoints(1);
  }

  private void catchStar() {
    numCaughtStars += 1;
  }

  private void dropGameItem(GameItem currItem) {
    removeItem(currItem);
  }

  private void spawnNew() {
    // get a random x-coordinate to spawn the new Apple/JumpingStar at
    Random randCoordinate = new Random();
    int spawnCoordinate = randCoordinate.nextInt(getGridWidth() - 80);

    // decide whether to spawn an Apple or a JumpingStar or nothing
    Random randItem = new Random();
    int randint = randItem.nextInt(200);
    if (randint < 2) {
      spawnStar(spawnCoordinate);
    } else if (randint < 9) {
      spawnApple(spawnCoordinate);
    }
    // else spawn nothing
  }

  private void spawnStar(int spawnCoordinate) {
    AppleStar nextItem = new AppleStar(80, 80);
    nextItem.setYVelocity(250);
    place(nextItem);
    nextItem.setPosition(spawnCoordinate, 0);
  }

  private void spawnApple(int spawnCoordinate) {
    Apple nextItem = new Apple(80, 80);
    nextItem.setYVelocity(350);
    place(nextItem);
    nextItem.setPosition(spawnCoordinate, 0);
  }

  /** Ends this minigame. */
  public void gameOver() {
    Statistics gameStatistics = game.getStatistics();
    gameStatistics.setPoints(gameStatistics.getPoints() + points.getNumPoints());
    gameStatistics.setStars(gameStatistics.getStars() + numCaughtStars);
    gameStatistics.setTaps(gameStatistics.getTaps() + numTaps);
    super.gameOver();
  }

  public double getNumSeconds() {
    return numSeconds;
  }
}
