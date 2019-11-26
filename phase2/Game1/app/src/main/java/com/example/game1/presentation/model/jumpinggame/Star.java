package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.presenter.common.ImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingImportInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

import java.util.List;

public class Star extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a Star with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   * @param appearances the appearances of this GameItem
   */
  public Star(int width, int height, List appearances) {

    super(width, height, appearances);
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
    //      setXCoordinate(getxCoordinate() - 15);
    //    }

    return result;
  }

  public void animate(double numOfSeconds) {
    updatePositionAndVelocity(numOfSeconds);
  }

  @Override
  public void move() {}

  @Override
  public JumpingResult update(ImportInfo jumpingImportInfo) {
    return (new JumpingResult());
  }
}
