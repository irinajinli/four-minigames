package com.example.game1.presentation.view.jumpinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;

public class JumpingGameView extends GameView {
  JumperSprite jumperSprite;
  List<ObstacleSprite> obstacles;
  List<CoinSprite> coins;
  List<GameSprite> queuedForRemoval;
  TerrainSprite terrainSprite;
  GameThread thread;
  public double cameraVelocityX = 450;
  public int numJumped = 0, numCoins = 0, numTaps = 0;
  Paint textPaint;
  private int skyColor = Color.rgb(204, 255, 255);
  private String jumpingSpriteName;

  public JumpingGameView(Context context) {
    super(context);
    getHolder().addCallback(this);
    thread = new GameThread(getHolder(), this);
    setFocusable(true);
  }

  public void queueForRemoval(GameSprite sprite){
    queuedForRemoval.add(sprite);
    queuedForRemoval.add(sprite);
  }

  public void setJumperSprite(String nickName){
    if (nickName.equals("BLUE")){
      this.jumpingSpriteName = "ninja_idle_000";
    }
    else{
      this.jumpingSpriteName = "ninja_idle_000";
    }
  }

  public void createJumpingSprite(){
    int resID = getResources().getIdentifier(jumpingSpriteName, "drawable", "com.example.game1.presentation");
    jumperSprite =
            new JumperSprite(
                    BitmapFactory.decodeResource(getResources(), resID),
                    100,
                    200,
                    this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    terrainSprite =
        new TerrainSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.grass),
            getScreenWidth(),
            getScreenHeight() / 2,
            this);
    terrainSprite.setPositionX(0);
    terrainSprite.setPositionY(getScreenHeight() / 2);

    jumperSprite =
        new JumperSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.ninja_idle__000),
            100,
            200,
            this);
    jumperSprite.setPositionY(terrainSprite.getPositionY() - jumperSprite.getHeight());

    obstacles = new ArrayList<>();
    obstacles.add(generateObstacle(getScreenWidth() * 8 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 3 / 5));
    obstacles.add(generateObstacle(getScreenWidth() * 6 / 5));

    jumperSprite.setVelocityX(-cameraVelocityX + cameraVelocityX);

    coins = new ArrayList<>();
    queuedForRemoval = new ArrayList<>();
    addCoin(getScreenWidth() * 3 /5);
    textPaint = new Paint();
    textPaint.setColor(Color.rgb(250, 250, 250));
    textPaint.setStyle(Paint.Style.FILL);
    textPaint.setAntiAlias(true);
    textPaint.setTextSize(30);


    /////
    // TODO WHY THIS CODE
    gameManager =
            AppManager.getInstance()
                    .getJumpingGameManager(
                            (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    ((JumpingGameManager)gameManager).setJumpingGameView(this);
    gameManager.createGameItems();
    gameManager.setActivity(activity);
    ////
    thread.setRunning(true);
    thread.start();
  }

  public void skyColor (int newColor){
    this.skyColor = newColor;
  }
  public void addCoin(int xp){
    CoinSprite coin = new CoinSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.gold_1),
            80,
            80,
            this);
    coin.setPositionY(terrainSprite.getPositionY() - 4* obstacles.get(0).getHeight());
    coin.setPositionX(xp);
    coin.setVelocityX(-cameraVelocityX);
    coins.add(coin);
  }
  private ObstacleSprite generateObstacle(int px) {
    ObstacleSprite obstacleSprite =
        new ObstacleSprite(
            BitmapFactory.decodeResource(getResources(), R.drawable.wooden_blocks_1),
            100,
            100,
            this);
    obstacleSprite.setPositionY(terrainSprite.getPositionY() - obstacleSprite.getHeight());
    obstacleSprite.setPositionX(px);
    obstacleSprite.setVelocityX(-cameraVelocityX);
    return obstacleSprite;
  }

  @Override
  public void update() {
    for(GameSprite sprite: queuedForRemoval){
      coins.remove(sprite);
    }
    queuedForRemoval = new ArrayList<>();
    for (CoinSprite coin: coins){
      coin.update();
    }
    jumperSprite.update();
    for (ObstacleSprite obstacleSprite : obstacles) {
      obstacleSprite.update();
    }
    terrainSprite.update();
  }

  public void displayJumps(Canvas canvas) {
    canvas.drawText("" + numJumped, 100, 100, textPaint);
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    if (canvas != null) {
      canvas.drawColor(skyColor);
      // Paint paint = new Paint();
      // paint.setColor(Color.rgb(250, 0, 0));
      // canvas.drawRect(0, getScreenHeight() * 1 / 2, getScreenWidth(), getScreenHeight(), paint);

      terrainSprite.draw(canvas);

      for (ObstacleSprite obstacleSprite : obstacles) {
        obstacleSprite.draw(canvas);
      }
      for (CoinSprite coin: coins){
        coin.draw(canvas);
      }
      jumperSprite.draw(canvas);

      //displayJumps(canvas);
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (jumperSprite.getVelocityY() == 0) {
      jumperSprite.setVelocityY(-2000);
      jumperSprite.setAccelerationY(5000);
    }
    numTaps += 1;
    return super.onTouchEvent(event);
  }

  public int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  public void gameOver(){
    thread.setRunning(false);
    gameManager.gameOver();
  }
}
