package com.example.game1.presentation.presenter.tappinggame;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.tappinggame.Runner;
import com.example.game1.presentation.model.tappinggame.SpeedDisplayer;
import com.example.game1.presentation.model.tappinggame.StarDisplayer;
import com.example.game1.presentation.model.tappinggame.TapCounter;
import com.example.game1.presentation.model.tappinggame.TappingCircle;
import com.example.game1.presentation.model.tappinggame.TappingMovementInfo;
import com.example.game1.presentation.model.tappinggame.TimerDisplayer;
import com.example.game1.presentation.presenter.common.GameManager;

import java.util.List;

public class TappingGameManager extends GameManager {

  private int runnerWidth;
  private int runnerHeight;

  public TappingCircle tappingCircle;
  public Runner runner;
  public TapCounter tapCounter;
  public TimerDisplayer timerDisplayer;
  public StarDisplayer starDisplayer;
  public SpeedDisplayer speedDisplayer;
  private int secondsLeft;
  private int tappingSpeed;

  @Override
  public int getGridWidth() {
    return gridWidth;
  }

  @Override
  public int getGridHeight() {
    return gridHeight;
  }

  private int gridWidth;
  private int gridHeight;

  /** Constructs a TappingGameManager with the specified height, width, game, and activity. */
  public TappingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
  }

  /** execute animation on each item in myFishTank and update myFishTank accordingly. */
  public boolean update() {
    List<GameItem> Items = getGameItems();
    TappingMovementInfo tappingMovementInfo =
        new TappingMovementInfo(
            getScreenHeight(),
            getScreenWidth(),
            tappingSpeed,
            secondsLeft,
            getNumTaps(),
            getNumSeconds());
    for (GameItem item : Items) {
      item.update(tappingMovementInfo);
    }
    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  public void createGameItems() {
    this.tappingCircle = new TappingCircle(0.0, 0.0);
    place(tappingCircle);
    this.runner = new Runner(0.0, getScreenHeight() * 3 / 4, runnerWidth, runnerHeight);
    place(runner);
    this.tapCounter = new TapCounter(getGridWidth() * 1 / 3, getGridHeight() * 5 / 8);
    place(tapCounter);
    this.timerDisplayer = new TimerDisplayer(getGridWidth() * 1 / 3, getGridHeight() * 5 / 8 + 1);
    place(timerDisplayer);
    this.speedDisplayer = new SpeedDisplayer(getGridWidth() * 1 / 3, getGridHeight() * 5 / 8 + 2);
    place(speedDisplayer);
    this.starDisplayer = new StarDisplayer(getGridWidth() * 1 / 3, getGridHeight() * 5 / 8 + 3);
    place(starDisplayer);
  }

  public void gameOver() {
    // TODO
    game.getStatistics().setPoints(tapCounter.getNumTaps());
    game.getStatistics().setStars(starDisplayer.getNumStar());
    game.getStatistics().setTaps(tapCounter.getNumTaps());
    super.gameOver();
  }

  public void setSecondsLeft(int secondsLeft) {
    this.secondsLeft = secondsLeft;
  }

  public void setTappingSpeed(int tappingSpeed) {
    this.tappingSpeed = tappingSpeed;
  }

  public void setItemSize(int runnerWidth, int runnerHeight) {
    this.runnerWidth = runnerWidth;
    this.runnerHeight = runnerHeight;
  }

  public void setGridWidthHeight(int gridWidth, int gridHeight) {
    this.gridWidth = gridWidth;
    this.gridHeight = gridHeight;
  }
}
