package com.example.game1.presentation.view.jumpinggame;

import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

public class Star extends GameObject {

  public Star(int width, int height, JumpingGameManager jgm) {
    super(width, height, jgm);
  }

  @Override
  public void update(){
    super.update();
    if (this.isOverlapping(jgm.jumper)){
      jgm.numStars += 1;
      jgm.queueForRemoval(this);
    }
  }


}