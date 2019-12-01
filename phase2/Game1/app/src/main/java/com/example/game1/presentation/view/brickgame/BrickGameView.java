package com.example.game1.presentation.view.brickgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.AppManager;
import com.example.game1.R;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** The view of the jumping game presented to the user. */
public class BrickGameView extends GameView implements View.OnClickListener {

  private final int BACKGROUND_COLOR_DARK = Color.rgb(83, 92, 104);
  private final int BACKGROUND_COLOR_LIGHT = Color.rgb(223, 249, 251);
  private GameThread thread;
  private OnClickListener listener;
  private int numTaps = 0;
  private List<Bitmap> ballBmps;
  private List<Bitmap> starBmps;
  private Bitmap brickBmp;
  private Bitmap brickDamagedBmp;
  private List<Bitmap> paddleBlueBmps;
  private List<Bitmap> paddleRedBmps;
  private List<Bitmap> paddleYellowBmps;

  // keys for Map from item string to it's bitmap image
  private final String BALL_KEY = "ball";
  private final String STAR_KEY = "star";
  private final String BRICK_KEY = "brick";
  private final String BRICK_DAMAGED_KEY = "brick_damaged";
  private final String PADDLE_RED_KEY = "paddle_red";
  private final String PADDLE_BLUE_KEY = "paddle_red";
  private final String PADDLE_YELLOW_KEY = "paddle_red";
  /**
   * creates a new BrickView *
   *
   * @param context the context in which to create the view
   */
  public BrickGameView(Context context) {
    super(context);

    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
    this.listener =
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            if (true) {
              numTaps++;
              ((BrickGameManager) gameManager).setNumTaps(numTaps);
            }
          }
        };
  }

  /**
   * initialiezes the game view when a surface is created
   *
   * @param holder the surface holder holding the view
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    gameManager =
        AppManager.getInstance()
            .buildGameManager(
                Game.GameName.BRICK,
                (int) (getScreenHeight() / charHeight),
                (int) (getScreenWidth() / charWidth),
                activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());
    ((BrickGameManager) gameManager).setNumSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    extractBmpFiles();

    ((BrickGameManager) gameManager)
            .setBmpfiles(
                    ballBmps,
                    starBmps,
                    brickBmp,
                    brickDamagedBmp,
                    paddleBlueBmps,
                    paddleRedBmps,
                    paddleYellowBmps);
    setBackgroundColorDark(BACKGROUND_COLOR_DARK);
    setBackgroundColorLight(BACKGROUND_COLOR_LIGHT);

    generateBackgroundColor();

    gameManager.createGameItems();
    gameManager.startMusic();

    thread.setRunning(true);
    thread.start();

    this.setOnClickListener(this.listener);
  }

  /** Updates this game view */
  @Override
  public void update() {
    // get amount of time in seconds);

    boolean updated = gameManager.update();
    // stop thread if update fails
    if (!updated) {
      thread.setRunning(false);
    }
  }

  @Override
  public void drawItems(Canvas canvas) {
    List<GameItem> items = gameManager.getGameItems();
    for (GameItem item : items) {
      drawItem(canvas, item, getAppearance(item));
    }
  }
  /**
   * draws this game view on the canvas
   *
   * @param canvas the canvas on which to draw
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);

  }

  private Bitmap getAppearance(GameItem item){
    String key = null;
    if (item instanceof Paddle){
      Customization cust = gameManager.getGame().getCustomization();
      if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)){
        key = PADDLE_BLUE_KEY;
      }
      else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)){
        key = PADDLE_RED_KEY;
      }
      else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)){
        key = PADDLE_YELLOW_KEY;
      }
    }
    else if (item instanceof Ball){
      key = BALL_KEY;
    }
    else if (item instanceof Brick){
      if (((Brick)item).isDamaged()){
        key = BRICK_DAMAGED_KEY;
      }
      else{
        key = BRICK_KEY;
      }
    }
    else if (item instanceof Star){
      key = STAR_KEY;
    }
    if (key == null){
      return null;
    }
    return getAppearance(key);
  }
  /**
   * Handles the jumper's jump when the user taps the screen
   *
   * @param event the event for the screen tap
   * @return true or false depending on the implementation of the superclass
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    ((BrickGameManager) gameManager).onTouchEvent(event.getX());
    return super.onTouchEvent(event);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();
        gameManager.gameOver();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  @Override
  public void onClick(View v) {

    if (true) {
      numTaps++;
      System.out.println(numTaps);
    }
  }

  public void extractBmpFiles() {
    BrickGameManager brickGameManager = (BrickGameManager) gameManager;
    brickBmp =
        getNewBitmap(
            R.drawable.brick_blue,
            getScreenWidth() / brickGameManager.getNumBricksHorizontal(),
            brickGameManager.getBrickHeight());
    brickDamagedBmp =
        getNewBitmap(
            R.drawable.brick_blue_damaged,
            getScreenWidth() / brickGameManager.getNumBricksHorizontal(),
            brickGameManager.getBrickHeight());

    int[] ballFiles = {R.drawable.ball_blue};
    int[] starFiles = {R.drawable.star_6};
    int[] paddleBlueFiles = {R.drawable.paddle_blue};
    int[] paddleRedFiles = {R.drawable.paddle_red};
    int[] paddleYellowFiles = {R.drawable.paddle_yellow};

    ballBmps = new ArrayList<Bitmap>();
    starBmps = new ArrayList<Bitmap>();
    paddleBlueBmps = new ArrayList<Bitmap>();
    paddleRedBmps = new ArrayList<Bitmap>();
    paddleYellowBmps = new ArrayList<Bitmap>();

    generateAnimatedBmps(
        ballBmps, ballFiles, brickGameManager.getBallWidth(), brickGameManager.getBallHeight());
    generateAnimatedBmps(
        starBmps, starFiles, brickGameManager.getStarWidth(), brickGameManager.getStarHeight());
    generateAnimatedBmps(
        paddleBlueBmps,
        paddleBlueFiles,
        brickGameManager.getPaddleWidth(),
        brickGameManager.getPaddleHeight());
    generateAnimatedBmps(
        paddleRedBmps,
        paddleRedFiles,
        brickGameManager.getPaddleWidth(),
        brickGameManager.getPaddleHeight());
    generateAnimatedBmps(
        paddleYellowBmps,
        paddleYellowFiles,
        brickGameManager.getPaddleWidth(),
        brickGameManager.getPaddleHeight());

    addGameItemAppearance(BRICK_KEY, brickBmp);
    addGameItemAppearance(BRICK_DAMAGED_KEY, brickDamagedBmp);
    addGameItemAppearances(BALL_KEY, ballBmps);
    addGameItemAppearances(PADDLE_BLUE_KEY, paddleBlueBmps);
    addGameItemAppearances(PADDLE_RED_KEY, paddleRedBmps);
    addGameItemAppearances(PADDLE_YELLOW_KEY, paddleYellowBmps);
    addGameItemAppearances(STAR_KEY, starBmps);
  }
}
