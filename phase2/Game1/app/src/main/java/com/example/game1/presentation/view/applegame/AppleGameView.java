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
import com.example.game1.presentation.model.applegame.Apple;
import com.example.game1.presentation.model.applegame.AppleStar;
import com.example.game1.presentation.model.applegame.Basket;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** Based on Fish Tank's FishTankView. */
public class AppleGameView extends GameView implements View.OnClickListener {

  private List<Bitmap> appleBmps;
  private List<Bitmap> starBmps;
  private List<Bitmap> basketBmps;
  private List<Bitmap> basketBlueBmps;
  private List<Bitmap> basketYellowBmps;
  private List<Bitmap> basketRedBmps;
  private final int BACKGROUND_COLOR_DARK = Color.BLACK;
  private final int BACKGROUND_COLOR_LIGHT = Color.LTGRAY;
  public static final int APPLE_WIDTH = 100;
  public static final int APPLE_HEIGHT = 100;
  public static final int STAR_WIDTH = 80;
  public static final int STAR_HEIGHT = 80;
  public static final int BASKET_WIDTH = 100;
  public static final int BASKET_HEIGHT = 100;
  private OnClickListener listener;
  
  // image files for game objects
  private final int[] APPLE_FILES = {R.drawable.apple_red};
  private final int[] STAR_FILES = {R.drawable.star_6};
  private final int[] BASKET_FILES = {R.drawable.basket_red};
  private final int[] BASKET_BLUE_FILES = {R.drawable.basket_blue};
  private final int[] basketYellowFiles = {R.drawable.basket_yellow};
  private final int[] BASKET_RED_FILES = {R.drawable.basket_red};

  /** Construct an AppleGameView with the specified Context. */
  public AppleGameView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this);
    this.listener =
        new OnClickListener() {
          @Override
          public void onClick(View v) {
            if (true) {
              gameManager.incrementNumTaps();
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
    generateCharacterColor();
    gameManager.setNumSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);
    if (gameManager instanceof AppleGameManager){
      ((AppleGameManager) gameManager).setItemSize(APPLE_WIDTH, APPLE_HEIGHT, STAR_WIDTH, STAR_HEIGHT, BASKET_WIDTH, BASKET_HEIGHT);
    }

    // TODO: new for colors
//    extractSkyColors();
    setBackgroundColorDark(BACKGROUND_COLOR_DARK);
    setBackgroundColorLight(BACKGROUND_COLOR_LIGHT);

    generateBackgroundColor();
    gameManager.createGameItems();
    thread.setRunning(true);
    thread.start();
    this.setOnClickListener(this.listener);
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    super.surfaceDestroyed(holder);
    Statistics gameStatistics = gameManager.getGame().getStatistics();
    gameStatistics.setTaps(
        gameStatistics.getTaps() + ((AppleGameManager) gameManager).getNumTaps());
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
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
    if (item instanceof LivesCounter || item instanceof PointsCounter) { // String appearance
      setupPaintText();
      Object appearance = item.getDescription();
      if (appearance instanceof String) {
        paintText.setColor(Color.WHITE);
      }
      canvas.drawText((String) appearance, (float) xCoordinate, (float) yCoordinate, paintText);
    }

    if (item instanceof Apple || item instanceof AppleStar || item instanceof Basket) {
      Bitmap appearance;
      String key;

      if (item instanceof Basket) {
        key = "Basket" + getCharacterColorScheme();

      } else {
        key = item.getClass().getSimpleName();
      }
      appearance = getAppearance(key);
      canvas.drawBitmap(
          appearance,
          (int) Math.round(xCoordinate),
          (int) Math.round(yCoordinate),
          paintText);
    }
  }

  public void extractBmpFiles() {
    // animated items require a list of bitmaps for each of their frames
    appleBmps = new ArrayList<Bitmap>();
    starBmps = new ArrayList<Bitmap>();
    basketBmps = new ArrayList<Bitmap>();
    // TODO NEW FOR CUST
    basketBlueBmps = new ArrayList<Bitmap>();
    basketYellowBmps = new ArrayList<Bitmap>();
    basketRedBmps = new ArrayList<Bitmap>();

    generateAnimatedBmps(
        appleBmps, APPLE_FILES, APPLE_WIDTH, APPLE_HEIGHT);
    generateAnimatedBmps(
        starBmps, STAR_FILES, STAR_WIDTH, STAR_HEIGHT);
    generateAnimatedBmps(
        basketBmps, BASKET_FILES, BASKET_WIDTH, BASKET_HEIGHT);
    generateAnimatedBmps(
        basketBlueBmps,
        BASKET_BLUE_FILES,
        BASKET_WIDTH,
        BASKET_HEIGHT);
    generateAnimatedBmps(
        basketYellowBmps,
        basketYellowFiles,
        BASKET_WIDTH,
        BASKET_HEIGHT);
    generateAnimatedBmps(
        basketRedBmps,
        BASKET_RED_FILES,
        BASKET_WIDTH,
        BASKET_HEIGHT);

    addGameItemAppearances("BasketYellow", basketYellowBmps);
    addGameItemAppearances("BasketBlue", basketBlueBmps);
    addGameItemAppearances("BasketRed", basketRedBmps);
    addGameItemAppearances("Apple", appleBmps);
    addGameItemAppearances("AppleStar", starBmps);
  }

  public String generateKey() {

    return "Basket" + getCharacterColorScheme();
  }

  public void setupPaintText() {
    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
  }


}
