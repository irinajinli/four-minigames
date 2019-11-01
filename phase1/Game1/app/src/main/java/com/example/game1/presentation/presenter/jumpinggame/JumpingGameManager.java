package com.example.game1.presentation.presenter.jumpinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.R;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Background;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.MainThread;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.jumpinggame.CoinSprite;
import com.example.game1.presentation.view.jumpinggame.GameObject;
import com.example.game1.presentation.view.jumpinggame.GameSprite;
import com.example.game1.presentation.view.jumpinggame.Jumper;
import com.example.game1.presentation.view.jumpinggame.JumperSprite;
import com.example.game1.presentation.view.jumpinggame.JumpingGameView;
import com.example.game1.presentation.view.jumpinggame.Obstacle;
import com.example.game1.presentation.view.jumpinggame.ObstacleSprite;
import com.example.game1.presentation.view.jumpinggame.Star;
import com.example.game1.presentation.view.jumpinggame.Terrain;
import com.example.game1.presentation.view.jumpinggame.TerrainSprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JumpingGameManager extends GameManager {
  /**
   * A GameManager for an Apple minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling Apples.
   */

  public JumpingGameView jgv;

 // JumperSprite jumperSprite;
  public List<Obstacle> obstacles;
  public List<Star> stars;
  public List<GameObject> queuedForRemoval;
  public Terrain terrain;
  public Jumper jumper;
  GameThread thread;
  public double cameraVelocityX = 450;
  public int numJumped = 0, numCoins = 0, numTaps = 0;
  Paint textPaint;
  private int skyColor;
  private String jumpingSpriteName;
  private Context thisContext;
  public int numStars;


  /** Constructs an AppleGameManager with a height and width of 10. */
  public JumpingGameManager() {
    super(10, 10);
  }

  /**
   * Constructs an AppleGameManager with the specified height and width.
   *
   * @param height the height of the AppleGameManager
   * @param width the width of the AppleGameManager
   */
  public JumpingGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.APPLE);
  }

  public void setJumpingGameView (JumpingGameView jgv){
    this.jgv = jgv;
  }
  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();
    setTheme(cust.getColourScheme());

    terrain = new Terrain(jgv.getScreenWidth(), getScreenHeight() / 2, this);
    terrain.setPositionX(0);
    terrain.setPositionY(getScreenHeight() / 2);

    jumper = new Jumper(100, 200, this);
      setCharacterColor(cust.getCharacterColour());
    jumper.setPositionY(terrain.getPositionY() - jumper.getHeight());
    jumper.setVelocityX(-cameraVelocityX + cameraVelocityX);

    obstacles = new ArrayList<>();
    obstacles.add(generateObstacle(getScreenWidth() * 8 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 3 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 6 / 5));


    stars = new ArrayList<>();
    addStar(getScreenWidth() * 3 /5);
    queuedForRemoval = new ArrayList<>();

  }


  public void queueForRemoval(GameObject gameObject){
    queuedForRemoval.add(gameObject);
    queuedForRemoval.add(gameObject);
  }

  public void setCharacterColor(Customization.CharacterColour characterColour){
    this.jumper.characterColour = characterColour;
  }

  public void setTheme(Customization.ColourScheme theme){
    if (theme.equals(Customization.ColourScheme.DARK)) {
      skyColor = Color.rgb(83, 92, 104);
    }
    else if (theme.equals((Customization.ColourScheme.LIGHT))){
      skyColor = Color.rgb(223,249,251);
    }
    else{
      skyColor = Color.rgb(204, 255, 255);
    }
  }





  public int getSkyColor (){
    return this.skyColor;
  }

  public void addStar(int xp){
    Star star = new Star(80, 80, this);
    star.setPositionY(terrain.getPositionY() - 4* obstacles.get(0).getHeight());
    star.setPositionX(xp);
    star.setVelocityX(-cameraVelocityX);
    stars.add(star);
  }



  private Obstacle generateObstacle(int px) {
    Obstacle obstacle = new Obstacle(100, 100, this);

    obstacle.setPositionY(terrain.getPositionY() - obstacle.getHeight());
    obstacle.setPositionX(px);
    obstacle.setVelocityX(-cameraVelocityX);
    return obstacle;
  }

  @Override
  public void update() {
      jumper.update();
      terrain.update();

      // note, right now, stars are the only object that get removed
    for(GameObject gameObject: queuedForRemoval){
      stars.remove(gameObject);
    }

    queuedForRemoval = new ArrayList<>();

    for (Star star: stars){
      star.update();
    }

    for (Obstacle obstacle : obstacles) {
      obstacle.update();
    }

  }


  public void onScreenTap(){
    if (jumper.getVelocityY() == 0) {
      jumper.setVelocityY(-2000);
      jumper.setAccelerationY(5000);
    }
    numTaps += 1;
  }

    public int getScreenWidth() {
      return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getScreenHeight() {
      return Resources.getSystem().getDisplayMetrics().heightPixels;
    }



  /** Ends this minigame. */
  public void gameOver() {
    // set points here
    game.setNumPoints(numJumped);
    game.setNumStars(numStars);
    game.setNumTaps(numTaps);
    System.out.println(numJumped + "  " + numStars + "  " + numTaps);
    super.gameOver();
  }
}
