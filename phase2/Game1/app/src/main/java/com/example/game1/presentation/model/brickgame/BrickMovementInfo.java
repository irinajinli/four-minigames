package com.example.game1.presentation.model.brickgame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;

import java.util.ArrayList;
import java.util.List;

public class BrickMovementInfo extends MovementInfo {

  //double numSeconds;
  private Ball ball;
  private List<Brick> bricks;
  private List<Star> stars;
  private Paddle paddle;
  private List<GameItem> gameItems;
  private List<double[]> starsToAdd;

  private boolean continueGame;

  private int numBroken;
  private int numStars;
  private int numTaps;
  private boolean needNewStar;
  private int newStarX, newStarY;

  // @TODO remove this
  List<Bitmap> starBmps;

  private final double STAR_PROBABILITY = 0.8;



  public BrickMovementInfo(Ball ball, List<Brick> bricks, List<Star> stars, Paddle paddle, List<GameItem> gameItems, int screenHeight, int screenWidth, double numSeconds) {
    super(numSeconds, screenHeight, screenWidth);
    this.ball = ball;
    this.bricks = bricks;
    this.stars = stars;
    this.paddle = paddle;
    this.gameItems = gameItems;
    continueGame = true;
  }

  public void animate() {
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // oldItem list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    // note, right now, stars are the only object that get removed
    starsToAdd = new ArrayList<>();

    // update ball and check if game is over
    if (!updateBall()) {
      continueGame = false;
    }

    updateStars(oldItems);
    updateBricks(oldItems);

    for (GameItem newItem : newItems) {
      gameItems.add(newItem);
    }

    removeOldItems(oldItems);
  }

  public int getNumBroken(){
    return numBroken;
  }

  public int getNumTaps(){
    return numTaps;
  }

  public int getNumStars(){
    return numStars;
  }

  public boolean continueGame() {
    return continueGame;
  }

  private boolean updateBall() {
    if (ball.getXCoordinate() + ball.getWidth() >= getScreenWidth() || ball.getXCoordinate() < 0) {
      ball.setXVelocity(-ball.getXVelocity());
    }
    // out of top boundary
    if (ball.getYCoordinate() < 0) {
      ball.setYVelocity(Math.abs(ball.getYVelocity()));
    }
    // overlapping and not more than halfway through
    if (ball.isOverlapping(paddle)
            && ball.getYCoordinate() + ball.getHeight()
            < paddle.getYCoordinate() + paddle.getHeight() / 2) {
      ball.setYVelocity((-Math.abs(ball.getYVelocity())));
    }
    if (ball.getYCoordinate() > getScreenHeight()) {
      return false;
    }
    return true;
  }

  private void updateStars(List<GameItem> oldItems) {
    for (Star star : stars) {
      if (ball.isOverlapping(star)) {
        oldItems.add(star);
        numStars += 1;
      }
    }
  }

  public void updateBricks(List<GameItem> oldItems) {
    for (Brick brick : bricks) {
      if (ball.isOverlapping(brick)) {
        boolean broken = brick.damageBrick();
        if (broken) {
          oldItems.add(brick);
          numBroken++;
          if (Math.random() > STAR_PROBABILITY) {
            double [] coordinates = {brick.getXCoordinate(), brick.getYCoordinate()};
            starsToAdd.add(coordinates);
          }
        } else if (brick.needChangeAppearance()) {
          // brick.setDescription(brickDamagedBmp);
        }
        ball.setYVelocity(Math.abs(ball.getYVelocity()));
      }
    }
  }

  public void processItemChanges(List<GameItem> newItems, List<GameItem> oldItems) {}

  public void removeOldItems(List<GameItem> oldItems) {
    for (GameItem oldItem : oldItems) {
      gameItems.remove(oldItem);
      if (oldItem instanceof Brick) {
        bricks.remove(oldItem);
      } else if (oldItem instanceof Star) {
        stars.remove(oldItem);
      }
    }
  }

  public List<double[]> getStarsToAdd(){
    return starsToAdd;
  }
}
