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
  private float charWidth;
  /** The height of a character. */
  private float charHeight;
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
  private int backgroundColor;
  private int backgroundColorDark;
  private int backgroundColorLight;

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

  /**
   * Returns the width of this game view
   * @return the width of this game view
   */
  public int getScreenWidth() {
    return screenWidth;
  }

  /**
   * Returns the height of this game view
   * @return the height of this game view
   */
  public int getScreenHeight() {
    return screenHeight;
  }


  /**
   * Initializes the game view when it is created
   * @param holder the holder holding this game view
   */
  @Override
  public abstract void surfaceCreated(SurfaceHolder holder);

  /**
   * Adjusts the game view when a change is made to the surface.
   * @param holder the holder containing this game view
   * @param format the format of the surface
   * @param width the width of the surface
   * @param height the height of the surface
   */
  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  /**
   * Stops the game when the game view is closed
   *
   * @param holder the SurfaceHolder holding this game view
   */
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

  /** Update this GameView's GameManager. */
  public void update() {
    boolean updated = gameManager.update();
    // stop thread if update fails
    if (!updated) {
      thread.setRunning(false);
    }
  }

  /**
   * draws all the items in this game
   *
   * @param canvas the canvas on which to draw
   */
  public void drawItems(Canvas canvas) {
    List<GameItem> items = gameManager.getGameItems();
    for (GameItem item : items) {
      drawItem(canvas, item);
    }
  }

  /**
   * Draws this entire game view
   *
   * @param canvas the canvas on which to draw
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawColor(getBackgroundColor());
      drawItems(canvas);
    }
  }

  /**
   * Draw this GameItem.
   *
   * @param canvas the canvas on which to draw this item.
   * @param item the item to draw
   */
  public void drawItem(Canvas canvas, GameItem item) {

    paintText = new Paint();
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setTextSize(36);
    Object appearance = item.getDescription();
    double xCoordinate = item.getXCoordinate();
    double yCoordinate = item.getYCoordinate();
    if (appearance.getClass() == String.class) {
      canvas.drawText(
          (String) appearance,
          (float) xCoordinate * charWidth,
          (float) yCoordinate * charHeight,
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
        appearance, (int) Math.round(xCoordinate), (int) Math.round(yCoordinate), paintText);
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

  /**
   * Returns a bitmap image from the given resource with width and height given by the parameters
   * width and height respectively
   *
   * @param resource the image resource to draw
   * @param width the desired width
   * @param height the desired height
   * @return a resized bitmap image using the given resource
   */
  public Bitmap getNewBitmap(int resource, int width, int height) {
    Bitmap newBmp = BitmapFactory.decodeResource(getResources(), resource);
    newBmp = getResizedBitmap(newBmp, width, height);
    return newBmp;
  }

  /**
   * Generates a list of resized bitmap image for an animated item (one image for each frame of its
   * animation) and stores them in dest
   *
   * @param dest the destination list to store all the bitmap image frames
   * @param files the files for each frame of the animation
   * @param width the width of the desired images
   * @param height the height of the desired images
   */
  public void generateAnimatedBmps(List<Bitmap> dest, int[] files, int width, int height) {
    for (int i : files) {
      dest.add(getNewBitmap(i, width, height));
    }
  }

  /**
   * Maps each item in the game to its associated bitmap images
   */
  public abstract void extractBmpFiles();

  /**
   * Returns the map of items to appearances in this game view
   * @return the map of items to appearances in this game view
   */
  public HashMap<String, List<Bitmap>> getItemAppearances() {
    return itemAppearances;
  }

  /**
   * Maps gameItemName to the appearance image
   * @param gameItemName the name of the item whose appearances is mapped
   * @param image the bitmap image of the item
   */
  public void addGameItemAppearance(String gameItemName, Bitmap image) {
    if (itemAppearances.containsKey(gameItemName)) {
      itemAppearances.get(gameItemName).add(image);
    } else {
      List<Bitmap> images = new ArrayList<>();
      images.add(image);
      itemAppearances.put(gameItemName, images);
    }
  }

  /**
   * Maps a list of item appearances (one for each frame) to a given item
   * @param gameItemName the item whose appearances need to be mapped
   * @param images the list of images of the item
   */
  public void addGameItemAppearances(String gameItemName, List<Bitmap> images) {
    if (itemAppearances.containsKey(gameItemName)) {
      for (Bitmap image : images) {
        itemAppearances.get(gameItemName).add(image);
      }
    } else {
      itemAppearances.put(gameItemName, images);
    }
  }

  /**
   * Returns the appearances associated with a given game item.
   * @param key the key for the given game item
   * @return a list of images for that item
   */
  public List<Bitmap> getAppearances(String key) {
    return itemAppearances.get(key);
  }

  /**
   * Returns the bitmap image appearance associated with a given game item.
   * @param key the key for the given game item
   * @return the image of the item
   */
  public Bitmap getAppearance(String key) {
    return itemAppearances.get(key).get(0);
  }

  /**
   * Initializes the background colour scheme based on the user's selection.
   */
  public void generateBackgroundColorScheme() {

    Customization cust = gameManager.getGame().getCustomization();
    if (cust.getColourScheme().equals(Customization.ColourScheme.DARK)) {
      this.backgroundColorScheme = "Dark";
    } else if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      this.backgroundColorScheme = "Light";
    }
  }

  /**
   * Initializes the character's colour settings based on the user's customization settings.
   */
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

  /**
   * Sets the background colour based upon the user's selection
   */
  public void generateBackgroundColor() {
    generateBackgroundColorScheme();
    if (this.backgroundColorScheme.equals("Dark")) {
      setScreenBackgroundColor(backgroundColorDark);
    } else if (this.backgroundColorScheme.equals("Light")) {
      setScreenBackgroundColor(backgroundColorLight);
    }
  }

  /**
   * Returns the background colour for this game
   * @return the background colour for this game
   */
  public int getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Sets the background colour for this game
   * @param backgroundColor the background colour to set
   */
  public void setScreenBackgroundColor(int backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * sets the colour designated as the dark background colour
   * @param backgroundColorDark the colour to designate as the dark background colour
   */
  public void setBackgroundColorDark(int backgroundColorDark) {
    this.backgroundColorDark = backgroundColorDark;
  }

  /**
   * sets the colour designated as the light background colour
   * @param backgroundColorLight the colour to designate as the light background colour
   */
  public void setBackgroundColorLight(int backgroundColorLight) {
    this.backgroundColorLight = backgroundColorLight;
  }

  /**
   * Returns the character colour scheme for this game
   * @return the character colour scheme for this game
   */
  public String getCharacterColorScheme() {
    return characterColorScheme;
  }

  /**
   * Returns the next index in a given game item's animation. Loops from 0 to the end of the list storing it's appearances
   * @param index the current index
   * @param length the length of the list containing the item's appearances.
   * */
  public int updateIndex(int index, int length) {
    index += 1;
    if (index == length) {
      index = 0;
    }
    return index;
  }

  /** Returns the width of a character in this game view.
   *
    * @return the width of a character in this game view.
   */
  public float getCharWidth(){
    return charWidth;
  }

  /** Returns the height of a character in this game view.
   *
   * @return the height of a character in this game view.
   */
  public float getCharHeight(){
    return charHeight;
  }

  /**
   * Sets the character width for this game view
   * @param charWidth the character width
   */
  public void setCharWidth(float charWidth){
    this.charWidth = charWidth;
  }

  /**
   * Sets the character height for this game view
   * @param charHeight the character height to set
   */
  public void setCharHeight(float charHeight){
    this.charHeight = charHeight;
  }
}
