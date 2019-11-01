package com.example.game1.presentation.view.jumpinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

public class ObstacleSprite extends GameSprite {

  public ObstacleSprite(Bitmap image, int width, int height, GameView gameView) {
    super(image, width, height, gameView);
  }

  @Override
  public void update(){
    super.update();
    JumpingGameView jgv = (JumpingGameView)gameView;
    if (this.isOverlapping(jgv.jumperSprite)){
      jgv.gameOver();
    }
    else if (this.getPositionX() + this.getWidth() < 0){
      this.setPositionX(gameView.getScreenWidth() * 4 / 3);
      jgv.numJumped += 1;
      if (Math.random() > 0.7){
        jgv.addCoin(gameView.getScreenWidth() * 4 / 3 + this.getWidth() / 2 - 80/2); // 80 is the diameter of the coin
      }
    }
  }
}