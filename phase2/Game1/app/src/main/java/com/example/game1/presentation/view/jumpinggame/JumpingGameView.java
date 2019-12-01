package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.AppManager;
import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** The view of the jumping game presented to the user. */
public class JumpingGameView extends GameView implements View.OnClickListener {

  private final int BACKGROUND_COLOR_DARK = Color.rgb(83, 92, 104);
  private final int BACKGROUND_COLOR_LIGHT = Color.rgb(223, 249, 251);
  private final int JUMPER_WIDTH = 100;
  private final int JUMPER_HEIGHT = 200;
  private final int OBSTACLE_WIDTH = 100;
  private final int OBSTACLE_HEIGHT = 100;
  private final int STAR_WIDTH = 80;
  private final int STAR_HEIGHT = 80;
  private OnClickListener listener;
  private List<Bitmap> obstacleBmps;
  private List<Bitmap> starBmps;
  private List<Bitmap> terrainBmps;
  private List<Bitmap> jumperBlueBmps;
  private List<Bitmap> jumperRedBmps;
  private List<Bitmap> jumperYellowBmps;
  private int currentFrameJumper = 0;
  
  // image files for game objects
  private final int[] TERRAIN_FILES = {R.drawable.grass};
  private final int[] OBSTACLE_FILES = {R.drawable.wooden_blocks_1};
  private final int[] STAR_FILES = {R.drawable.star_6};
  private final int[] JUMPER_BLUE_FILES = {
          R.drawable.jumper_blue_1,
          R.drawable.jumper_blue_2,
          R.drawable.jumper_blue_3,
          R.drawable.jumper_blue_4,
          R.drawable.jumper_blue_5,
          R.drawable.jumper_blue_6,
          R.drawable.jumper_blue_7,
          R.drawable.jumper_blue_8,
          R.drawable.jumper_blue_9,
          R.drawable.jumper_blue_10
  };
  private final int[] JUMPER_RED_FILES = {
          R.drawable.jumper_red_1,
          R.drawable.jumper_red_2,
          R.drawable.jumper_red_3,
          R.drawable.jumper_red_4,
          R.drawable.jumper_red_5,
          R.drawable.jumper_red_6,
          R.drawable.jumper_red_7,
          R.drawable.jumper_red_8,
          R.drawable.jumper_red_9,
          R.drawable.jumper_red_10
  };
  private final int[] JUMPER_YELLOW_FILES = {
          R.drawable.jumper_yellow_1,
          R.drawable.jumper_yellow_2,
          R.drawable.jumper_yellow_3,
          R.drawable.jumper_yellow_4,
          R.drawable.jumper_yellow_5,
          R.drawable.jumper_yellow_6,
          R.drawable.jumper_yellow_7,
          R.drawable.jumper_yellow_8
  };
  
  
  /**
   * creates a new JumpingView *
   *
   * @param context the context in which to create the view
   */
  public JumpingGameView(Context context) {
    super(context);

    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
    this.listener =
        new OnClickListener() {
          @Override
          public void onClick(View v) {

              gameManager.setNumTaps(gameManager.getNumTaps() + 1);

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
                Game.GameName.JUMPING,
                (int) (getScreenHeight() / charHeight),
                (int) (getScreenWidth() / charWidth),
                activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());
    gameManager.setNumSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    if (gameManager instanceof JumpingGameManager){
      ((JumpingGameManager) gameManager).setItemSize(JUMPER_WIDTH, JUMPER_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, STAR_WIDTH, STAR_HEIGHT);
    }
    extractBmpFiles();
    generateCharacterColor();

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

  /**
   * Handles the jumper's jump when the user taps the screen
   *
   * @param event the event for the screen tap
   * @return true or false depending on the implementation of the superclass
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    ((JumpingGameManager) gameManager).onTouchEvent();
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
      gameManager.setNumTaps(gameManager.getNumTaps() + 1);
  }

  @Override
  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item) {
    setupPaintText();
    Bitmap appearance;
    String key;
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
    if (item instanceof Jumper) {
      key = "Jumper" + getCharacterColorScheme();
      appearance = getCurrentAppearance(key);
    } else {
      key = item.getClass().getSimpleName();
      appearance = getAppearance(key);
    }
    canvas.drawBitmap(
        appearance, (int) Math.round(xCoordinate), (int) Math.round(yCoordinate), paintText);
  }

  public void extractBmpFiles() {
    // terrainBmp = getNewBitmap(R.drawable.grass, getScreenWidth(), getScreenHeight() / 2);
    terrainBmps = new ArrayList<>();
    obstacleBmps = new ArrayList<>();
    starBmps = new ArrayList<>();
    // @TODO don't need to load everything - only the customization that was chosen
    jumperBlueBmps = new ArrayList<>();
    jumperRedBmps = new ArrayList<>();
    jumperYellowBmps = new ArrayList<>();
    JumpingGameManager jumpingGameManager = (JumpingGameManager) gameManager;
    generateAnimatedBmps(terrainBmps, TERRAIN_FILES, getScreenWidth(), getScreenHeight() / 2);
    generateAnimatedBmps(
        obstacleBmps,
        OBSTACLE_FILES,
        jumpingGameManager.getObstacleWidth(),
        jumpingGameManager.getObstacleHeight());
    generateAnimatedBmps(
        starBmps, STAR_FILES, jumpingGameManager.getStarWidth(), jumpingGameManager.getStarHeight());
    generateAnimatedBmps(
        jumperBlueBmps,
        JUMPER_BLUE_FILES,
        jumpingGameManager.getJumperWidth(),
        jumpingGameManager.getJumperHeight());
    generateAnimatedBmps(
        jumperRedBmps,
        JUMPER_RED_FILES,
        jumpingGameManager.getJumperWidth(),
        jumpingGameManager.getJumperHeight());
    generateAnimatedBmps(
        jumperYellowBmps,
        JUMPER_YELLOW_FILES,
        jumpingGameManager.getJumperWidth(),
        jumpingGameManager.getJumperHeight());

    addGameItemAppearances("JumperYellow", jumperYellowBmps);
    addGameItemAppearances("JumperBlue", jumperBlueBmps);
    addGameItemAppearances("JumperRed", jumperRedBmps);
    addGameItemAppearances("Obstacle", obstacleBmps);
    addGameItemAppearances("JumpingStar", starBmps);
    addGameItemAppearances("Terrain", terrainBmps);
  }

  public void setupPaintText() {
    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
  }

  public Bitmap getCurrentAppearance(String key) {
    List<Bitmap> appearances = getAppearances(key);

    Bitmap appearance = appearances.get(currentFrameJumper);
    currentFrameJumper = updateIndex(currentFrameJumper, appearances.size());
    return appearance;
  }
}
