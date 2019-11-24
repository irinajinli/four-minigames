package com.example.game1.presentation.model.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.common.Result;
import com.example.game1.presentation.view.common.GameItemOld;

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

  /** Constructs a runner at the specified cursor location (x, y). */
  public Runner(Object appearance, int x, int y) {
    super(appearance);
    setPosition(x, y);
    setAppearance(appearance);
    canRun = true;
  }



  public void move() {}
  /**
   * Move Runner according to the speed.
   *
   * @return
   */
  public boolean move(int width) {
    if (getxCoordinate() + speed  < width - 100) {
      double newX = getxCoordinate() + speed ;
      setPosition(newX, getyCoordinate());
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
