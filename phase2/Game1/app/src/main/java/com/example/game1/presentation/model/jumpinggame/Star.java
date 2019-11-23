package com.example.game1.presentation.model.jumpinggame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

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
  public JumpingResult animate(ImportInfo jumpingImportInfo) {
    updatePositionAndVelocity(((JumpingImportInfo) jumpingImportInfo).getNumOfSeconds());
    JumpingResult result = new JumpingResult();

    Jumper jumper = ((JumpingImportInfo) jumpingImportInfo).getJumper();
    // if star is collected by the jumper,
    // inform the jumping result to remove the star in the view
    // and increment the numStar collected in the game manager
    if (this.isOverlapping(jumper)) {
      result.setStarCollected(true);
      result.addOutItem(this);
    }
//    else{
//      setxCoordinate(getxCoordinate() - 15);
//    }

    return result;
  }

  @Override
  public void move() {}

  @Override
  public JumpingResult update(ImportInfo jumpingImportInfo) {
    return (new JumpingResult());
  }
}