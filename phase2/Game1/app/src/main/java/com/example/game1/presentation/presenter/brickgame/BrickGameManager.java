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
  private Bitmap brickBMP;
  private Bitmap brickDamagedBMP;
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
  public static final int PADDLE_WIDTH = 300; // 1550
  public static final int PADDLE_HEIGHT = 60;
  public static final int BALL_WIDTH = 80;
  public static final int BALL_HEIGHT = 80;
  public static final double BALL_VELOCITY_X = 900;
  public static final double BALL_VELOCITY_Y = 900;
  public static final double STAR_PROBABILITY = 0.8;
  private final int darkColor = Color.rgb(83, 92, 104);
  private final int lightColor = Color.rgb(223, 249, 251);
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
    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      this.paddleBMP = paddleBlueBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
      this.paddleBMP = paddleRedBMP;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      this.paddleBMP = paddleYellowBMP;
    } else {
      this.paddleBMP = paddleBlueBMP;
    }

    paddle = new Paddle(PADDLE_HEIGHT, PADDLE_WIDTH, paddleBMP);
    setPaddlePosition(paddle);
    place(paddle);

    bricks = new ArrayList<Brick>();
    int brickWidth = getScreenWidth() / NUM_BRICKS_HORIZONTAL;
    for (int i = 0; i < NUM_BRICK_LAYERS; i++) {
      for (int j = 0; j < NUM_BRICKS_HORIZONTAL; j++) {
        Brick newBrick = new Brick(BRICK_HEIGHT, brickWidth, brickBMP);
        newBrick.setPosition(j * brickWidth, i * BRICK_HEIGHT);
        place(newBrick);
        bricks.add(newBrick);
      }
    }

    ball = new Ball(BALL_WIDTH, BALL_HEIGHT, ballBMP);
    ball.setPosition(getScreenWidth() / 2, BRICK_HEIGHT * (NUM_BRICK_LAYERS + 1));
    ball.setXVelocity(BALL_VELOCITY_X);
    ball.setYVelocity(BALL_VELOCITY_Y);
    place(ball);

    stars = new ArrayList<>();

    setRunning(true);
  }

  /**
   * Set the theme of this game
   *
   * @param theme the theme to set
   */
  private void setTheme(Customization.ColourScheme theme) {
    if (theme.equals(Customization.ColourScheme.DARK)) {
      backgroundColor = darkColor;
    } else { // (theme.equals((Customization.ColourScheme.LIGHT)))
      backgroundColor = lightColor;
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
        new BrickImportInfo(
            getScreenHeight(),
            getScreenWidth(),
            this.ball,
            this.bricks,
            this.paddle,
            getNumOfSeconds());

    for (GameItem item : gameItems) {
      if (item instanceof AnimatedGameItem) {
        result = ((AnimatedGameItem) item).animate(brickImportInfo);
        // process result
        BrickResult brickResult = (BrickResult) result;

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
      }
    }
    // out of horizontal bounds
    if (ball.getXCoordinate() + ball.getWidth() >= getScreenWidth() || ball.getXCoordinate() < 0) {
      ball.setXVelocity(-ball.getXVelocity());
    }
    // out of top boundary
    if (ball.getyCoordinate() < 0) {
      ball.setYVelocity(Math.abs(ball.getYVelocity()));
    }

    // hits paddle
    // if (ball.getyCoordinate() + ball.getHeight() > paddle.getyCoordinate() &&
    // !(ball.getxCoordinate() + ball.getWidth() < paddle.getxCoordinate() ||
    // ball.getxCoordinate() > paddle.getxCoordinate() + paddle.getWidth() )){

    // overlapping and not more than halfway through
    if (ball.isOverlapping(paddle)
        && ball.getyCoordinate() + ball.getHeight()
            < paddle.getyCoordinate() + paddle.getHeight() / 2) {
      ball.setYVelocity((-Math.abs(ball.getYVelocity())));
    }

    if (ball.getyCoordinate() > getScreenHeight()) {
      gameOver();
      return false;
    }

    for (Star star : stars) {
      if (ball.isOverlapping(star)) {
        outItems.add(star);
        numStars += 1;
      }
    }

    for (Brick brick : bricks) {
      if (ball.isOverlapping(brick)) {
        boolean broken = brick.damageBrick();
        if (broken) {
          outItems.add(brick);
          numBroken++;
          if (Math.random() > STAR_PROBABILITY) {
            Star star = new Star(STAR_WIDTH, STAR_HEIGHT, starBMP);
            star.setPosition(
                brick.getXCoordinate() + brick.getWidth() / 2 - STAR_WIDTH / 2,
                brick.getyCoordinate());
            place(star);
            stars.add(star);
          }
        } else if (brick.needChangeAppearance()) {
          brick.setAppearance(brickDamagedBMP);
        }
        ball.setYVelocity(Math.abs(ball.getYVelocity()));
      }
    }

    //  if (brickResult.isGameOver()) {
    //  gameOver();
    // return false;
    // }

    for (GameItem newItem : newItems) {
      place(newItem);
    }

    for (GameItem outItem : outItems) {
      removeItem(outItem);
      if (outItem instanceof Brick) {
        bricks.remove(outItem);
      } else if (outItem instanceof Star) {
        stars.remove(outItem);
      }
    }

    // TODO: temporary return true; decide when you want to return true/false
    return true;
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
    game.setNumPoints(numBroken);
    game.setNumStars(numStars);
    game.setNumTaps(numTaps);

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

  public void setBMPfiles(
      Bitmap ballBMP,
      Bitmap starBMP,
      Bitmap brickBMP,
      Bitmap brickDamagedBMP,
      Bitmap paddleBlueBMP,
      Bitmap paddleRedBMP,
      Bitmap paddleYellowBMP) {
    this.ballBMP = ballBMP;
    this.brickBMP = brickBMP;
    this.brickDamagedBMP = brickDamagedBMP;
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

  public Object getSkyColor() {
    return 0;
    // TODO FOR ABDUL: decide what to do with this method (it overrides abstract method of parent)
  }
}
