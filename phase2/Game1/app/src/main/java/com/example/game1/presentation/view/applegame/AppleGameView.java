package com.example.game1.presentation.view.applegame;

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
import com.example.game1.presentation.model.Statistics;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** Based on Fish Tank's FishTankView. */
public class AppleGameView extends GameView implements View.OnClickListener{

  private List<Bitmap> appleBmps;
  private List<Bitmap> starBmps;
  private List<Bitmap> basketBmps;
  private List<Bitmap> basketBlueBmps;
  private List<Bitmap> basketYellowBmps;

  private int skyColorDark;
  private int skyColorLight;
  private int skyColorDefault;
  private OnClickListener listener;

  /** Construct an AppleGameView with the specified Context. */
  public AppleGameView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this);
    this.listener =
            new OnClickListener() {
              @Override
              public void onClick(View v) {
                if (true) {
                  ((AppleGameManager) gameManager).incrementNumTaps();
                }
              }
            };
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    // use screen height and width to determine the size of the GameManager
    gameManager =
        AppManager.getInstance()
            .buildGameManager(Game.GameName.APPLE, getScreenHeight(), getScreenWidth(), activity);
    gameManager.startMusic();

    extractBmpFiles();

    ((AppleGameManager) gameManager)
        .setBMPFiles(appleBmps, starBmps, basketBmps, basketBlueBmps, basketYellowBmps);
    ((AppleGameManager) gameManager).setNumSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    // TODO: new for colors
    extractSkyColors();
    setSkyColorDark(skyColorDark);
    setSkyColorLight(skyColorLight);

    generateSkyColor();
    gameManager.createGameItems();
    thread.setRunning(true);
    thread.start();
    this.setOnClickListener(this.listener);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
    Statistics gameStatistics = gameManager.getGame().getStatistics();
    gameStatistics.setTaps(gameStatistics.getTaps() + ((AppleGameManager) gameManager).getNumTaps());
  }

  @Override
  public void onClick(View v) {
    ((AppleGameManager) gameManager).incrementNumTaps();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // move basket to location of tap
    double touchX = event.getX();
    ((AppleGameManager) gameManager).moveBasket((int) touchX);

    return super.onTouchEvent(event);

    // TODO: change game.numTaps when the game is over instead of adding 1 each time?
  }

  @Override
  public void drawItem(Canvas canvas, GameItem item) {
    if (item instanceof AnimatedGameItem) { // Bitmap appearance
      Object appearance2 = item.getAppearance();
      double xCoordinate2 = item.getXCoordinate();
      double yCoordinate2 = item.getYCoordinate();

      if (appearance2.getClass() == Bitmap.class) {
        canvas.drawBitmap(
            (Bitmap) appearance2,
            (int) Math.round(xCoordinate2),
            (int) Math.round(yCoordinate2),
            paintText);
      }
    }
    else if (item.getAppearance() instanceof String) { // String appearance

      paintText = new Paint();
      paintText.setTypeface(Typeface.DEFAULT_BOLD);
      paintText.setTextSize(36);

      Object appearance = item.getAppearance();
      double xCoordinate = item.getXCoordinate();
      double yCoordinate = item.getYCoordinate();
      if (appearance.getClass() == String.class) {
          paintText.setColor(Color.WHITE);
        }
        canvas.drawText((String) appearance, (float) xCoordinate, (float) yCoordinate, paintText);
      }
    }

  public void extractBmpFiles() {
    int[] appleFiles = {R.drawable.apple_red};
    int[] starFiles = {R.drawable.star_6};
    int[] basketFiles = {R.drawable.basket_red};
    int[] basketBlueFiles = {R.drawable.basket_blue};
    int[] basketYellowFiles = {R.drawable.basket_yellow};

    // animated items require a list of bitmaps for each of their frames
    appleBmps = new ArrayList<Bitmap>();
    starBmps = new ArrayList<Bitmap>();
    basketBmps = new ArrayList<Bitmap>();
    // TODO NEW FOR CUST
    basketBlueBmps = new ArrayList<Bitmap>();
    basketYellowBmps = new ArrayList<Bitmap>();

    generateAnimatedBmps(
        appleBmps, appleFiles, AppleGameManager.APPLE_WIDTH, AppleGameManager.APPLE_HEIGHT);
    generateAnimatedBmps(
        starBmps, starFiles, AppleGameManager.STAR_WIDTH, AppleGameManager.STAR_HEIGHT);
    generateAnimatedBmps(
        basketBmps, basketFiles, AppleGameManager.BASKET_WIDTH, AppleGameManager.BASKET_HEIGHT);
    generateAnimatedBmps(
        basketBlueBmps,
        basketBlueFiles,
        AppleGameManager.BASKET_WIDTH,
        AppleGameManager.BASKET_HEIGHT);
    generateAnimatedBmps(
        basketYellowBmps,
        basketYellowFiles,
        AppleGameManager.BASKET_WIDTH,
        AppleGameManager.BASKET_HEIGHT);
  }

  public void extractSkyColors() {
    skyColorDark = Color.BLACK;
    skyColorLight = Color.LTGRAY;
    skyColorDefault = Color.BLACK;
  }
}
