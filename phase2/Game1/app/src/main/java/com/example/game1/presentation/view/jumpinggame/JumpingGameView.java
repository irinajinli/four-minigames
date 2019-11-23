package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;



/** The view of the jumping game presented to the user. */
public class JumpingGameView extends GameView implements View.OnClickListener {


  private GameThread thread;


  private OnClickListener listener;
  private int numTaps = 0;
  private Bitmap obstacleBMP =
          BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1);
  private Bitmap starBMP = BitmapFactory.decodeResource(getResources(), R.drawable.star_6);
  private Bitmap terrainBMP = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
  private Bitmap jumperBlueBMP =
          BitmapFactory.decodeResource(getResources(), R.drawable.jumper_blue);
  private Bitmap jumperRedBMP = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_red);
  private Bitmap jumperYellowBMP =
          BitmapFactory.decodeResource(getResources(), R.drawable.jumper_yellow);
  private Bitmap jumperNinjaBMP =
          BitmapFactory.decodeResource(getResources(), R.drawable.ninja_idle__000);

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
                if (true) {
                  numTaps++;
                  ((JumpingGameManager) gameManager).setNumTaps(numTaps);
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
                    .getJumpingGameManager(
                            (int) (getScreenHeight() / charHeight),
                            (int) (getScreenWidth() / charWidth),
                            activity);
    gameManager.setScreenHeight(this.getScreenHeight());
    gameManager.setScreenWidth(this.getScreenWidth());
    ((JumpingGameManager)gameManager).setNumOfSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    obstacleBMP = BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1);
    starBMP = BitmapFactory.decodeResource(getResources(), R.drawable.star_6);
    terrainBMP = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
    jumperBlueBMP = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_blue);
    jumperRedBMP = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_red);
    jumperYellowBMP = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_yellow);
    jumperNinjaBMP = BitmapFactory.decodeResource(getResources(), R.drawable.ninja_idle__000);

    obstacleBMP = getResizedBitmap(obstacleBMP, 100, 100);
    starBMP = getResizedBitmap(starBMP, 80, 80);
    terrainBMP = getResizedBitmap( terrainBMP, getScreenWidth(), getScreenHeight()/2);
    jumperBlueBMP = getResizedBitmap(jumperBlueBMP, 100, 200);
    jumperRedBMP = getResizedBitmap(jumperRedBMP, 100, 200);
    jumperYellowBMP = getResizedBitmap(jumperYellowBMP, 100, 200);
    jumperNinjaBMP = getResizedBitmap(jumperNinjaBMP, 100, 200);



    ((JumpingGameManager) gameManager)
            .setBMPfiles(
                    obstacleBMP,
                    starBMP,
                    terrainBMP,
                    jumperBlueBMP,
                    jumperYellowBMP,
                    jumperRedBMP,
                    jumperNinjaBMP);
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

//  /**
//   * draws this game view on the canvas
//   *
//   * @param canvas the canvas on which to draw
//   */
//  @Override
//  public void draw(Canvas canvas) {
//    super.draw(canvas);
//    if (canvas != null) {
//      canvas.drawColor(((JumpingGameManager) gameManager).getSkyColor());
//      gameManager.draw(canvas);
//
//    }
//  }

  /**
   * Handles the jumper's jump when the user taps the screen
   *
   * @param event the event for the screen tap
   * @return true or false depending on the implementation of the superclass
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    ((JumpingGameManager) gameManager).onScreenTap();
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
      ((TappingGameManager) gameManager).tapCounter.setNumTaps(numTaps);
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

  @Override
  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item) {

    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    Object appearance = item.getAppearance();
    double xCoordinate = item.getxCoordinate();
    double yCoordinate = item.getyCoordinate();
    if (appearance.getClass() == String.class) {

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
