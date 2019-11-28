package com.example.game1.presentation.presenter.jumpinggame;

import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.common.MovementInfo;

public class JumpingMovementInfo extends MovementInfo {

  double numSeconds;
  Jumper jumper;

  Terrain terrain;
  int screenHeight;
  int screenWidth;

  public JumpingMovementInfo(
      int screenHeight, int screenWidth, Jumper jumper, Terrain terrain, double numSeconds) {
    // this.numSeconds = numSeconds;
    // this.item = item;
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.jumper = jumper;
    this.terrain = terrain;
    this.numSeconds = numSeconds;
  }

  public double getNumSeconds() {
    return numSeconds;
  }

  public void setNumSeconds(double numSeconds) {
    this.numSeconds = numSeconds;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
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
