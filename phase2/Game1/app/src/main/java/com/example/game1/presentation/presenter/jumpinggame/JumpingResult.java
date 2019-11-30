package com.example.game1.presentation.presenter.jumpinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.Result;

import java.util.ArrayList;
import java.util.List;

public class JumpingResult extends Result {



  /** if result need to be updated */
  boolean obstacleJumped = false;

  /** if result need to be updated */
  boolean starCollected = false;

  /** if result need to be updated */
  boolean needNewStar = false;

  /** if game is over */
  boolean gameOver = false;



  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public boolean isObstacleJumped() {
    return obstacleJumped;
  }

  public void setObstacleJumped(boolean obstacleJumped) {
    this.obstacleJumped = obstacleJumped;
  }

  public boolean isStarCollected() {
    return starCollected;
  }

  public void setStarCollected(boolean starCollected) {
    this.starCollected = starCollected;
  }

  public boolean isNeedNewStar() {
    return needNewStar;
  }

  public void setNeedNewStar(boolean needNewStar) {
    this.needNewStar = needNewStar;
  }
}
