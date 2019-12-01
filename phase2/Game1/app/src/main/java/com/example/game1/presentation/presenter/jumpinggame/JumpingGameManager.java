package com.example.game1.presentation.presenter.jumpinggame;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
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
  private final int JUMPER_WIDTH = 100;
  private final int JUMPER_HEIGHT = 200;
  private final int OBSTACLE_WIDTH = 100;
  private final int OBSTACLE_HEIGHT = 100;
  private final int STAR_WIDTH = 80;
  private final int STAR_HEIGHT = 80;

  private Terrain terrain;
  private Jumper jumper;
  private List<Obstacle> obstacles;
  private double cameraVelocityX = 450;
  private int numJumped = 0;
  private int numStars = 0;
  private int numTaps = 0;

  private boolean isRunning;

  private double numSeconds;

  private int currFrame;

  /** Constructs a JumpingGameManager with the specified height, width, game, and activity. */
  public JumpingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    // this.game = new Game(Game.GameName.JUMPING);
  }

  /**
   * returns the terrain in this jumping game
   *
   * @return the terrain in this jumping game
   */
  public Terrain getTerrain() {
    return terrain;
  }

  /**
   * Returns the jumper in this Jumping game
   *
   * @return the jumper in this Jumping game
   */
  public Jumper getJumper() {
    return jumper;
  }

  /**
   * returns the number of successful jumps performed by the player.
   *
   * @return the number of obstacles jumped by the player
   */
  public int getNumJumped() {
    return numJumped;
  }

  /**
   * Sets the number of successful jumps made by this user.
   *
   * @param numJumped the new number of jumps to set
   */
  public void setNumJumped(int numJumped) {
    this.numJumped = numJumped;
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

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }

//  /**
//   * Returns whether this game is running
//   *
//   * @return whether this game is running
//   */
//  public boolean getRunning() {
//    return this.isRunning;
//  }

  /**
   * Sets whether or not this game is running
   *
   * @param isRunning whether or not this game is running
   */
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }

  public double getNumSeconds() {
    return numSeconds;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();

    terrain = new Terrain(getScreenWidth(), getScreenHeight() / 2);
    setTerrainPosition(terrain);
    place(terrain);

    // @TODO changed
    jumper = new Jumper(JUMPER_HEIGHT, JUMPER_WIDTH);
    setJumperPosition(jumper);
    place(jumper);

    Obstacle obstacle1 = new Obstacle(OBSTACLE_HEIGHT, OBSTACLE_WIDTH);
    setObstaclePosition(obstacle1, getScreenWidth() * 8 / 5);
    Obstacle obstacle2 = new Obstacle(OBSTACLE_HEIGHT, OBSTACLE_WIDTH);
    setObstaclePosition(obstacle2, getScreenWidth() * 3 / 5);
    Obstacle obstacle3 = new Obstacle(OBSTACLE_HEIGHT, OBSTACLE_WIDTH);
    setObstaclePosition(obstacle3, getScreenWidth() * 6 / 5);
    obstacles = new ArrayList<>();
    obstacles.add(obstacle1);
    obstacles.add(obstacle2);
    obstacles.add(obstacle3);
    for (Obstacle obstacle : obstacles) {
      place(obstacle);
    }

    JumpingStar jumpingStar = new JumpingStar(80, 80);

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
    //    JumpingStar jumpingStar = new JumpingStar(80, 80, this);
    jumpingStar.setYCoordinate(terrain.getYCoordinate() - 4 * obstacles.get(0).getHeight());
    jumpingStar.setXCoordinate(xCoordinate);
    jumpingStar.setXVelocity(-cameraVelocityX);
    // stars.add(jumpingStar);
  }

  /** updates all items in this game */
  @Override
  public boolean update() {
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // oldItems list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    JumpingMovementInfo jumpingMovementInfo =
        new JumpingMovementInfo(
            getScreenHeight(), getScreenWidth(), this.jumper, this.terrain, getNumSeconds());

    for (GameItem item : getGameItems()) {
      Result result = item.update(jumpingMovementInfo);
      JumpingResult jumpingResult = (JumpingResult) result;
      if (jumpingResult.getOldItems() != null) {
        for (GameItem oldItem : jumpingResult.getOldItems()) {
          oldItems.add(oldItem);
        }
      }
      isRunning = updateStatistics(jumpingResult);
      if (jumpingResult.isNeedNewStar()) {
        newItems.add(generateNewStar());
      }
    }
    processLists(oldItems, newItems);
    return isRunning;
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
    game.getStatistics().setTaps(numTaps);
    System.out.println(numJumped + "  " + numStars + "  " + numTaps);

    super.gameOver();
  }

  public void setTerrainPosition(Terrain terrain) {
    terrain.setXCoordinate(0);
    terrain.setYCoordinate(getScreenHeight() / 2);
  }

  public void setObstaclexCoordinate(Obstacle obstacle) {
    if (obstacle.getXCoordinate() + obstacle.getWidth() < 0) {
      obstacle.setXCoordinate(getScreenWidth() * 4 / 3);
    }
  }

  public void setObstaclePosition(Obstacle obstacle, double xCoordinate) {
    obstacle.setYCoordinate(terrain.getYCoordinate() - obstacle.getHeight());
    obstacle.setXCoordinate(xCoordinate);
    obstacle.setXVelocity(-cameraVelocityX);
  }

  public void autoSetStarPosition(JumpingStar jumpingStar) {
    double xCoordinate = getScreenWidth() * 4 / 3 + jumpingStar.getWidth() / 2 - 80 / 2;
    jumpingStar.setXCoordinate(xCoordinate);
    // JumpingStar jumpingStar = new JumpingStar(80, 80, this);
    double yCoordinate = terrain.getYCoordinate() - 4 * obstacles.get(0).getHeight();
    jumpingStar.setYCoordinate(yCoordinate);
    jumpingStar.setXVelocity(-cameraVelocityX);
  }

  public void setJumperPosition(Jumper jumper) {
    jumper.setYCoordinate(terrain.getYCoordinate() - JUMPER_HEIGHT);
    jumper.setXVelocity(-cameraVelocityX + cameraVelocityX); // 0 but left here for modifications
  }

  public boolean updateStatistics(JumpingResult result) {
    if (result.isObstacleJumped()) {
      numJumped += 1;
    }
    if (result.isStarCollected()) {
      numStars += 1;
    }
    if (result.isGameOver()) {
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

  public void processLists(List<GameItem> oldItems, List<GameItem> newItems){
    for (GameItem newItem : newItems) {
      place(newItem);
    }
    for (GameItem oldItem : oldItems) {
      removeItem(oldItem);
    }
  }

  public int getJumperWidth() {
    return JUMPER_WIDTH;
  }

  public int getJumperHeight() {
    return JUMPER_HEIGHT;
  }

  public int getObstacleWidth() {
    return OBSTACLE_WIDTH;
  }

  public int getObstacleHeight() {
    return OBSTACLE_HEIGHT;
  }

  public int getStarWidth() {
    return STAR_WIDTH;
  }

  public int getStarHeight() {
    return STAR_HEIGHT;
  }
}
