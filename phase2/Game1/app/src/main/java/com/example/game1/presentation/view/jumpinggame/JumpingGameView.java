package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;


/** The view of the jumping game presented to the user. */
public class JumpingGameView extends GameView implements View.OnClickListener {


  private GameThread thread;


  private OnClickListener listener;
  private int numTaps = 0;
  private List<Bitmap> obstacleBmps;
  private List<Bitmap> starBmps;
  private Bitmap terrainBmp;
  private Bitmap jumperBlueBmp;
  private Bitmap jumperRedBmp ;
  private Bitmap jumperYellowBmp;
  private Bitmap jumperNinjaBmp;
  private final int SKY_COLOR_DARK = Color.rgb(83, 92, 104);
  private final int SKY_COLOR_LIGHT = Color.rgb(223, 249, 251);
  private List<Bitmap> jumperBlueBmps;
  private List<Bitmap> jumperRedBmps;
  private List<Bitmap> jumperYellowBmps;

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

    extractBmpFiles();




    ((JumpingGameManager) gameManager)
            .setAppearance(
                    obstacleBmps,
                    starBmps,
                    terrainBmp,
                    jumperBlueBmps,
                    jumperYellowBmps,
                    jumperRedBmps);

    ((JumpingGameManager)gameManager).setSkyColors(SKY_COLOR_DARK, SKY_COLOR_LIGHT);

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

    if (true) {
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

    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    Object appearance = item.getAppearance();
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
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


  public void extractBmpFiles(){
    Bitmap obstacleBmp = BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1);
    Bitmap starBmp = BitmapFactory.decodeResource(getResources(), R.drawable.star_6);
    terrainBmp = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
    jumperBlueBmp = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_blue);
    jumperRedBmp = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_red);
    jumperYellowBmp = BitmapFactory.decodeResource(getResources(), R.drawable.jumper_yellow);
    jumperNinjaBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ninja_idle__000);

    obstacleBmp = getResizedBitmap(obstacleBmp, 100, 100);
    starBmp = getResizedBitmap(starBmp, 80, 80);
    terrainBmp = getResizedBitmap( terrainBmp, getScreenWidth(), getScreenHeight()/2);
    jumperBlueBmp = getResizedBitmap(jumperBlueBmp, 100, 200);
    jumperRedBmp = getResizedBitmap(jumperRedBmp, 100, 200);
    jumperYellowBmp = getResizedBitmap(jumperYellowBmp, 100, 200);
    jumperNinjaBmp = getResizedBitmap(jumperNinjaBmp, 100, 200);
    

    obstacleBmps = new ArrayList<>();
    obstacleBmps.add(obstacleBmp);
    starBmps = new ArrayList<>();
    starBmps.add(starBmp);
  // @TODO don't need to load everything - only the customization that was chosen
    jumperBlueBmps = new ArrayList<>();
    jumperRedBmps = new ArrayList<>();
    jumperYellowBmps = new ArrayList<>();

    int[] jumperBlueFiles = {R.drawable.jumper_blue_1, R.drawable.jumper_blue_2, R.drawable.jumper_blue_3, R.drawable.jumper_blue_4,R.drawable.jumper_blue_5, R.drawable.jumper_blue_6, R.drawable.jumper_blue_7, R.drawable.jumper_blue_8 ,  R.drawable.jumper_blue_9,  R.drawable.jumper_blue_10};
    int[] jumperRedFiles = {R.drawable.jumper_red_1, R.drawable.jumper_red_2, R.drawable.jumper_red_3, R.drawable.jumper_red_4,R.drawable.jumper_red_5, R.drawable.jumper_red_6, R.drawable.jumper_red_7, R.drawable.jumper_red_8, R.drawable.jumper_red_9 , R.drawable.jumper_red_10 };
    int[] jumperYellowFiles = {R.drawable.jumper_yellow_1, R.drawable.jumper_yellow_2, R.drawable.jumper_yellow_3, R.drawable.jumper_yellow_4,R.drawable.jumper_yellow_5, R.drawable.jumper_yellow_6, R.drawable.jumper_yellow_7, R.drawable.jumper_yellow_8 };


    generateJumperBmps(jumperBlueBmps, jumperBlueFiles);
    generateJumperBmps(jumperRedBmps, jumperRedFiles);
    generateJumperBmps(jumperYellowBmps, jumperYellowFiles);

  }

  private void generateJumperBmps(List<Bitmap> dest, int[] files){
    for (int i: files){
      Bitmap b = BitmapFactory.decodeResource(getResources(), i);
      b = getResizedBitmap(b, JumpingGameManager.JUMPER_HEIGHT, JumpingGameManager.JUMPER_HEIGHT);
      dest.add(b);
    }
  }




}
