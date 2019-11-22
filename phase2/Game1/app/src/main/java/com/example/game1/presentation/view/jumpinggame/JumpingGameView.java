package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.R;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Obstacle;
import com.example.game1.presentation.model.jumpinggame.Star;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

/**
 * The view of the jumping game presented to the user.
 */
public class JumpingGameView extends GameView {
  private GameSprite jumperSprite;
  private List<GameSprite> obstacleSprites;
  private List<Obstacle> obstacles;
  private List<GameSprite> starSprites;
  private List<Star> stars;
  private GameSprite terrainSprite;
  private Terrain terrain;
  private Jumper jumper;
  private GameThread thread;
  private Context thisContext;
  private JumpingGameManager jgm;
  private String jumperSpriteFile;

  /** creates a new JumpingView
   * *
   * @param context the context in which to create the view
   */
  public JumpingGameView(Context context) {
    super(context);
    //// check if this line below may cause any bugs
    thisContext = context;
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
  }

  /**
   * sets the string of the filename with the intended jumper sprite
   * @param characterColour the colour selected by the user
   */
  public void setJumperSpriteFile(Customization.CharacterColour characterColour) {
    if (characterColour.equals(Customization.CharacterColour.BLUE)) {
      this.jumperSpriteFile = "jumper_blue";
    } else if (characterColour.equals(Customization.CharacterColour.RED)) {
      this.jumperSpriteFile = "jumper_red";
    } else if (characterColour.equals(Customization.CharacterColour.YELLOW)) {
      this.jumperSpriteFile = "jumper_yellow";
    } else {
      this.jumperSpriteFile = "ninja_idle__000";
    }
  }

  /**
   * Creates the jumping sprite.
   * @param jumperSpriteFile the filename of the jumper sprite
   */
  public void createJumpingSprite(String jumperSpriteFile) {
    int resID =
        getResources().getIdentifier(jumperSpriteFile, "drawable", thisContext.getPackageName());
    jumperSprite =
        new GameSprite(BitmapFactory.decodeResource(getResources(), resID), jumper, this);
  }

  /**
   * initialiezes the game view when a surface is created
   * @param holder the surface holder holding the view
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    gameManager =
        AppManager.getInstance()
            .getJumpingGameManager(
                (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    gameManager.createGameItems();
    gameManager.setActivity(activity);
    gameManager.startMusic();

    jgm = (JumpingGameManager) gameManager;

    terrain = jgm.getTerrain();
    jumper = jgm.getJumper();
    obstacles = jgm.getObstacles();
    stars = jgm.getStars();

    terrainSprite =
        new GameSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.grass), terrain, this);
    terrain.setPositionX(0);
    terrain.setPositionY(getScreenHeight() / 2);

    setJumperSpriteFile(jgm.getJumper().characterColour);
    createJumpingSprite(jumperSpriteFile);

    obstacleSprites = new ArrayList<>();
    for (Obstacle obstacle : obstacles) {
      obstacleSprites.add(generateObstacle(obstacle));
    }

    starSprites = new ArrayList<>();
    for (Star star : stars) {
      addStarSprite(star);
    }

    thread.setRunning(true);
    thread.start();
  }

  /**
   * Creates a new start sprite and adds it to this game view
   * @param star the star for which to create the sprite
   */
  public void addStarSprite(Star star) {
    GameSprite starSprite =
        new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.star_6), star, this);
    starSprites.add(starSprite);
  }

  /**
   * Generates a new game obstacle sprite and returns it
   * @param obstacle the obstacle for which to create a sprite
   * @return a new game obstacle sprite
   */
  private GameSprite generateObstacle(Obstacle obstacle) {
    return new GameSprite(
        BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1), obstacle, this);
  }

  /**
   * Updates this game view
   */
  @Override
  public void update() {
    if (jgm.getRunning() == false) {
      gameOver();
    } else {

      jgm.update();
      // TODO right now, only stars need to be removed so this temporary solution simply regenerates
      // the
      // list of stars and removes the old ones (inefficient; will optimize later)
      if (stars.size() != starSprites.size()) {
        starSprites = new ArrayList<>();
        for (Star star : stars) {
          addStarSprite(star);
        }
      }
    }
  }

  /**
   * draws this game view on the canvas
   * @param canvas the canvas on which to draw
   */
  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawColor(jgm.getSkyColor());
      terrainSprite.draw(canvas);

      for (GameSprite obstacleSprite : obstacleSprites) {
        obstacleSprite.draw(canvas);
      }

      for (GameSprite star : starSprites) {
        star.draw(canvas);
      }
      jumperSprite.draw(canvas);
    }
  }

  /**
   * Handles the jumper's jump when the user taps the screen
   * @param event the event for the screen tap
   * @return true or false depending on the implementation of the superclass
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    jgm.onScreenTap();
    return super.onTouchEvent(event);
  }

  /**
   * Ends the current jumping game being view
   */
  public void gameOver() {
    thread.setRunning(false);
    gameManager.gameOver();
  }
}
