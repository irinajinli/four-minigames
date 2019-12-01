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

import com.example.game1.AppManager;
import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.tappinggame.Runner;
import com.example.game1.presentation.model.tappinggame.TappingCircle;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

public class TappingGameView extends GameView implements View.OnClickListener {

  private final int BACKGROUND_COLOR_DARK = Color.rgb(83, 92, 104);
  private final int BACKGROUND_COLOR_LIGHT = Color.rgb(223, 249, 251);
  protected int numTaps;
  protected int numStars;
  protected boolean gameStarted;
  protected int bestResult;
  protected int secondLeft;
  protected int speed;
  private Bitmap tappingCircleBmp;
  private List<Bitmap> yellowPugs;
  private List<Bitmap> blueBirds;
  private List<Bitmap> redFishs;
  private OnClickListener listener;
  private CountDownTimer myTimer;
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
              ((TappingGameManager) gameManager).setNumTaps(numTaps);
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

    setBackgroundColorDark(BACKGROUND_COLOR_DARK);
    setBackgroundColorLight(BACKGROUND_COLOR_LIGHT);

    generateBackgroundColor();

    gameManager.createGameItems();
    gameManager.startMusic();

    thread.setRunning(true);
    thread.start();

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
            if (0 == secondsPassed) {
            } else {
              speed = (int) (numTaps / secondsPassed);
              if (gameManager instanceof TappingGameManager) {
                ((TappingGameManager) gameManager).setTappingSpeed(speed);
                ((TappingGameManager) gameManager).setSecondsLeft(secondLeft);
              }
            }
          }

          @Override
          public void onFinish() {
              (gameManager).gameOver();
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


  @Override
  public void onClick(View v) {
    if (gameStarted) {
      numTaps++;
      if (gameManager instanceof TappingGameManager){
        gameManager.setNumTaps(numTaps);
      }
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
          item.getDescription(),
          (float) xCoordinate * GameView.charWidth,
          (float) yCoordinate * GameView.charHeight,
          paintText);
    }
  }

  public void extractBmpFiles() {
    tappingCircleBmp = getNewBitmap(R.drawable.circle, getScreenWidth(), getScreenHeight() / 2);
    int runnerWidth = (int) (getScreenWidth() * TappingGameManager.RUNNER_WIDTH_MULTIPLIER);
    int runnerHeight = (int) (getScreenWidth() * TappingGameManager.RUNNER_HEIGHT_MULTIPLIER);
    if (gameManager instanceof TappingGameManager){
      ((TappingGameManager) gameManager).setRunnerWidthAndHeight(runnerWidth, runnerHeight);
    }
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
    };
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
