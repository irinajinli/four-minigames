package com.example.game1.presentation.presenter.jumpinggame;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.JumpingMovementInfo;
import com.example.game1.presentation.model.jumpinggame.JumpingResult;
import com.example.game1.presentation.model.jumpinggame.JumpingStar;
import com.example.game1.presentation.model.jumpinggame.Obstacle;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.model.common.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameManager for a Jumping minigame. Includes an extra variable numDroppedApples and extra
 * methods for handling GameObjects.
 */
public class JumpingGameManager extends GameManager {
  private int jumperWidth;
  private int jumperHeight;
  private int obstacleWidth;
  private int obstacleHeight;
  private int starWidth;
  private int starHeight;

  private Terrain terrain;
  private Jumper jumper;
  private List<Obstacle> obstacles;
  private double cameraVelocityX = 450;
  private int numJumped = 0;
  private int numStars = 0;

  //  private boolean isRunning;

  private double numSeconds;

  /** Constructs a JumpingGameManager with the specified height, width, game, and activity. */
  public JumpingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
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

  public double getNumSeconds() {
    return numSeconds;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    terrain = new Terrain(getScreenWidth(), getScreenHeight() / 2);
    setTerrainPosition(terrain);
    place(terrain);

    // @TODO changed
    jumper = new Jumper(jumperHeight, jumperWidth);
    setJumperPosition(jumper);
    place(jumper);

    Obstacle obstacle1 = new Obstacle(obstacleHeight, obstacleWidth);
    setObstaclePosition(obstacle1, getScreenWidth() * 8 / 5);
    Obstacle obstacle2 = new Obstacle(obstacleHeight, obstacleWidth);
    setObstaclePosition(obstacle2, getScreenWidth() * 3 / 5);
    Obstacle obstacle3 = new Obstacle(obstacleHeight, obstacleWidth);
    setObstaclePosition(obstacle3, getScreenWidth() * 6 / 5);
    obstacles = new ArrayList<>();
    obstacles.add(obstacle1);
    obstacles.add(obstacle2);
    obstacles.add(obstacle3);
    for (Obstacle obstacle : obstacles) {
      place(obstacle);
    }

    JumpingStar jumpingStar = new JumpingStar(getStarWidth(), getStarHeight());

    setStarPosition(jumpingStar, getScreenWidth() * 3 / 5);
    place(jumpingStar);

    setRunning(true);
  }

  /**
   * Adds the specified jumpingStar to this game at the given position
   *
   * @param xCoordinate the position at which to add the jumpingStar
   */
  private void setStarPosition(JumpingStar jumpingStar, int xCoordinate) {
    jumpingStar.setYCoordinate(terrain.getYCoordinate() - 4 * obstacles.get(0).getHeight());
    jumpingStar.setXCoordinate(xCoordinate);
    jumpingStar.setXVelocity(-cameraVelocityX);
  }

  /** updates all items in this game */
  @Override
  public boolean update() {
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // oldItems list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    boolean gameConinue = true;
    JumpingMovementInfo jumpingMovementInfo =
        new JumpingMovementInfo(
            getScreenHeight(), getScreenWidth(), this.jumper, this.terrain, getNumSeconds());
    for (GameItem item : getGameItems()) {
      Result result = item.update(jumpingMovementInfo);
      if (result.getOldItems() != null) {
        for (GameItem oldItem : result.getOldItems()) {
          oldItems.add(oldItem);
        }
      }
      if (result instanceof JumpingResult){
      JumpingResult jumpingResult = (JumpingResult) result;

      gameConinue = updateStatistics(jumpingResult);
      if (jumpingResult.isNeedNewStar()) {
        newItems.add(generateNewStar());
      }}
    }
    processLists(oldItems, newItems);
    return gameConinue;
  }

  /** Handles the jumper's jump when teh screen is tapped */
  public void onTouchEvent() {
    if (jumper.getYVelocity() == 0) {
      jumper.setYVelocity(-2000);
      jumper.setYAcceleration(5000);
    }
  }

  /** Ends this minigame. */
  public void gameOver() {
    setRunning(false);
    // set points here
    game.getStatistics().setPoints(numJumped);
    game.getStatistics().setStars(numStars);
    game.getStatistics().setTaps(getNumTaps());
    System.out.println(numJumped + "  " + numStars + "  " + getNumTaps());

    super.gameOver();
  }

  public void setTerrainPosition(Terrain terrain) {
    terrain.setXCoordinate(0);
    terrain.setYCoordinate(getScreenHeight() / 2);
  }

  public void setObstaclePosition(Obstacle obstacle, double xCoordinate) {
    obstacle.setYCoordinate(terrain.getYCoordinate() - obstacle.getHeight());
    obstacle.setXCoordinate(xCoordinate);
    obstacle.setXVelocity(-cameraVelocityX);
  }

  public void autoSetStarPosition(JumpingStar jumpingStar) {
    double xCoordinate = getScreenWidth() * 4 / 3 + jumpingStar.getWidth() / 2 - 80 / 2;
    jumpingStar.setXCoordinate(xCoordinate);
    double yCoordinate = terrain.getYCoordinate() - 4 * obstacles.get(0).getHeight();
    jumpingStar.setYCoordinate(yCoordinate);
    jumpingStar.setXVelocity(-cameraVelocityX);
  }

  public void setJumperPosition(Jumper jumper) {
    jumper.setYCoordinate(terrain.getYCoordinate() - jumperHeight);
    jumper.setXVelocity(-cameraVelocityX + cameraVelocityX);
  }

  public boolean updateStatistics(JumpingResult result) {
    if (result.isObstacleJumped()) {
      numJumped += 1;
    }
    if (result.isStarCollected()) {
      numStars += 1;
    }
    if (result.isObstacleTouched()) {
      gameOver();
      return false;
    }
    return true;
  }

  public JumpingStar generateNewStar() {
    JumpingStar newJumpingStar = new JumpingStar(80, 80);
    autoSetStarPosition(newJumpingStar);
    return newJumpingStar;
  }

  public void processLists(List<GameItem> oldItems, List<GameItem> newItems) {
    for (GameItem newItem : newItems) {
      place(newItem);
    }
    for (GameItem oldItem : oldItems) {
      removeItem(oldItem);
    }
  }

  public int getStarWidth() {
    return starWidth;
  }

  public int getStarHeight() {
    return starHeight;
  }

  public void setItemSize(
      int jumperWidth,
      int jumperHeight,
      int obstacleWidth,
      int obstacleHeight,
      int starWidth,
      int starHeight) {
    this.jumperWidth = jumperWidth;
    this.jumperHeight = jumperHeight;
    this.obstacleWidth = obstacleWidth;
    this.obstacleHeight = obstacleHeight;
    this.starWidth = starWidth;
    this.starHeight = starHeight;
  }


}
