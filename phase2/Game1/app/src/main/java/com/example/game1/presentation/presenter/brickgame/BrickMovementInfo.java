package com.example.game1.presentation.presenter.brickgame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.common.Result;

import java.util.ArrayList;
import java.util.List;

public class BrickMovementInfo extends MovementInfo {

  //double numSeconds;
  private Ball ball;
  private List<Brick> bricks;
  private List<Star> stars;
  private Paddle paddle;
  private List<GameItem> gameItems;

  private int screenHeight;
  private int screenWidth;
  private boolean continueGame;

  private int numBroken;
  private int numStars;
  private int numTaps;

  // @TODO remove this
  List<Bitmap> starBmps;

  private final double STAR_PROBABILITY = 0.8;

  public BrickMovementInfo(Ball ball, List<Brick> bricks, List<Star> stars, Paddle paddle, List<GameItem> gameItems, int screenHeight, int screenWidth, List<Bitmap> starBmps, double numSeconds) {
    super(numSeconds);
    this.ball = ball;
    this.bricks = bricks;
    this.stars = stars;
    this.paddle = paddle;
    this.gameItems = gameItems;
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.starBmps = starBmps;
   // this.numSeconds = numSeconds;
    continueGame = true;
  }

  public void animate() {
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // oldItem list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    // note, right now, stars are the only object that get removed

    //processItemChanges(newItems, oldItems);

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
  boolean continueGame() {
    return continueGame;
  }

  private boolean updateBall() {
    if (ball.getXCoordinate() + ball.getWidth() >= screenWidth || ball.getXCoordinate() < 0) {
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
    if (ball.getYCoordinate() > screenHeight) {
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
            Star star =
                    new Star(BrickGameManager.STAR_WIDTH, BrickGameManager.STAR_HEIGHT, starBmps);
            star.setPosition(
                    brick.getXCoordinate() + brick.getWidth() / 2 - BrickGameManager.STAR_WIDTH / 2,
                    brick.getYCoordinate());
            gameItems.add(star);
            stars.add(star);
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

//  public double getNumSeconds() {
//    return numSeconds;
//  }
}
