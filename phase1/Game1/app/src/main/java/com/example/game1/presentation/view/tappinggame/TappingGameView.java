package com.example.game1.presentation.view.tappinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.view.common.MainThread;
import com.example.game1.R;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameView;

public class TappingGameView extends GameView implements View.OnClickListener{


//  /** Screen width. */
//  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//  /** Screen height. */
//  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

//  /** The width of a character. */
//  public static float charWidth;
//  /** The height of a character. */
//  public static float charHeight;

//  /** The fish tank contents. */
//  public TappingGameManager tappingGameManager;
  /** The part of the program that manages time. */
  private MainThread tappingMainThread;

  private Bitmap tappingCircleBMP = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
  private Bitmap runnerBMP;
  private Bitmap yellowPug = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_pug);
  private Bitmap blueBird = BitmapFactory.decodeResource(getResources(), R.drawable.blue_bird);
  private Bitmap redFish = BitmapFactory.decodeResource(getResources(), R.drawable.red_fish);


  protected int numTaps;
  protected int numStars;
  protected boolean gameStarted;
  protected int bestResult;
  protected int secondLeft;
  protected int speed;
  private boolean reachDestination;
  private OnClickListener listener;
  private CountDownTimer myTimer;

  /**
   * Create a new fish tank in the context environment.
   *
   * @param context the environment.
   */
  public TappingGameView(Context context) {
    super(context);
    getHolder().addCallback(this);
    tappingMainThread = new MainThread(getHolder(), this);
    setFocusable(true);
    numTaps = 0;
    numStars = 0;
    gameStarted = false;
    bestResult = 0;
    secondLeft = 10;
    speed = 0;
    reachDestination = false;
    this.listener = new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (gameStarted){
          numTaps++;
          ((TappingGameManager)gameManager).tapCounter.setNumTaps(numTaps);
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

//    // Use the letter size and screen height to determine the size of the GameManager.
//    gameManager =
//            new TappingGameManager(
//                    (int) (getScreenHeight() / charHeight),
//                    (int) (getScreenWidth() / charWidth),
//                    tappingCircleBMP,
//                    runnerBMP);

    gameManager =
            AppManager.getInstance()
                    .getTappingGameManager(
                            (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    //        tankManager.setTappingCircleImage(tappingCircleBMP);


    ((TappingGameManager)gameManager).setPictures(tappingCircleBMP, yellowPug, blueBird, redFish);


    gameManager.createGameItems();

    gameManager.setActivity(activity);
    tappingMainThread.setRunning(true);
    tappingMainThread.start();
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

              reachDestination = !((TappingGameManager) gameManager).isCanRun();


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
            if (!gameStarted){
              ((TappingGameManager) gameManager).gameOver(numTaps, numStars);
            }

          }
        };
    myTimer = timer;
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
        myTimer.cancel();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update the fish tank. */
  public void update() {
    gameManager.update();
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);

    if (canvas != null) {
      gameManager.draw(canvas);
    }
  }


  @Override
  public void onClick(View v){

    if (gameStarted){
      numTaps++;
      ((TappingGameManager)gameManager).tapCounter.setNumTaps(numTaps);
    }
  }

}
