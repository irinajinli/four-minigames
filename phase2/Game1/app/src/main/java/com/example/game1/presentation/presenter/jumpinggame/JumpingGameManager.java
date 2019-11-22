package com.example.game1.presentation.presenter.jumpinggame;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.jumpinggame.Obstacle;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.view.jumpinggame.GameObject;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Star;
import com.example.game1.presentation.presenter.common.Result;
import java.util.ArrayList;
import java.util.List;

public class JumpingGameManager extends GameManager {
  /**
   * A GameManager for a Jumping minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling GameObjects.
   */

  // JumperSprite jumperSprite;
  //  private List<Obstacle> obstacles;
  //  private List<Star> stars;
  //  private List<GameObject> queuedForRemoval;

  private Terrain terrain;

  private Jumper jumper;
  private Obstacle obstacle1;
  private Obstacle obstacle2;
  private Obstacle obstacle3;

  private double cameraVelocityX = 450;
  private int numJumped = 0;
  private int numStars = 0;



  private int numTaps = 0;
  private int skyColor;
  private boolean isRunning;
  private Bitmap obstacleBMP;
  private Bitmap starBMP;
  private Bitmap terrainBMP;
  private Bitmap jumperBlueBMP;
  private Bitmap jumperRedBMP;
  private Bitmap jumperYellowBMP;
  private Bitmap jumperNinjaMP;
  private Bitmap jumperBMP;

  private double numOfSeconds;

  /**
   * Constructs a JumpingGameManager with the specified height and width.
   *
   * @param height the height of the JumpingGameManager
   * @param width the width of the JumpingGameManager
   */
  public JumpingGameManager(int height, int width, Game game) {
    super(height, width, game);
    //this.game = new Game(Game.GameName.JUMPING);
  }

  /**
   * returns the obstacles in this jumping game
   *
   * @return the obstacles in this jumping game
   */
  //  public List<Obstacle2> getObstacles(){
  //      return obstacles;
  //  }

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
   * Returns the stars currently in this jumping game
   *
   * @return the stars currently in this jumping game
   */
  //  public List<Star> getStars(){
  //      return stars;
  //    }

  /**
   * returns the number of successful jumps performed by the player.
   *
   * @return the number of obstacles jumped by the player
   */
  public int getNumJumped() {
    return numJumped;
  }

  /**
   * Sets the number of successful jumps made by this user
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

  /**
   * Sets whether or not this game is running
   *
   * @param isRunning whether or not this game is running
   */
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }



  /**
   * Returns whether this game is running
   *
   * @return whether this game is running
   */
  public boolean getRunning() {
    return this.isRunning;
  }
  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();
    setTheme(cust.getColourScheme());
    if(cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)){
      this.jumperBMP = jumperBlueBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)){
      this.jumperBMP = jumperRedBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)){
      this.jumperBMP = jumperYellowBMP;
    } else {
      this.jumperBMP = jumperNinjaMP;
    }



    terrain = new Terrain(getScreenWidth(), getScreenHeight() / 2, terrainBMP);
    setTerrainPosition(terrain);
    place(terrain);
    //    terrain.setxCoordinate(0);
    //    terrain.setyCoordinate(getScreenHeight() / 2);

    jumper = new Jumper(200, 100, jumperBMP);
    setJumperPosition(jumper);
    place(jumper);
    // setCharacterColor(cust.getCharacterColour());
    //    jumper.setPositionY(terrain.getPositionY() - jumper.getHeight());
    //    jumper.setVelocityX(-cameraVelocityX + cameraVelocityX);

    // obstacles = new ArrayList<>();



    obstacle1 = new Obstacle(100, 100, obstacleBMP);
    setObstaclePosition(obstacle1, getScreenWidth() * 8 / 5);
    place(obstacle1);
    obstacle2 = new Obstacle(100, 100, obstacleBMP);
    setObstaclePosition(obstacle2, getScreenWidth() * 3 / 5);
    place(obstacle2);
    obstacle3 = new Obstacle(100, 100, obstacleBMP);
    setObstaclePosition(obstacle3, getScreenWidth() * 6 / 5);
    place(obstacle3);
    //    obstacles.add(generateObstacle(getScreenWidth() * 8 / 5));
    //    obstacles.add(generateObstacle(getScreenWidth() * 3 / 5));
    //    obstacles.add(generateObstacle(getScreenWidth() * 6 / 5));

    Star star = new Star(80, 80, starBMP);
    // stars = new ArrayList<>();
    setStarPosition(star, getScreenWidth() * 3 / 5);
    place(star);


    // queuedForRemoval = new ArrayList<>();
    setRunning(true);
  }

  //  /**
  //   * Queues this game object to be removed from the game
  //   * @param gameObject the game object to be removed
  //   */
  //  public void queueForRemoval(GameObject gameObject) {
  //    queuedForRemoval.add(gameObject);
  //    queuedForRemoval.add(gameObject);
  //  }

//  /**
//   * Sets teh colour of the jumper
//   *
//   * @param characterColour the colour to set
//   */
//  private void setCharacterColor(Customization.CharacterColour characterColour) {
//    this.jumper.characterColour = characterColour;
//  }

  /**
   * Set the theme of this game
   *
   * @param theme the theme to set
   */
  private void setTheme(Customization.ColourScheme theme) {
    if (theme.equals(Customization.ColourScheme.DARK)) {
      skyColor = Color.rgb(83, 92, 104);
    } else if (theme.equals((Customization.ColourScheme.LIGHT))) {
      skyColor = Color.rgb(223, 249, 251);
    } else {
      skyColor = Color.rgb(204, 255, 255);
    }
  }

  /**
   * Returns the colour of the sky
   *
   * @return the colour of the sky
   */
  public int getSkyColor() {
    return this.skyColor;
  }

  /**
   * Adds the specified star to this game at the given position
   *
   * @param xCoordinate the position at which to add the star
   */
  public void setStarPosition(Star star, int xCoordinate) {
    //    Star star = new Star(80, 80, this);
    star.setyCoordinate(terrain.getyCoordinate() - 4 * obstacle1.getHeight());
    star.setxCoordinate(xCoordinate);
    star.setxVelocity(-cameraVelocityX);
    // stars.add(star);
  }

  //  private Obstacle generateObstacle(int px) {
  //    Obstacle2 obstacle = new Obstacle2(100, 100);
  //    if (obstacleHeight == 0){
  //      obstacleHeight = obstacle.getHeight();
  //    }

  //
  //    obstacle.setPositionY(terrain.getPositionY() - obstacle.getHeight());
  //    obstacle.setPositionX(px);
  //    obstacle.setVelocityX(-cameraVelocityX);
  //    return obstacle;
  //  }

  /** updates all items in this game */
  @Override
  public boolean update() {
    //    jumper.update();
    //    terrain.update();
    // newItems list stores GameItem to be added to gameItems
    List<GameItem> newItems = new ArrayList<>();
    // outItem list stores GameItem to be removed from gameIems
    List<GameItem> outItems = new ArrayList<>();
    // note, right now, stars are the only object that get removed
    Result result;

    List<GameItem> gameItems = getGameItems();
    JumpingImportInfo jumpingImportInfo =
            new JumpingImportInfo(getScreenHeight(), getScreenWidth(), this.jumper, this.terrain, getNumOfSeconds());

    for (GameItem item : gameItems) {
      if (item instanceof AnimatedGameItem) {
        result = ((AnimatedGameItem) item).animate(jumpingImportInfo);
        // process result
        JumpingResult jumpingResult = (JumpingResult)result;
        if (jumpingResult.getInItems() != null) {
          for (GameItem inItem : jumpingResult.getInItems()) {
            newItems.add(inItem);
          }
        }
        if (jumpingResult.getOutItems() != null) {
          for (GameItem outItem : jumpingResult.getOutItems()) {
            outItems.add(outItem);
          }
        }
        if (jumpingResult.isObstacleJumped()) {
          numJumped += 1;
        }
        if (jumpingResult.isStarCollected()) {
          numStars += 1;
        }
        if (jumpingResult.isNeedNewStar()) {
          Star newStar = new Star(80, 80, starBMP);
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

      for (GameItem outItem : outItems) {
        removeItem(outItem);
      }

      //    queuedForRemoval = new ArrayList<>();
      //
      //    for (Star star : stars) {
      //      star.update();
      //    }
      //
      //    for (Obstacle obstacle : obstacles) {
      //      obstacle.update();
      //    }
    }
    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  /** Handles the jumper's jump when teh screen is tapped */
  public void onScreenTap() {
    if (jumper.getyVelocity() == 0) {
      jumper.setyVelocity(-2000);
      jumper.setyAcceleration(5000);
    }
    //numTaps += 1;
  }

  //  /**
  //   * returns the width of the screen
  //   * @return the width of the screen
  //   */
  //  public int getScreenWidth() {
  //    return Resources.getSystem().getDisplayMetrics().widthPixels;
  //  }

  //  /**
  //   * REturns the height of the screen
  //   *
  //   * @return the height of the screen
  //   */
  //  public int getScreenHeight() {
  //    return Resources.getSystem().getDisplayMetrics().heightPixels;
  //  }

  /** Ends this minigame. */
  public void gameOver() {
    setRunning(false);
    // set points here
    game.setNumPoints(numJumped);
    game.setNumStars(numStars);
    game.setNumTaps(numTaps);
    System.out.println(numJumped + "  " + numStars + "  " + numTaps);

    super.gameOver();
  }

  public void setTerrainPosition(Terrain terrain) {
    terrain.setxCoordinate(0);
    terrain.setyCoordinate(getScreenHeight() / 2);
  }

  public void setObstaclexCoordinate(Obstacle obstacle) {
    if (obstacle.getxCoordinate() + obstacle.getWidth() < 0) {
      obstacle.setxCoordinate(getScreenWidth() * 4 / 3);
    }
  }

  public void setObstaclePosition(Obstacle obstacle, double xCoordinate) {
    obstacle.setyCoordinate(terrain.getyCoordinate() - obstacle.getHeight());
    obstacle.setxCoordinate(xCoordinate);
    obstacle.setxVelocity(-cameraVelocityX);
  }

  public void autoSetStarPosition(Star star) {
    double xCoordinate = getScreenWidth() * 4 / 3 + star.getWidth() / 2 - 80 / 2;
    star.setxCoordinate(xCoordinate);
    // Star star = new Star(80, 80, this);
    double yCoordinate = terrain.getyCoordinate() - 4 * obstacle1.getHeight();
    star.setyCoordinate(yCoordinate);
    //                star.setPositionY(terrain.getPositionY() - 4 * obstacles.get(0).getHeight());
    //        star.setPositionX(xp);

    // stars.add(star);
    star.setxVelocity(-cameraVelocityX);
  }

  public void setJumperPosition(Jumper jumper) {
    jumper.setyCoordinate(terrain.getyCoordinate() - jumper.getHeight());
    jumper.setxVelocity(-cameraVelocityX + cameraVelocityX);
  }

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }
  //  public void processResult(Result result){
  //    if (result.getInItems() != null){
  //
  //    }
  //  }

  public void setBMPfiles(Bitmap obstacleBMP, Bitmap starBMP, Bitmap terrainBMP,
                          Bitmap jumperBlueBMP, Bitmap jumperYellowBMP,
                          Bitmap jumperRedBMP, Bitmap jumperNinjaBMP){
    this.obstacleBMP = obstacleBMP;
    this.starBMP = starBMP;
    this.terrainBMP = terrainBMP;
    this.jumperBlueBMP = jumperBlueBMP;
    this.jumperYellowBMP = jumperYellowBMP;
    this.jumperRedBMP = jumperRedBMP;
    this.jumperNinjaMP = jumperNinjaBMP;
  }

  public double getNumOfSeconds() {
    return numOfSeconds;
  }

  public void setNumOfSeconds(double numOfSeconds) {
    this.numOfSeconds = numOfSeconds;
  }

}
