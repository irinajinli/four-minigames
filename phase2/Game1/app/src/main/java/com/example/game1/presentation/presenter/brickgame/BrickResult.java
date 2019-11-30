package com.example.game1.presentation.presenter.brickgame;

import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.Result;

import java.util.List;

public class BrickResult extends Result {

  double numSeconds;
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

  public void setBall(Ball ball) {
    this.ball = ball;
  }

  public void setBricks(List<Brick> bricks) {
    this.bricks = bricks;
  }

  public void setStars(List<Star> stars) {
    this.stars = stars;
  }

  /**
   * Returns the Paddle in this brick game
   *
   * @return the Paddle in this brick game
   */
  public Paddle getPaddle() {
    return paddle;
  }

  public void setPaddle(Paddle paddle) {
    this.paddle = paddle;
  }

  /**
   * returns the number of successful jumps performed by the player.
   *
   * @return the number of obstacles jumped by the player
   */
  public int getNumBroken() {
    return numBroken;
  }

  /**
   * Sets the number of successful jumps made by this user.
   *
   * @param numBroken the new number of jumps to set
   */
  public void setNumBroken(int numBroken) {
    this.numBroken = numBroken;
  }

  /**
   * returns the number of stars collected by the player in this game
   *
   * @return the number of stars collected by the player in this game
   */
  public int getNumStars() {
    return numStars;
  }

  /**
   * sets the number of stars collected by this player
   *
   * @param numStars the new number to set
   */
  public void setNumStars(int numStars) {
    this.numStars = numStars;
  }
}
