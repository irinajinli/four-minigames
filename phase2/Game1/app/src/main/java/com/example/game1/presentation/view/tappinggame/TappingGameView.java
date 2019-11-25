package com.example.game1.presentation.view.tappinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.AppManager;
import com.example.game1.presentation.model.tappinggame.Runner;
import com.example.game1.presentation.model.tappinggame.TappingCircle;
import com.example.game1.presentation.view.common.GameThread;

import com.example.game1.R;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

public class TappingGameView extends GameView implements View.OnClickListener {

  private Bitmap tappingCircleBmp;
  private List<Bitmap> yellowPugs;
  private List<Bitmap> blueBirds;
  private List<Bitmap> redFishs;

  protected int numTaps;
  protected int numStars;
  protected boolean gameStarted;
  protected int bestResult;
  protected int secondLeft;
  protected int speed;

  private OnClickListener listener;
  private CountDownTimer myTimer;
  private final int SKY_COLOR_DARK = Color.rgb(83, 92, 104);
  private final int SKY_COLOR_LIGHT = Color.rgb(223, 249, 251);
  private int currentFrameRunner = 0;
  /**
   * Create a new fish tank in the context environment.
   *
   * @param context the environment.
   */
  public TappingGameView(Context context) {
    super(context);
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
    //      itemAppearances = new HashMap<>();
    numTaps = 0;
    numStars = 0;
    gameStarted = false;
    bestResult = 0;
    secondLeft = 10;
    speed = 0;

    this.listener =
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            if (gameStarted) {
              numTaps++;
              ((TappingGameManager) gameManager).tapCounter.setNumTaps(numTaps);
            }
          }
        };
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    gameManager =
        AppManager.getInstance()
            .buildGameManager(
                Game.GameName.TAPPING,
                (int) (getScreenHeight() / charHeight),
                (int) (getScreenWidth() / charWidth),
                activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());

    extractBmpFiles();
    generateCharacterColor();

    setSkyColorDark(SKY_COLOR_DARK);
    setSkyColorLight(SKY_COLOR_LIGHT);

    generateSkyColor();



    gameManager.createGameItems();
    gameManager.startMusic();

    thread.setRunning(true);
    thread.start();

    ((TappingGameManager) gameManager).setCanRun(false);
    this.setOnClickListener(this.listener);
    CountDownTimer timer =
        new CountDownTimer(10000, 1000) {

          @Override
          public void onTick(long millisUntilFinished) {
            secondLeft--;
            ((TappingGameManager) gameManager).timerDisplayer.setSecondsLeft(secondLeft);
            // display the remaining time
            long timeTillEnd = (millisUntilFinished / 1000) + 1;
            long secondsPassed = 10 - timeTillEnd;

            // add logic to catch speed for the time passed.
            // double speed;
            // int star = 0;

            if (0 == secondsPassed) {
              speed = 0;
              ((TappingGameManager) gameManager).speedDisplayer.setSpeed(speed);
            } else {
              speed = (int) (numTaps / secondsPassed);
              ((TappingGameManager) gameManager).speedDisplayer.setSpeed(speed);
              ((TappingGameManager) gameManager).runner.setSpeed(speed);

              // set star to be the maximum speed reached for now
              if (numStars < speed) {
                numStars = (int) speed;
                ((TappingGameManager) gameManager).starDisplayer.setNumStar(numStars);
              }
            }
          }

          @Override
          public void onFinish() {
            // the game is over
            // iv_tap.setEnabled(false);
            gameStarted = false;
            // tv_info.setText("Game Over!");

            // check the high score and save the new result if better
            if (numTaps > bestResult) {
              bestResult = numTaps;
            }
            if (!gameStarted) {
              ((TappingGameManager) gameManager).gameOver(numTaps, numStars);
            }
          }
        };
    myTimer = timer;
    timer.start();
    gameStarted = true;
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
    myTimer.cancel();
  }

  /** Update the fish tank. */
  public void update() {
    gameManager.update();
  }


  @Override
  public void onClick(View v) {
    if (gameStarted) {
      numTaps++;
      ((TappingGameManager) gameManager).tapCounter.setNumTaps(numTaps);
    }
  }

  @Override
  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item) {
    setupPaintText();
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
    String key = "";
    if (item instanceof Runner || item instanceof TappingCircle) {
      try {
        key = generateKey(item);
      } catch (Exception e) {
        System.out.println("No Key");
      }
      ;
      Bitmap appearance;
      if (item instanceof Runner) {
        appearance = getCurrentAppearance(key);
      } else {
        appearance = tappingCircleBmp;
      }
      canvas.drawBitmap(
          appearance, (int) Math.round(xCoordinate), (int) Math.round(yCoordinate), paintText);
    } else {
      canvas.drawText(
          (String) item.getAppearance(),
          (float) xCoordinate * GameView.charWidth,
          (float) yCoordinate * GameView.charHeight,
          paintText);
    }
  }

  public void extractBmpFiles() {
    tappingCircleBmp = getNewBitmap(R.drawable.circle, getScreenWidth(), getScreenHeight() / 2);
    int runnerWidth = (int) (getScreenWidth() * TappingGameManager.RUNNER_WIDTH_MULTIPLIER);
    int runnerHeight = (int) (getScreenWidth() * TappingGameManager.RUNNER_HEIGHT_MULTIPLIER);

    int[] yellowPugFiles = {
      R.drawable.dog_yellow_1,
      R.drawable.dog_yellow_2,
      R.drawable.dog_yellow_3,
      R.drawable.dog_yellow_4,
      R.drawable.dog_yellow_5,
      R.drawable.dog_yellow_6,
      R.drawable.dog_yellow_7,
      R.drawable.dog_yellow_8
    };
    int[] blueBirdFiles = {
      R.drawable.bird_blue_1, R.drawable.bird_blue_2, R.drawable.bird_blue_3, R.drawable.bird_blue_4
    }; // R.drawable.blue_bird};
    int[] redFishsFiles = {
      R.drawable.fish_red_1,
      R.drawable.fish_red_2,
      R.drawable.fish_red_3,
      R.drawable.fish_red_4,
      R.drawable.fish_red_5,
      R.drawable.fish_red_6
    };

    yellowPugs = new ArrayList<>();
    blueBirds = new ArrayList<>();
    redFishs = new ArrayList<>();

    generateAnimatedBmps(yellowPugs, yellowPugFiles, runnerWidth, runnerHeight);
    generateAnimatedBmps(blueBirds, blueBirdFiles, runnerWidth, runnerHeight);
    generateAnimatedBmps(redFishs, redFishsFiles, runnerWidth, runnerHeight);

    //      yellowPugs.add(yellowPug);
    //      blueBirds.add(blueBird);
    //      redFishs.add(redFish);
    addGameItemAppearance("TappingCircle", tappingCircleBmp);

    addGameItemAppearances("RunnerYellow", yellowPugs);
    addGameItemAppearances("RunnerBlue", blueBirds);
    addGameItemAppearances("RunnerRed", redFishs);
  }

  public void setupPaintText() {
    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    paintText.setColor(Color.CYAN);
  }

  public String generateKey(GameItem item) throws Exception {
    if (item instanceof TappingCircle) {

      return "TappingCircle";

    } else if (item instanceof Runner) {
      return "Runner" + getCharacterColorScheme();

    } else {
      throw new Exception("No key generated");
    }
  }

  public Bitmap getCurrentAppearance(String key) {
    List<Bitmap> appearances = getAppearances(key);

    Bitmap appearance = appearances.get(currentFrameRunner);
    currentFrameRunner = updateIndex(currentFrameRunner, appearances.size());
    return appearance;
  }
}
