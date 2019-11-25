package com.example.game1.presentation.model.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.view.common.GameItemOld;

import java.util.List;

/** Runner of the tapping game */
public class Runner extends AnimatedGameItem {



  private int speed;

  public boolean isCanRun() {
    return canRun;
  }

  public void setCanRun(boolean canRun) {
    this.canRun = canRun;
  }

  private boolean canRun;

  /**
   * Constructs a Jumper with the specified height, width, and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearances the appearances of this GameItem
   */
  public Runner(int height, int width, List appearances) {
    super(height, width, appearances);
    canRun = true;
  }

  /** Constructs a runner at the specified cursor location (x, y). */
  /**public Runner(List appearances, int x, int y) {
    super(x, y, appearances);
    setPosition(x, y);
    setAppearance(appearance);
    canRun = true;
  }*/



  public void move() {}
  /**
   * Move Runner according to the speed.
   *
   * @return
   */
  public boolean move(int width) {
    if (getXCoordinate() + speed  < width - 100) {
      double newX = getXCoordinate() + speed ;
      setPosition(newX, getYCoordinate());
      canRun = true;
      return canRun;
    }
    else{
      canRun = false;
      return canRun;
    }
  }


  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public Result update(ImportInfo jumpingImportInfo) {
    return (new Result());
  }
  public Result animate(ImportInfo importInfo){return new Result();}
}
