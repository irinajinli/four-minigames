package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.R;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

public class JumpingGameView extends GameView {
  GameSprite jumperSprite;
  List<GameSprite> obstacleSprites;
  List<Obstacle> obstacles;
  List<GameSprite> starSprites;
  List<Star> stars;
  GameSprite terrainSprite;
  Terrain terrain;
  Jumper jumper;
  GameThread thread;
  private Context thisContext;
  private JumpingGameManager jgm;
  String jumperSpriteFile;

  public JumpingGameView(Context context) {
    super(context);
    //// check if this line below may cause any bugs
    thisContext = context;
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
  }

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

  public void createJumpingSprite(String jumperSpriteFile) {
    int resID =
        getResources().getIdentifier(jumperSpriteFile, "drawable", thisContext.getPackageName());
    jumperSprite =
        new GameSprite(BitmapFactory.decodeResource(getResources(), resID), jumper, this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    gameManager =
        AppManager.getInstance()
            .getJumpingGameManager(
                (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    ((JumpingGameManager) gameManager).setJumpingGameView(this);
    gameManager.createGameItems();
    gameManager.setActivity(activity);

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

    setJumperSpriteFile(jgm.jumper.characterColour);
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

  public void addStarSprite(Star star) {
    GameSprite starSprite =
        new GameSprite(BitmapFactory.decodeResource(getResources(), R.drawable.star_6), star, this);
    starSprites.add(starSprite);
  }

  private GameSprite generateObstacle(Obstacle obstacle) {
    GameSprite obstacleSprite =
        new GameSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1),
            obstacle,
            this);
    return obstacleSprite;
  }

  @Override
  public void update() {
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

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    jgm.onScreenTap();
    return super.onTouchEvent(event);
  }

  public int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public void gameOver() {
    thread.setRunning(false);
    gameManager.gameOver();
  }
}
