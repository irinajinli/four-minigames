package com.example.game1.presentation.presenter.jumpinggame;

import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.JumpingMovementInfo;
import com.example.game1.presentation.model.jumpinggame.JumpingResult;
import com.example.game1.presentation.model.jumpinggame.Obstacle;
import com.example.game1.presentation.model.jumpinggame.Star;
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
  public static final int JUMPER_WIDTH = 100;
  public static final int JUMPER_HEIGHT = 200;
  public static final int OBSTACLE_WIDTH = 100;
  public static final int OBSTACLE_HEIGHT = 100;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;


  private Terrain terrain;
  private Jumper jumper;
  private Obstacle obstacle1;
  private Obstacle obstacle2;
  private Obstacle obstacle3;
  private double cameraVelocityX = 450;
  private int numJumped = 0;
  private int numStars = 0;
  private int numTaps = 0;
  private Object skyColor;
  private int skyColorLight;
  private int skyColorDark;
  private boolean isRunning;
  private List obstacleAppearances;
  private List starAppearances;
  private Bitmap terrainAppearance;
  private List<Bitmap> jumperBlueAppearances;
  private List<Bitmap> jumperRedAppearances;
  private List<Bitmap> jumperYellowAppearances;
  private List<Bitmap> jumperAppearances;
  private double numSeconds;

  private int currFrame;

  /** Constructs a JumpingGameManager with the specified height, width, game, and activity. */
  public JumpingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    // this.game = new Game(Game.GameName.JUMPING);
  }

  private Bitmap getNextJumperFrame() {
    currFrame += 1;
    if (currFrame >= jumperAppearances.size()) {
      currFrame = 0;
    }
    return jumperAppearances.get(currFrame);
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

  /**
   * Returns the colour of the sky
   *
   * @return the colour of the sky
   */
  public Object getSkyColor() {
    return this.skyColor;
  }

  public void setSkyColor(Object skyColor) {
    this.skyColor = skyColor;
  }

  /**
   * Returns whether this game is running
   *
   * @return whether this game is running
   */
  public boolean getRunning() {
    return this.isRunning;
  }

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

    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      this.jumperAppearances = jumperBlueAppearances;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
      this.jumperAppearances = jumperRedAppearances;
    } else { // (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW))
      this.jumperAppearances = jumperYellowAppearances;
    }

    if (cust.getColourScheme().equals(Customization.ColourScheme.DARK)) {
      setSkyColor(skyColorDark);
    } else if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      setSkyColor(skyColorLight);
    }

    terrain = new Terrain(getScreenWidth(), getScreenHeight() / 2);
    setTerrainPosition(terrain);
    place(terrain);

    // @TODO changed
    jumper = new Jumper(JUMPER_HEIGHT, JUMPER_WIDTH);
    setJumperPosition(jumper);
    place(jumper);

    obstacle1 = new Obstacle(100, 100);
    setObstaclePosition(obstacle1, getScreenWidth() * 8 / 5);
    place(obstacle1);
    obstacle2 = new Obstacle(100, 100);
    setObstaclePosition(obstacle2, getScreenWidth() * 3 / 5);
    place(obstacle2);
    obstacle3 = new Obstacle(100, 100);
    setObstaclePosition(obstacle3, getScreenWidth() * 6 / 5);
    place(obstacle3);

    Star star = new Star(80, 80);

    setStarPosition(star, getScreenWidth() * 3 / 5);
    place(star);

    setRunning(true);
  }

  /**
   * Adds the specified star to this game at the given position
   *
   * @param xCoordinate the position at which to add the star
   */
  private void setStarPosition(Star star, int xCoordinate) {
    //    Star star = new Star(80, 80, this);
    star.setYCoordinate(terrain.getYCoordinate() - 4 * obstacle1.getHeight());
    star.setXCoordinate(xCoordinate);
    star.setXVelocity(-cameraVelocityX);
    // stars.add(star);
  }

  /** updates all items in this game */
  @Override
  public boolean update() {
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // oldItems list stores GameItem to be removed from gameItems
    List<GameItem> oldItems = new ArrayList<>();
    // note, right now, stars are the only object that get removed
    Result result;

    List<GameItem> gameItems = getGameItems();
    JumpingMovementInfo jumpingMovementInfo =
        new JumpingMovementInfo(
            getScreenHeight(), getScreenWidth(), this.jumper, this.terrain, getNumSeconds());

    for (GameItem item : gameItems) {

        result = item.update(jumpingMovementInfo);
        // process result
        JumpingResult jumpingResult = (JumpingResult) result;
        if (jumpingResult.getNewItems() != null) {
          for (GameItem newItem : jumpingResult.getNewItems()) {
            newItems.add(newItem);
          }
        }
        if (jumpingResult.getOldItems() != null) {
          for (GameItem oldItem : jumpingResult.getOldItems()) {
            oldItems.add(oldItem);
          }
        }
        if (jumpingResult.isObstacleJumped()) {
          numJumped += 1;
        }
        if (jumpingResult.isStarCollected()) {
          numStars += 1;
        }
        if (jumpingResult.isNeedNewStar()) {
          Star newStar = new Star(80, 80);
          autoSetStarPosition(newStar);
          newItems.add(newStar);
        }
        if (jumpingResult.isGameOver()) {
          gameOver();
          return false;
        }
      }

      for (GameItem newItem : newItems) {
        place(newItem);
      }

      for (GameItem oldItem : oldItems) {
        removeItem(oldItem);
      }


    //jumper.setDescription(getNextJumperFrame());
    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  /** Handles the jumper's jump when teh screen is tapped */
  public void onTouchEvent() {
    if (jumper.getYVelocity() == 0) {
      jumper.setYVelocity(-2000);
      jumper.setYAcceleration(5000);
    }
    // numTaps += 1;
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

  public void autoSetStarPosition(Star star) {
    double xCoordinate = getScreenWidth() * 4 / 3 + star.getWidth() / 2 - 80 / 2;
    star.setXCoordinate(xCoordinate);
    // Star star = new Star(80, 80, this);
    double yCoordinate = terrain.getYCoordinate() - 4 * obstacle1.getHeight();
    star.setYCoordinate(yCoordinate);
    star.setXVelocity(-cameraVelocityX);
  }

  public void setJumperPosition(Jumper jumper) {
    jumper.setYCoordinate(terrain.getYCoordinate() - JUMPER_HEIGHT);
    jumper.setXVelocity(-cameraVelocityX + cameraVelocityX); // 0 but left here for modifications
  }

  public void setAppearance(
      List obstacleAppearances,
      List starAppearances,
      Bitmap terrainAppearance,
      List jumperBlueAppearances,
      List jumperYellowAppearances,
      List jumperRedAppearances) {
    this.obstacleAppearances = obstacleAppearances;
    this.starAppearances = starAppearances;
    this.terrainAppearance = terrainAppearance;
    this.jumperBlueAppearances = jumperBlueAppearances;
    this.jumperYellowAppearances = jumperYellowAppearances;
    this.jumperRedAppearances = jumperRedAppearances;
  }

  public void setSkyColors(int skyColorDark, int skyColorLight) {
    this.skyColorDark = skyColorDark;
    this.skyColorLight = skyColorLight;
  }
}
