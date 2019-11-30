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
  public static final int NUM_BRICK_LAYERS = 3;
  public static final int NUM_BRICKS_HORIZONTAL = 6;
  public static final int BRICK_HEIGHT = 80;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;
  public static final int PADDLE_WIDTH = 300; // 1550
  public static final int PADDLE_HEIGHT = 60;
  public static final int BALL_WIDTH = 80;
  public static final int BALL_HEIGHT = 80;
  public static final double BALL_VELOCITY_X = 900;
  public static final double BALL_VELOCITY_Y = 900;
  private final int DARK_COLOR = Color.rgb(83, 92, 104);
  private final int LIGHT_COLOR = Color.rgb(223, 249, 251);
  /**
   * A GameManager for a Brick Breaking minigame. Includes extra variables and methods for handling
   * game-specific features..
   */
  private Paddle paddle;

  private Ball ball;
  private List<Brick> bricks;
  private List<Star> stars;
  private int numBroken = 0;
  private int numStars = 0;
  private int numTaps = 0;
  private int backgroundColor;
  private boolean isRunning;
  private Bitmap brickBmp;
  private Bitmap brickDamagedBmp;
  private List<Bitmap> starBmps;
  private List<Bitmap> ballBmps;
  private List<Bitmap> paddleBmps;
  private List<Bitmap> paddleBlueBmps;
  private List<Bitmap> paddleRedBmps;
  private List<Bitmap> paddleYellowBmps;
  private double numSeconds;

  /**
   * Constructs a BrickGameManager with the specified height and width.
   *
   * @param height the height of the JumpingGameManager
   * @param width the width of the JumpingGameManager
   */
  public BrickGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    // this.game = new Game(Game.GameName.JUMPING);
  }

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

  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    BrickItemsBuilder builder = new BrickItemsBuilder(game.getCustomization());
    builder.setTheme(this);
    builder.createPaddle(
        PADDLE_HEIGHT, PADDLE_WIDTH, paddleBlueBmps, paddleRedBmps, paddleYellowBmps);
    builder.createBricks(
        getScreenWidth(), NUM_BRICKS_HORIZONTAL, NUM_BRICK_LAYERS, BRICK_HEIGHT, brickBmp);
    builder.createBall(
        BALL_WIDTH,
        BALL_HEIGHT,
        ballBmps,
        getScreenWidth(),
        BRICK_HEIGHT,
        NUM_BRICK_LAYERS,
        BALL_VELOCITY_X,
        BALL_VELOCITY_Y);
    builder.placeItems(this);

    setRunning(true);

    // create background according to Customization
    //    Customization cust = game.getCustomization();
    //    setTheme(cust.getColourScheme());

    //    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
    //      this.paddleBmps = paddleBlueBmps;
    //    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
    //      this.paddleBmps = paddleRedBmps;
    //    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
    //      this.paddleBmps = paddleYellowBmps;
    //    } else {
    //      this.paddleBmps = paddleBlueBmps;
    //    }
    //
    //    paddle = new Paddle(PADDLE_HEIGHT, PADDLE_WIDTH, paddleBmps);
    //    setPaddlePosition(paddle);
    //    place(paddle);

    //    bricks = new ArrayList<Brick>();
    //    int brickWidth = getScreenWidth() / NUM_BRICKS_HORIZONTAL;
    //    for (int i = 0; i < NUM_BRICK_LAYERS; i++) {
    //      for (int j = 0; j < NUM_BRICKS_HORIZONTAL; j++) {
    //        Brick newBrick = new Brick(BRICK_HEIGHT, brickWidth, brickBmp);
    //        newBrick.setPosition(j * brickWidth, i * BRICK_HEIGHT);
    //        place(newBrick);
    //        bricks.add(newBrick);
    //      }
    //    }

    //    ball = new Ball(BALL_WIDTH, BALL_HEIGHT, ballBmps);
    //    ball.setPosition(getScreenWidth() / 2, BRICK_HEIGHT * (NUM_BRICK_LAYERS + 1));
    //    ball.setXVelocity(BALL_VELOCITY_X);
    //    ball.setYVelocity(BALL_VELOCITY_Y);
    //    place(ball);

    //    stars = new ArrayList<>();
  }

  /**
   * Set the theme of this game
   *
   * @param theme the theme to set
   */
  void setTheme(Customization.ColourScheme theme) {
    if (theme.equals(Customization.ColourScheme.DARK)) {
      backgroundColor = DARK_COLOR;
    } else { // (theme.equals((Customization.ColourScheme.LIGHT)))
      backgroundColor = LIGHT_COLOR;
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
  public void setStarPosition(Star star, int xCoordinate) {}

  /** updates all items in this game */
  @Override
  public boolean update() {

    BrickMovementInfo result = new BrickMovementInfo(ball, bricks, stars, paddle,  gameItems, getScreenHeight(), getScreenWidth(),  starBmps, getNumSeconds());
    result.animate();

    numBroken = result.getNumBroken();
    numTaps = result.getNumTaps();
    numStars = result.getNumStars();
    for (GameItem item: gameItems){
      if (item instanceof AnimatedGameItem)
        ((AnimatedGameItem)item).updatePositionAndVelocity(numSeconds);
    }


    if (!result.continueGame()){
      gameOver();
    }
    return result.continueGame();
  }



  /** Handles the jumper's jump when the screen is tapped */
  public void onTouchEvent(double xCoordinate) {
    paddle.setXCoordinate(xCoordinate - paddle.getWidth() / 2);

    // numTaps += 1;
  }

  /** Ends this minigame. */
  public void gameOver() {
    setRunning(false);
    // set points here
    game.getStatistics().setPoints(numBroken);
    game.getStatistics().setStars(numStars);
    game.getStatistics().setTaps(numTaps);

    super.gameOver();
  }

  public void setPaddlePosition(Paddle paddle) {
    paddle.setXCoordinate((getScreenWidth() - PADDLE_WIDTH) / 2);
    paddle.setYCoordinate(getScreenHeight() - PADDLE_HEIGHT * 6);
  }

  public int getNumTaps() {
    return numTaps;
  }

  public void setNumTaps(int numTaps) {
    this.numTaps = numTaps;
  }

  public void setBmpfiles(
      List<Bitmap> ballBmps,
      List<Bitmap> starBmps,
      Bitmap brickBmp,
      Bitmap brickDamagedBmp,
      List<Bitmap> paddleBlueBmps,
      List<Bitmap> paddleRedBmps,
      List<Bitmap> paddleYellowBmps) {
    this.ballBmps = ballBmps;
    this.brickBmp = brickBmp;
    this.brickDamagedBmp = brickDamagedBmp;
    this.starBmps = starBmps;
    this.paddleBlueBmps = paddleBlueBmps;
    this.paddleYellowBmps = paddleYellowBmps;
    this.paddleRedBmps = paddleRedBmps;
  }

  public double getNumSeconds() {
    return numSeconds;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }

  public Object getSkyColor() {
    return 0;
    // TODO FOR ABDUL: decide what to do with this method (it overrides abstract method of parent)
  }
}
