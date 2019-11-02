package com.example.game1.presentation.view.tappinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameView;

public class TappingGameView extends GameView {


  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The width of a character. */
  public static float charWidth;
  /** The height of a character. */
  public static float charHeight;

  /** The fish tank contents. */
  public TappingGameManager tappingGameManager;
  /** The part of the program that manages time. */
  private TappingMainThread tappingMainThread;

  private Bitmap tappingCircleBMP = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
  private Bitmap runnerBMP = BitmapFactory.decodeResource(getResources(), R.drawable.pug);

  protected int numTaps;
  protected int numStars;
  protected boolean gameStarted;
  protected int bestResult;
  protected int secondLeft;
  protected int speed;



  /**
   * Create a new fish tank in the context environment.
   *
   * @param context the environment.
   */
  public TappingGameView(Context context) {
    super(context);
    getHolder().addCallback(this);
    tappingMainThread = new TappingMainThread(getHolder(), this);
    setFocusable(true);
    numTaps = 0;
    numStars = 0;
    gameStarted = false;
    bestResult = 0;
    secondLeft = 10;
    speed = 0;
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    // Use the letter size and screen height to determine the size of the GameManager.
    tappingGameManager =
            new TappingGameManager(
                    (int) (screenHeight / charHeight),
                    (int) (screenWidth / charWidth),
                    tappingCircleBMP,
                    runnerBMP);
    //        tankManager.setTappingCircleImage(tappingCircleBMP);
    tappingGameManager.createTappingItems();

    tappingGameManager.setActivity(activity);
    tappingMainThread.setRunning(true);
    tappingMainThread.start();


    CountDownTimer timer =
            new CountDownTimer(10000, 1000) {


              @Override
              public void onTick(long millisUntilFinished) {
                secondLeft--;
                tappingGameManager.timerDisplayer.setSecondsLeft(secondLeft);
                // display the remaining time
                long timeTillEnd = (millisUntilFinished / 1000) + 1;
                long secondsPassed = 10 - timeTillEnd;

                // add logic to catch speed for the time passed.
                //double speed;
                //int star = 0;

                if (0 == secondsPassed) {
                  speed = 0;
                  tappingGameManager.speedDisplayer.setSpeed(speed);
                } else {
                  speed = (int)(numTaps / secondsPassed);
                  tappingGameManager.speedDisplayer.setSpeed(speed);
                  // set star to be the maximum speed reached for now
                  if (numStars < speed) {
                    numStars = (int) speed;
                    tappingGameManager.starDisplayer.setNumStar(numStars);
                  }
                }
              }

              @Override
              public void onFinish() {
                // the game is over
                //iv_tap.setEnabled(false);
                gameStarted = false;
                //tv_info.setText("Game Over!");

                // check the high score and save the new result if better
                if (numTaps > bestResult) {
                  bestResult = numTaps;
                }

                tappingGameManager.gameOver(numTaps, numStars);

              }
            };
    timer.start();

    gameStarted = true;
  }


  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        tappingMainThread.setRunning(false);
        tappingMainThread.join();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update the fish tank. */
  public void update() {
    tappingGameManager.update();
  }

  @Override
  public void draw(Canvas canvas) {
    //super.draw(canvas);

    if (canvas != null) {
      tappingGameManager.draw(canvas);
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // count tap in game's numTaps
    //        Game game = gameManager.getGame();
    //        game.setNumTaps(game.getNumTaps() + 1);
    //
    //        double touchX = event.getX() / charWidth;
    //        ((AppleGameManager) gameManager).moveBasket((int) touchX);
    //
    //        return true;
    if (gameStarted){
      numTaps++;
      tappingGameManager.tapCounter.setNumTaps(numTaps);
    }

    return true;
  }



}
