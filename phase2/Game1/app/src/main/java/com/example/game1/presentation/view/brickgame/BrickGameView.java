package com.example.game1.presentation.view.brickgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;

import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;


/** The view of the jumping game presented to the user. */
public class BrickGameView extends GameView implements View.OnClickListener {


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
                    .getBrickGameManager(
                            (int) (getScreenHeight() / charHeight),
                            (int) (getScreenWidth() / charWidth),
                            activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());
    ((BrickGameManager)gameManager).setNumOfSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    Bitmap ballBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball_blue);
    Bitmap starBmp = BitmapFactory.decodeResource(getResources(), R.drawable.star_6);
    brickBmp = BitmapFactory.decodeResource(getResources(), R.drawable.brick_blue);
    brickDamagedBmp = BitmapFactory.decodeResource(getResources(), R.drawable.brick_blue_damaged);
    Bitmap paddleBlueBmp = BitmapFactory.decodeResource(getResources(), R.drawable.paddle_blue);
    Bitmap paddleRedBmp = BitmapFactory.decodeResource(getResources(), R.drawable.paddle_red);
    Bitmap paddleYellowBmp = BitmapFactory.decodeResource(getResources(), R.drawable.paddle_yellow);

    ballBmp = getResizedBitmap(ballBmp, BrickGameManager.BALL_WIDTH, BrickGameManager.BALL_HEIGHT);
    starBmp = getResizedBitmap(starBmp, BrickGameManager.STAR_WIDTH, BrickGameManager.STAR_HEIGHT);
    brickBmp = getResizedBitmap( brickBmp, getScreenWidth()/BrickGameManager.NUM_BRICKS_HORIZONTAL, BrickGameManager.BRICK_HEIGHT);
    brickDamagedBmp = getResizedBitmap( brickDamagedBmp, getScreenWidth()/BrickGameManager.NUM_BRICKS_HORIZONTAL, BrickGameManager.BRICK_HEIGHT);
    paddleBlueBmp = getResizedBitmap(paddleBlueBmp, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);
    paddleRedBmp = getResizedBitmap(paddleRedBmp, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);
    paddleYellowBmp = getResizedBitmap(paddleYellowBmp, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);

    ballBmps = new ArrayList<Bitmap>();
    ballBmps.add(ballBmp);
    starBmps = new ArrayList<Bitmap>();
    starBmps.add(starBmp);
    paddleBlueBmps = new ArrayList<Bitmap>();
    paddleBlueBmps.add(paddleBlueBmp);
    paddleRedBmps= new ArrayList<Bitmap>();
    paddleRedBmps.add(paddleRedBmp);
    paddleYellowBmps = new ArrayList<Bitmap>();
    paddleYellowBmps.add(paddleYellowBmp);

    ((BrickGameManager) gameManager)
            .setBmpfiles(
                    ballBmps,
                    starBmps,
                    brickBmp,
                    brickDamagedBmp,
                    paddleBlueBmps,
                    paddleRedBmps,
                    paddleYellowBmps);
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
   * draws this game view on the canvas
   *
   * @param canvas the canvas on which to draw
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawColor(((BrickGameManager) gameManager).getBackgroundColor());
      //gameManager.draw(canvas);
      List<GameItem> items = gameManager.getGameItems();
      for (GameItem item : items) {

        drawItem(canvas, item);
      }
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


  /**
   * code taken from: https://stackoverflow.com/questions/4837715/how-to-resize-a-bitmap-in-android
   *
   * @param bm
   * @param newWidth
   * @param newHeight
   * @return
   */
  public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;
    // CREATE A MATRIX FOR THE MANIPULATION
    Matrix matrix = new Matrix();
    // RESIZE THE BIT MAP
    matrix.postScale(scaleWidth, scaleHeight);

    // "RECREATE" THE NEW BITMAP
    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    bm.recycle();
    return resizedBitmap;


  }

}
