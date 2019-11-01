package com.example.game1.presentation.presenter.tappinggame;

import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;


import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;





public class TappingGameManager extends GameManager {

//
//  protected TappingCircle tappingCircle;
//  protected Runner runner;
  protected int numTaps;
  protected int speed;
  protected String info;
  protected int points;
  protected int stars;
  protected boolean gameStarted;
  protected boolean leaveTappingGame;
  protected CountDownTimer timer;
  protected int bestResult;


  public TappingGameManager(int height, int width){
    super(height, width);
    this.game = new Game(Game.GameName.TAPPING);

  }

  public void createGameItems() {}

  public void createGameItems(Bitmap tappingCircleBMP, Bitmap runnerBMP) {
//    tappingCircle = new TappingCircle(tappingCircleBMP, 160, 160, 130, 170);
//    runner = new Runner(runnerBMP, 122, 216, 50, 600);
    numTaps = 0;
    speed = 0;
    info = "Start Tapping";
    points = 0;
    stars = 0;
    gameStarted = false;
    bestResult = 0;
    leaveTappingGame = false;
  }

  public void update() {
    // TODO for Melanie: write this method! GameManager.update is now abstract
    // get and display the best result
//    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
//    bestResult = preferences.getInt("hightScore", 0);
//      if (gameStarted){
//        ++ numTaps;
//        ++ points;
//      }



  }

  public void updateResult(int point, int stars, int taps){
    this.points = point;
    this.stars = stars;
    this.numTaps = taps;
  }

  public void gameOver() {
    // TODO
    game.setNumPoints(points);
    game.setNumStars(stars);
    game.setNumTaps(numTaps);
    super.gameOver();

  }
}
