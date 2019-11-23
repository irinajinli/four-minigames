package com.example.game1.presentation.view.tappinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.view.common.GameThread;

import com.example.game1.R;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameView;

public class TappingGameView extends GameView implements View.OnClickListener{

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
    thread = new GameThread(getHolder(), this);
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

    gameManager =
            AppManager.getInstance()
                    .getTappingGameManager(
                            (int) (getScreenHeight() / charHeight),
                            (int) (getScreenWidth() / charWidth),
                            activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());
    ((TappingGameManager)gameManager).setPictures(tappingCircleBMP, yellowPug, blueBird, redFish);
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
  public void surfaceDestroyed(SurfaceHolder holder) {
      super.surfaceDestroyed(holder);
      myTimer.cancel();
  }

  /** Update the fish tank. */
  public void update() {
    gameManager.update();
  }

//  @Override
//  public void draw(Canvas canvas) {
//    super.draw(canvas);
//
//    if (canvas != null) {
//      gameManager.draw(canvas);
//    }
//  }

  @Override
  public void onClick(View v){
    if (gameStarted){
      numTaps++;
      ((TappingGameManager)gameManager).tapCounter.setNumTaps(numTaps);
    }
  }

  @Override
  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item) {
    if (item instanceof Background) {
      Rect backgroundRect = new Rect(0, 0, 999999, 999999);
      Paint backgroundPaint = new Paint();
      backgroundPaint.setStyle(Paint.Style.STROKE);
      backgroundPaint.setStrokeWidth(5);
      backgroundPaint.setAntiAlias(true);
      backgroundPaint.setColor(Color.DKGRAY);
      backgroundPaint.setStyle(Paint.Style.FILL);
      canvas.drawRect(backgroundRect, backgroundPaint);
    } else {
      paintText = new Paint();
      paintText.setTypeface(Typeface.DEFAULT_BOLD);
      paintText.setTextSize(36);
      Object appearance = item.getAppearance();
      double xCoordinate = item.getxCoordinate();
      double yCoordinate = item.getyCoordinate();
      if (appearance.getClass() == String.class) {
        paintText.setColor(Color.WHITE);
        canvas.drawText(
                (String) appearance,
                (float) xCoordinate * GameView.charWidth,
                (float) yCoordinate * GameView.charHeight,
                paintText);

        // canvas.drawText((String) appearance, x * TappingGameView.charWidth, y *
        // TappingGameView.charHeight, paintText);
      } else if (appearance.getClass() == Bitmap.class) {
        canvas.drawBitmap(
                (Bitmap) appearance,
                (int) Math.round(xCoordinate),
                (int) Math.round(yCoordinate),
                paintText);
      }
    }
  }
}
