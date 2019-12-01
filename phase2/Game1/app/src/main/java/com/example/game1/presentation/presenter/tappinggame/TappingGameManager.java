package com.example.game1.presentation.presenter.tappinggame;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.Result;
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

  public static final double RUNNER_WIDTH_MULTIPLIER = 0.2;
  public static final double RUNNER_HEIGHT_MULTIPLIER = 0.2;
  private int runnerWidth;
  private int runnerHeight;

  public TappingCircle tappingCircle;
  public Runner runner;
  public TapCounter tapCounter;
  public TimerDisplayer timerDisplayer;
  public StarDisplayer starDisplayer;
  public SpeedDisplayer speedDisplayer;
//  private Object tappingCircleAppearance;
//  private List runnerAppearances;
//  private List yellowPugAppearances;
//  private List blueBirdAppearances;
//  private List redFishAppearances;
  private boolean canRun;
//  private Object skyColor;
//  private Object skyColorDark;
//  private Object skyColorLight;

  private int secondsLeft;
  private  int tappingSpeed;



  private double numSeconds;


  private int numTaps;


  /** Constructs a TappingGameManager with the specified height, width, game, and activity. */
  public TappingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    this.canRun = true;
  }

  public boolean isCanRun() {
    return canRun;
  }

  public void setCanRun(boolean canRun) {
    this.canRun = canRun;
  }

//  public void setAppearance(
//      Object tappingCircleAppearance,
//      List yellowPugAppearances,
//      List blueBirdAppearances,
//      List redFishAppearances) {
//    this.tappingCircleAppearance = tappingCircleAppearance;
//    this.yellowPugAppearances = yellowPugAppearances;
//    this.blueBirdAppearances = blueBirdAppearances;
//    this.redFishAppearances = redFishAppearances;
//    this.runnerAppearances = yellowPugAppearances;
//  }

  /** execute animation on each item in myFishTank and update myFishTank accordingly. */
  public boolean update() {
    //Result result;
    List<GameItem> Items = getGameItems();
    TappingMovementInfo tappingMovementInfo =
            new TappingMovementInfo(
                    getScreenHeight(), getScreenWidth(), tappingSpeed, secondsLeft, numTaps, numSeconds);
    for (GameItem item : Items) {
      item.update(tappingMovementInfo);
      }
    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  public void createGameItems() {



    this.tappingCircle = new TappingCircle(0.0, 0.0);
    place(tappingCircle);
    this.runner = new Runner(0.0, 1550.0, runnerWidth, runnerHeight);
    // runner.setPosition(0, 1550);
    place(runner);
    this.tapCounter = new TapCounter(10.0, 30.0);
    place(tapCounter);
    this.timerDisplayer = new TimerDisplayer(10.0, 31.0);
    place(timerDisplayer);
    this.speedDisplayer = new SpeedDisplayer(10.0, 32.0);
    place(speedDisplayer);
    this.starDisplayer = new StarDisplayer(10.0, 33.0);
    place(starDisplayer);
  }

  public void gameOver() {
    // TODO
    game.getStatistics().setPoints(tapCounter.getNumTaps());
    game.getStatistics().setStars(starDisplayer.getNumStar());
    game.getStatistics().setTaps(tapCounter.getNumTaps());
    super.gameOver();
  }

//  public void setSkyColors(Object skyColorDark, Object skyColorLight) {
//    this.skyColorDark = skyColorDark;
//    this.skyColorLight = skyColorLight;
//  }

//  public Object getSkyColor() {
//    return skyColor;
//  }
//
//  public void setSkyColor(Object skyColor) {
//    this.skyColor = skyColor;
//  }

  public int getSecondsLeft() {
    return secondsLeft;
  }

  public void setSecondsLeft(int secondsLeft) {
    this.secondsLeft = secondsLeft;
  }

  public int getTappingSpeed() {
    return tappingSpeed;
  }

  public void setTappingSpeed(int tappingSpeed) {
    this.tappingSpeed = tappingSpeed;
  }

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }

  public double getNumSeconds() {
    return numSeconds;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }
  public void setRunnerWidthAndHeight(int runnerWidth, int runnerHeight){
    this.runnerWidth = runnerWidth;
    this.runnerHeight = runnerHeight;
  }

  public void updateTappingMovementInfo(TappingMovementInfo tmi){
    tmi.setNumTaps(this.numTaps);
    tmi.setSecondsLeft(this.secondsLeft);
    tmi.setTappingSpeed(this.tappingSpeed);
  }
}
