package com.example.game1.presentation.presenter.brickgame;


import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.common.Result;
import java.util.ArrayList;
import java.util.List;

public class BrickGameManager extends GameManager {
  /**
   * A GameManager for a Brick Breaking minigame. Includes extra variables and methods for
   * handling game-specific features..
   */



  private Paddle paddle;
  private Ball ball;
  private List<Brick> bricks;

  private int numBroken = 0;
  private int numStars = 0;



  private int numTaps = 0;
  private int backgroundColor;
  private boolean isRunning;
  private Bitmap brickBMP;
  private Bitmap starBMP;
  private Bitmap ballBMP;
  private Bitmap paddleBMP;
  private Bitmap paddleBlueBMP;
  private Bitmap paddleRedBMP;
  private Bitmap paddleYellowBMP;

  private double numOfSeconds;

  public static final int NUM_BRICK_LAYERS = 3;
  public static final int NUM_BRICKS_HORIZONTAL = 6;
  public static final int BRICK_HEIGHT = 80;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;
  public static final int PADDLE_WIDTH = 300;
  public static final int PADDLE_HEIGHT = 60;
  public static final int BALL_WIDTH = 80;
  public static final int BALL_HEIGHT = 80;


  /**
   * Constructs a BrickGameManager with the specified height, width, game, and activity.
   */
  public BrickGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    //this.game = new Game(Game.GameName.JUMPING);
  }




  /**
   * Returns the jumper in this Jumping game
   *
   * @return the jumper in this Jumping game
   */
  public Paddle getPaddle() {
    return paddle;
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
      this.paddleBMP = paddleBlueBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)){
      this.paddleBMP = paddleRedBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)){
      this.paddleBMP = paddleYellowBMP;
    } else {
      this.paddleBMP = paddleBlueBMP;
    }


    paddle = new Paddle(PADDLE_HEIGHT, PADDLE_WIDTH, paddleBMP);
    setPaddlePosition(paddle);
    place(paddle);

    bricks = new ArrayList<Brick>();
    int brickWidth = getScreenWidth() / NUM_BRICKS_HORIZONTAL;
    for (int i = 0; i < NUM_BRICK_LAYERS; i++){
      for (int j = 0; j < NUM_BRICKS_HORIZONTAL; j++){
        Brick newBrick = new Brick(BRICK_HEIGHT, brickWidth, brickBMP);
        newBrick.setPosition(j*brickWidth, i*BRICK_HEIGHT);
        place(newBrick);
        bricks.add(newBrick);
      }
    }


    ball = new Ball(BALL_WIDTH, BALL_HEIGHT, ballBMP);
    ball.setPosition(100, 100);
    place(ball);

    Star star = new Star(STAR_WIDTH, STAR_HEIGHT, starBMP);

    setStarPosition(star, getScreenWidth() * 3 / 5);
    place(star);



    setRunning(true);
  }



  /**
   * Set the theme of this game
   *
   * @param theme the theme to set
   */
  private void setTheme(Customization.ColourScheme theme) {
    if (theme.equals(Customization.ColourScheme.DARK)) {
      backgroundColor = Color.rgb(83, 92, 104);
    } else if (theme.equals((Customization.ColourScheme.LIGHT))) {
      backgroundColor = Color.rgb(223, 249, 251);
    } else {
      backgroundColor = Color.rgb(204, 255, 255);
    }
  }

  /**
   * Returns the colour of the sky
   *
   * @return the colour of the sky
   */
  public int getBackgroundColor() {
    return this.backgroundColor;
  }

  /**
   * Adds the specified star to this game at the given position
   *
   * @param xCoordinate the position at which to add the star
   */
  public void setStarPosition(Star star, int xCoordinate) {

  }



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
    BrickImportInfo brickImportInfo =
            new BrickImportInfo(getScreenHeight(), getScreenWidth(), this.ball, this.bricks, this.paddle, getNumOfSeconds());

    for (GameItem item : gameItems) {
      if (item instanceof AnimatedGameItem) {
        result = ((AnimatedGameItem) item).animate(brickImportInfo);
        // process result
        BrickResult brickResult = (BrickResult)result;
        if (brickResult.getInItems() != null) {
          for (GameItem inItem : brickResult.getInItems()) {
            newItems.add(inItem);
          }
        }
        if (brickResult.getOutItems() != null) {
          for (GameItem outItem : brickResult.getOutItems()) {
            outItems.add(outItem);
          }
        }
        /**if (brickResult.isObstacleJumped()) {
          numJumped += 1;
        }
        if (brickResult.isStarCollected()) {
          numStars += 1;
        }
        if (brickResult.isNeedNewStar()) {
          Star newStar = new Star(80, 80, starBMP);
          autoSetStarPosition(newStar);
          newItems.add(newStar);
        }*/
        if (brickResult.isGameOver()) {
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
    }
    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  /** Handles the jumper's jump when teh screen is tapped */
  public void onScreenTap() {

    //numTaps += 1;
  }


  /** Ends this minigame. */
  public void gameOver() {
    setRunning(false);
    // set points here
    game.setNumPoints(numBroken);
    game.setNumStars(numStars);
    game.setNumTaps(numTaps);

    super.gameOver();
  }

  public void setPaddlePosition(Paddle paddle) {
    paddle.setxCoordinate((getScreenWidth() - PADDLE_WIDTH)/2);
    paddle.setyCoordinate(getScreenHeight() - PADDLE_HEIGHT*6);
  }


  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }


  public void setBMPfiles(Bitmap ballBMP, Bitmap starBMP, Bitmap brickBMP,
                          Bitmap paddleBlueBMP, Bitmap paddleYellowBMP,
                          Bitmap paddleRedBMP){
    this.ballBMP = ballBMP;
    this.brickBMP = brickBMP;
    this.starBMP = starBMP;
    this.paddleBlueBMP = paddleBlueBMP;
    this.paddleYellowBMP = paddleYellowBMP;
    this.paddleRedBMP = paddleRedBMP;
  }

  public double getNumOfSeconds() {
    return numOfSeconds;
  }

  public void setNumOfSeconds(double numOfSeconds) {
    this.numOfSeconds = numOfSeconds;
  }

}
