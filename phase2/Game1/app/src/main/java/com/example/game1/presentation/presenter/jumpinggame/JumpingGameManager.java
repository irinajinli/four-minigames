package com.example.game1.presentation.presenter.jumpinggame;


import android.content.res.Resources;
import android.graphics.Color;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.jumpinggame.GameObject;
import com.example.game1.presentation.view.jumpinggame.Jumper;
import com.example.game1.presentation.view.jumpinggame.JumpingGameView;
import com.example.game1.presentation.view.jumpinggame.Obstacle;
import com.example.game1.presentation.view.jumpinggame.Star;
import com.example.game1.presentation.view.jumpinggame.Terrain;

import java.util.ArrayList;
import java.util.List;

public class JumpingGameManager extends GameManager {
  /**
   * A GameManager for a Jumping minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling GameObjects.
   */

  // JumperSprite jumperSprite;
  private List<Obstacle> obstacles;
  private List<Star> stars;
  private List<GameObject> queuedForRemoval;
  private Terrain terrain;
  private Jumper jumper;
  private double cameraVelocityX = 450;
  private int numJumped = 0, numStars = 0, numTaps = 0;
  private int skyColor;
  private boolean isRunning;

  /**
   * Constructs a JumpingGameManager with the specified height and width.
   *
   * @param height the height of the JumpingGameManager
   * @param width the width of the JumpingGameManager
   */
  public JumpingGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.JUMPING);
  }

  /**
   * returns the obstacles in this jumping game
   * @return the obstacles in this jumping game
   */
  public List<Obstacle> getObstacles(){
      return obstacles;
  }

  /**
   * returns the terrain in this jumping game
   * @return the terrain in this jumping game
   */
  public Terrain getTerrain(){
      return terrain;
  }

  /**
   * Returns the jumper in this Jumping game
   * @return the jumper in this Jumping game
   */
  public Jumper getJumper(){
      return jumper;
  }

  /**
   * Returns the stars currently in this jumping game
   * @return the stars currently in this jumping game
   */
  public List<Star> getStars(){
      return stars;
    }

  /**
   * returns the number of successful jumps performed by the player.
   * @return the number of obstacles jumped by the player
   */
  public int getNumJumped(){
    return numJumped;
  }

  /**
   * Sets the number of successful jumps made by this user
   * @param  numJumped the new number of jumps to set
   */
  public void setNumJumped(int numJumped){
    this.numJumped = numJumped;
  }

  /**
   * returns the number of stars collected by the player in this game
   * @return the number of stars collected by the player in this game
   */
  public int getNumStars(){
    return numStars;
  }

  /**
   * sets the number of stars collected by this player
   * @param numStars the new number to set
   */
  public void setNumStars(int numStars){
    this.numStars = numStars;
  }

  /**
   * Sets whether or not this game is running
   * @param isRunning whether or not this game is running
   */
  public void setRunning(boolean isRunning){
    this.isRunning = isRunning;
  }

  /**
   * Returns whether this game is running
   * @return whether this game is running
   */
  public boolean getRunning(){
    return this.isRunning;
  }
  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();
    setTheme(cust.getColourScheme());

    terrain = new Terrain(getScreenWidth(), getScreenHeight() / 2, this);
    terrain.setPositionX(0);
    terrain.setPositionY(getScreenHeight() / 2);

    jumper = new Jumper(100, 200, this);
    setCharacterColor(cust.getCharacterColour());
    jumper.setPositionY(terrain.getPositionY() - jumper.getHeight());
    jumper.setVelocityX(-cameraVelocityX + cameraVelocityX);

    obstacles = new ArrayList<>();
    obstacles.add(generateObstacle(getScreenWidth() * 8 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 3 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 6 / 5));

    stars = new ArrayList<>();
    addStar(getScreenWidth() * 3 / 5);
    queuedForRemoval = new ArrayList<>();
    setRunning(true);
  }

  /**
   * Queues this game object to be removed from the game
   * @param gameObject the game object to be removed
   */
  public void queueForRemoval(GameObject gameObject) {
    queuedForRemoval.add(gameObject);
    queuedForRemoval.add(gameObject);
  }

  /**
   * Sets teh colour of the jumper
   * @param characterColour the colour to set
   */
  private void setCharacterColor(Customization.CharacterColour characterColour) {
    this.jumper.characterColour = characterColour;
  }

  /**
   * Set the theme of this game
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
   * @return the colour of the sky
   */
  public int getSkyColor() {
    return this.skyColor;
  }

  /**
   * Adds the specified star to this game at the given position
   * @param xp the position at which to add the star
   */
  public void addStar(int xp) {
    Star star = new Star(80, 80, this);
    star.setPositionY(terrain.getPositionY() - 4 * obstacles.get(0).getHeight());
    star.setPositionX(xp);
    if (Math.random() > 1.5) { // code to add in the ability for starss to move forward (currently set to >1.5 which disables this functionality)
      star.setVelocityX(-cameraVelocityX * 3 / 5);
    }
    else{
        star.setVelocityX(-cameraVelocityX);
    }
    stars.add(star);
  }

  private Obstacle generateObstacle(int px) {
    Obstacle obstacle = new Obstacle(100, 100, this);

    obstacle.setPositionY(terrain.getPositionY() - obstacle.getHeight());
    obstacle.setPositionX(px);
    obstacle.setVelocityX(-cameraVelocityX);
    return obstacle;
  }

  /**
   * updates all items in this game
   */
  @Override
  public void update() {
    jumper.update();
    terrain.update();

    // note, right now, stars are the only object that get removed
    for (GameObject gameObject : queuedForRemoval) {
      stars.remove(gameObject);
    }

    queuedForRemoval = new ArrayList<>();

    for (Star star : stars) {
      star.update();
    }

    for (Obstacle obstacle : obstacles) {
      obstacle.update();
    }
  }

  /**
   * Handles the jumper's jump when teh screen is tapped
   */
  public void onScreenTap() {
    if (jumper.getVelocityY() == 0) {
      jumper.setVelocityY(-2000);
      jumper.setAccelerationY(5000);
    }
    numTaps += 1;
  }

  /**
   * returns the width of the screen
   * @return the width of the screen
   */
  public int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  /**
   * REturns the height of the screen
   *
   * @return the height of the screen
   */
  public int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

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
}
