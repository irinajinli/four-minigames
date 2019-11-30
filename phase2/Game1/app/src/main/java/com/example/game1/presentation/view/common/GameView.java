package com.example.game1.presentation.view.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** The fish tank view. */
public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

  // TODO: make variables private where possible

  /** The width of a character. */
  public static float charWidth;
  /** The height of a character. */
  public static float charHeight;
  /** The manager. */
  public GameManager gameManager;
  /** The part of the program that manages time. */
  public GameThread thread;
  /** The activity class corresponding this view */
  public AppCompatActivity activity;
  /** This item's Paint. */
  public Paint paintText = new Paint();
  /** Hash Map that map the GameItems with its appearance */
  protected HashMap<String, List<Bitmap>> itemAppearances;
  /** Screen width. */
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  /** Screen height. */
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  private String characterColorScheme;

  private String backgroundColorScheme;
  private int skyColor;
  private int skyColorDark;
  private int skyColorLight;

  /**
   * Constructs a GameView with the given context.
   *
   * @param context the environment.
   */
  public GameView(Context context) {
    super(context);
    this.activity = (AppCompatActivity) context;
    getHolder().addCallback(this);
    setFocusable(true);
    itemAppearances = new HashMap<>();
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  @Override
  public abstract void surfaceCreated(SurfaceHolder holder);

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        thread.setRunning(false);
        thread.join();
        gameManager.stopMusic();

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      retry = false;
    }
  }

  /** Update this GameView's GameManager. */
  public void update() {
    boolean updated = gameManager.update();
    // stop thread if update fails
    if (!updated) {
      thread.setRunning(false);
    }
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawColor(getSkyColor());
      List<GameItem> items = gameManager.getGameItems();
      for (GameItem item : items) {

        drawItem(canvas, item);
      }
    }
  }



  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */

//  public abstract void drawItem(Canvas canvas, GameItem item);

  public void drawItem(Canvas canvas, GameItem item) {

    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    Object appearance = item.getDescription();
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
    if (appearance.getClass() == String.class) {

      // TODO NEW: remove * charWidth?
      canvas.drawText(
              (String) appearance,
              (float) xCoordinate * GameView.charWidth,
              (float) yCoordinate * GameView.charHeight,
              paintText);

    } else if (appearance.getClass() == Bitmap.class) {
      canvas.drawBitmap(
              (Bitmap) appearance,
              (int) Math.round(xCoordinate),
              (int) Math.round(yCoordinate),
              paintText);
    }
  }

  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   */
  public void drawItem(Canvas canvas, GameItem item, Bitmap appearance) {
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();

    canvas.drawBitmap(
            appearance,
            (int) Math.round(xCoordinate),
            (int) Math.round(yCoordinate),
            paintText);
  }

  /**
   * Resize a bitmap to the specified width and height. code taken from:
   * https://stackoverflow.com/questions/4837715/how-to-resize-a-bitmap-in-android
   *
   * @param bm the bitmap to be resized
   * @param newWidth the new width
   * @param newHeight the new height
   * @return the resized bitmap
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

  public Bitmap getNewBitmap(int resource, int width, int height) {
    Bitmap newBmp = BitmapFactory.decodeResource(getResources(), resource);
    newBmp = getResizedBitmap(newBmp, width, height);
    return newBmp;
  }

  public void generateAnimatedBmps(List<Bitmap> dest, int[] files, int width, int height) {

    for (int i : files) {
      dest.add(getNewBitmap(i, width, height));
    }
  }

  public abstract void extractBmpFiles();

  public HashMap<String, List<Bitmap>> getItemAppearances() {
    return itemAppearances;
  }

  public void addGameItemAppearance(String gameItemName, Bitmap image) {
    if (itemAppearances.containsKey(gameItemName)) {
      itemAppearances.get(gameItemName).add(image);
    } else {
      List<Bitmap> images = new ArrayList<>();
      images.add(image);
      itemAppearances.put(gameItemName, images);
    }
  }

  public void addGameItemAppearances(String gameItemName, List<Bitmap> images) {
    if (itemAppearances.containsKey(gameItemName)) {
      for (Bitmap image : images) {
        itemAppearances.get(gameItemName).add(image);
      }
    } else {
      itemAppearances.put(gameItemName, images);
    }
  }

  public List<Bitmap> getAppearances(String key) {
    return itemAppearances.get(key);
  }

  public Bitmap getAppearance(String key) {
    return itemAppearances.get(key).get(0);
  }

  public void generateBackgroundColor() {

    Customization cust = gameManager.getGame().getCustomization();
    if (cust.getColourScheme().equals(Customization.ColourScheme.DARK)) {
      this.backgroundColorScheme = "Dark";
    } else if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      this.backgroundColorScheme = "Light";
    }
  }

  public void generateCharacterColor() {
    Customization cust = gameManager.getGame().getCustomization();
    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      this.characterColorScheme = "Blue";
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      this.characterColorScheme = "Yellow";
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
      this.characterColorScheme = "Red";
    }
  }

  public void setSkyColors(int skyColorDark, int skyColorLight) {
    this.skyColorDark = skyColorDark;
    this.skyColorLight = skyColorLight;
  }

  public void generateSkyColor() {
    generateBackgroundColor();
    if (this.backgroundColorScheme.equals("Dark")) {
      setSkyColor(skyColorDark);
    } else if (this.backgroundColorScheme.equals("Light")) {
      setSkyColor(skyColorLight);
    }
  }

  public int getSkyColor() {
    return skyColor;
  }

  public void setSkyColor(int skyColor) {
    this.skyColor = skyColor;
  }

  public int getSkyColorDark() {
    return skyColorDark;
  }

  public void setSkyColorDark(int skyColorDark) {
    this.skyColorDark = skyColorDark;
  }

  public int getSkyColorLight() {
    return skyColorLight;
  }

  public void setSkyColorLight(int skyColorLight) {
    this.skyColorLight = skyColorLight;
  }

  public String getCharacterColorScheme() {
    return characterColorScheme;
  }

  /** */
  public int updateIndex(int index, int length) {
    index += 1;
    if (index == length) {
      index = 0;
    }
    return index;
  }
}
