package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.model.common.MovementInfo;

public class JumpingMovementInfo extends MovementInfo {

  //double numSeconds;
  private Jumper jumper;

  private Terrain terrain;

  public JumpingMovementInfo(
      int screenHeight, int screenWidth, Jumper jumper, Terrain terrain, double numSeconds) {
    // this.numSeconds = numSeconds;
    // this.item = item;
    super(numSeconds, screenHeight, screenWidth);
    this.jumper = jumper;
    this.terrain = terrain;

  }

  public Jumper getJumper() {
    return jumper;
  }

  public void setJumper(Jumper jumper) {
    this.jumper = jumper;
  }

  public Terrain getTerrain() {
    return terrain;
  }

  public void setTerrain(Terrain terrain) {
    this.terrain = terrain;
  }
}
