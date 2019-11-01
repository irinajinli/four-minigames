package com.example.game1.presentation.view.jumpinggame;

import android.graphics.Bitmap;

import com.example.game1.presentation.view.common.GameView;

public class CoinSprite extends GameSprite {
/**
  public CoinSprite(Bitmap image, int width, int height, GameView gameView) {
    super(image, width, height, gameView);
  }

  @Override
  public void update(){
    super.update();
    JumpingGameView jgv = (JumpingGameView)gameView;
    if (this.isOverlapping(jgv.jumperSprite)){
      jgv.numCoins += 1;
      jgv.queueForRemoval(this);
    }
  }

  */
}