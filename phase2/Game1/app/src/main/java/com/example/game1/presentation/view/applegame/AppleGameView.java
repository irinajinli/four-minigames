package com.example.game1.presentation.view.applegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.AppManager;
import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.applegame.LivesCounter;
import com.example.game1.presentation.model.applegame.PointsCounter;
import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** Based on Fish Tank's FishTankView. */
public class AppleGameView extends GameView {

  // TODO: new for images AND NEW FOR CUST
  private List<Bitmap> appleBmps;
  private List<Bitmap> starBmps;
  private List<Bitmap> basketBmps;
  private List<Bitmap> basketBlueBmps;
  private List<Bitmap> basketYellowBmps;

  // TODO: new for colors
  private int skyColorDark;
  private int skyColorLight;
  private int skyColorDefault;

  /** Construct an AppleGameView with the specified Context. */
  public AppleGameView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // todo: new for skycolor

    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    // TODO: DELETE NEXT 2 LINES
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    // use screen height and width to determine the size of the GameManager
    gameManager =
            AppManager.getInstance()
                    .buildGameManager(Game.GameName.APPLE, getScreenHeight(), getScreenWidth(), activity);
    gameManager.startMusic();

    extractBmpFiles();

    ((AppleGameManager) gameManager)
        .setBMPFiles(appleBmps, starBmps, basketBmps, basketBlueBmps, basketYellowBmps);
    ((AppleGameManager)gameManager).setNumOfSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    // TODO: new for colors
    extractSkyColors();
    //        ((AppleGameManager) gameManager).setSkyColors(skyColorDark, skyColorLight,
    // skyColorDefault);
    setSkyColorDark(skyColorDark);
    setSkyColorLight(skyColorLight);

    generateSkyColor();
    gameManager.createGameItems();
    thread.setRunning(true);
    thread.start();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // count tap in game's numTaps
    Game game = gameManager.getGame();
    game.getStatistics().setTaps(game.getStatistics().getTaps() + 1);

    double touchX = event.getX();
    ((AppleGameManager) gameManager).moveBasket((int) touchX);

    return true;

    // TODO: change game.numTaps when the game is over instead of adding 1 each time?
  }

  @Override
  public void drawItem(Canvas canvas, GameItem item) {
    // TODO: new
    if (item instanceof AnimatedGameItem) {
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

    if (item instanceof Background) {
      Rect backgroundRect = new Rect(0, 0, 999999, 999999);
      Paint backgroundPaint = new Paint();
      backgroundPaint.setStyle(Paint.Style.STROKE);
      backgroundPaint.setStrokeWidth(5);
      backgroundPaint.setAntiAlias(true);
      backgroundPaint.setColor(Color.DKGRAY);
      backgroundPaint.setStyle(Paint.Style.FILL);
      canvas.drawRect(backgroundRect, backgroundPaint);
    } else { // String appearance

      paintText = new Paint();
      paintText.setTypeface(Typeface.DEFAULT_BOLD);
      paintText.setTextSize(36);

      Object appearance = item.getAppearance();
      double xCoordinate = item.getXCoordinate();
      double yCoordinate = item.getYCoordinate();
      if (appearance.getClass() == String.class) {
        //        if (item instanceof Basket) {
        //          if (gameManager
        //              .getGame()
        //              .getCustomization()
        //              .getCharacterColour()
        //              .equals(Customization.CharacterColour.BLUE)) {
        //            paintText.setColor(Color.BLUE);
        //          } else if (gameManager
        //              .getGame()
        //              .getCustomization()
        //              .getCharacterColour()
        //              .equals(Customization.CharacterColour.RED)) {
        //            paintText.setColor(Color.RED);
        //          } else if (gameManager
        //              .getGame()
        //              .getCustomization()
        //              .getCharacterColour()
        //              .equals(Customization.CharacterColour.YELLOW)) {
        //            paintText.setColor(Color.YELLOW);
        //          }
        //        }
        // TODO: fix instanceof if statement
        if (item instanceof PointsCounter || item instanceof LivesCounter) {
          paintText.setColor(Color.WHITE);
        }
        canvas.drawText((String) appearance, (float) xCoordinate, (float) yCoordinate, paintText);

        // canvas.drawText((String) appearance, x * TappingGameView.charWidth, y *
        // TappingGameView.charHeight, paintText);
        //      } else if (appearance.getClass() == Bitmap.class) {
        //        canvas.drawBitmap(
        //                (Bitmap) appearance,
        //                (int) Math.round(xCoordinate),
        //                (int) Math.round(yCoordinate),
        //                paintText);
        //      }
      }
    }
  }

  public void extractBmpFiles() {
    int[] appleFiles = {R.drawable.apple_red};
    int[] starFiles = {R.drawable.star_6};
    int[] basketFiles = {R.drawable.basket_red};
    // TODO NEW FOR CUST
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
