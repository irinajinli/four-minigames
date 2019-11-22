package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Star;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.model.jumpinggame.Obstacle;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/** The view of the jumping game presented to the user. */
public class JumpingGameView extends GameView implements View.OnClickListener {
  private GameSprite jumperSprite;
  private List<GameSprite> obstacleSprites;
  private List<Obstacle> obstacles;
  private List<GameSprite> starSprites;
  private List<Star> stars;
  private GameSprite terrainSprite;
  private Terrain terrain;
  private Jumper jumper;
  private GameThread thread;
  // private Context thisContext;
  private JumpingGameManager jgm;
  private String jumperSpriteFile;
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
    //// check if this line below may cause any bugs
    // thisContext = context;
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

  //  /**
  //   * sets the string of the filename with the intended jumper sprite
  //   * @param characterColour the colour selected by the user
  //   */
  //  public void setJumperSpriteFile(Customization.CharacterColour characterColour) {
  //    if (characterColour.equals(Customization.CharacterColour.BLUE)) {
  //      this.jumperSpriteFile = "jumper_blue";
  //    } else if (characterColour.equals(Customization.CharacterColour.RED)) {
  //      this.jumperSpriteFile = "jumper_red";
  //    } else if (characterColour.equals(Customization.CharacterColour.YELLOW)) {
  //      this.jumperSpriteFile = "jumper_yellow";
  //    } else {
  //      this.jumperSpriteFile = "ninja_idle__000";
  //    }
  //  }

  //  /**
  //   * Creates the jumping sprite.
  //   * @param jumperSpriteFile the filename of the jumper sprite
  //   */
  //  public void createJumpingSprite(String jumperSpriteFile) {
  //    int resID =
  //        getResources().getIdentifier(jumperSpriteFile, "drawable",
  // thisContext.getPackageName());
  //    jumperSprite =
  //        new GameSprite(BitmapFactory.decodeResource(getResources(), resID), jumper, this);
  //  }

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
                            (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
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
    gameManager.setActivity(activity);
    thread.setRunning(true);
    thread.start();

    this.setOnClickListener(this.listener);

    //    jgm = (JumpingGameManager) gameManager;
    //
    //    terrain = jgm.getTerrain();
    //    jumper = jgm.getJumper();
    //    obstacles = jgm.getObstacles();
    //    stars = jgm.getStars();

    //    terrainSprite =
    //        new GameSprite(
    //            BitmapFactory.decodeResource(getResources(), R.drawable.grass), terrain, this);
    //    terrain.setPositionX(0);
    //    terrain.setPositionY(getScreenHeight() / 2);

    //    setJumperSpriteFile(jgm.getJumper().characterColour);
    //    createJumpingSprite(jumperSpriteFile);

    //    obstacleSprites = new ArrayList<>();
    //    for (Obstacle obstacle : obstacles) {
    //      obstacleSprites.add(generateObstacle(obstacle));
    //    }

    //    starSprites = new ArrayList<>();
    //    for (Star star : stars) {
    //      addStarSprite(star);
    //    }

  }

  //  /**
  //   * Creates a new start sprite and adds it to this game view
  //   * @param star the star for which to create the sprite
  //   */
  //  public void addStarSprite(Star star) {
  //    GameSprite starSprite =
  //        new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.star_6), star,
  // this);
  //    starSprites.add(starSprite);
  //  }
  //
  //  /**
  //   * Generates a new game obstacle sprite and returns it
  //   * @param obstacle the obstacle for which to create a sprite
  //   * @return a new game obstacle sprite
  //   */
  //  private GameSprite generateObstacle(Obstacle obstacle) {
  //    return new GameSprite(
  //        BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1), obstacle,
  // this);
  //  }

  /** Updates this game view */
  @Override
  public void update() {
    // get amount of time in seconds);

    boolean updated = gameManager.update();
    // stop thread if update fails
    if (!updated) {
      thread.setRunning(false);
    }


    //gameManager.update();
    //    if (jgm.getRunning() == false) {
    //      gameOver();
    //    } else {
    //
    //      jgm.update();
    //      // TODO right now, only stars need to be removed so this temporary solution simply
    // regenerates
    //      // the
    //      // list of stars and removes the old ones (inefficient; will optimize later)
    //      if (stars.size() != starSprites.size()) {
    //        starSprites = new ArrayList<>();
    //        for (Star star : stars) {
    //          addStarSprite(star);
    //        }
    //      }
    //    }
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
      canvas.drawColor(((JumpingGameManager) gameManager).getSkyColor());
      gameManager.draw(canvas);
      //      terrainSprite.draw(canvas);
      //
      //      for (GameSprite obstacleSprite : obstacleSprites) {
      //        obstacleSprite.draw(canvas);
      //      }
      //
      //      for (GameSprite star : starSprites) {
      //        star.draw(canvas);
      //      }
      //      jumperSprite.draw(canvas);
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
    ((JumpingGameManager) gameManager).onScreenTap();
    return super.onTouchEvent(event);
  }

  //  /**
  //   * Ends the current jumping game being view
  //   */
  //  public void gameOver() {
  //    thread.setRunning(false);
  //    gameManager.gameOver();
  //  }

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

}
