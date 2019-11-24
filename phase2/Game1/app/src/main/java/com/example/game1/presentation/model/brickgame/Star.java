package com.example.game1.presentation.model.brickgame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.brickgame.BrickImportInfo;
import com.example.game1.presentation.presenter.brickgame.BrickResult;
import com.example.game1.presentation.presenter.common.ImportInfo;

public class Star extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a Star with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearance the appearance of this GameItem
   */
  public Star(int width, int height, Bitmap appearance) {

    super(width, height, appearance);
  }

  @Override
  /**
   * @param jumper
   * @return result: result needed by the game manager.
   */
  public BrickResult animate(ImportInfo brickImportInfo) {
    updatePositionAndVelocity(((BrickImportInfo) brickImportInfo).getNumOfSeconds());
    BrickResult result = new BrickResult();

    //Jumper jumper = ((JumpingImportInfo) jumpingImportInfo).getJumper();
    // if star is collected by the jumper,
    // inform the jumping result to remove the star in the view
    // and increment the numStar collected in the game manager
    //if (this.isOverlapping(jumper)) {
    //  result.setStarCollected(true);
    //  result.addOutItem(this);
    //}
//    else{
//      setXCoordinate(getxCoordinate() - 15);
//    }

    return result;
  }

  @Override
  public void move() {}

  @Override
  public BrickResult update(ImportInfo brickImportInfo) {
    return (new BrickResult());
  }
}
