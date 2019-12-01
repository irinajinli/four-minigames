package com.example.game1.presentation.model.brickgame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;

public class Star extends AnimatedGameItem {

  /** The star. */

  /**
   * Constructs a Star with the specified height, width and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public Star(int width, int height) {

    super(width, height);
  }

//  @Override
//  /**
//   * @param jumper
//   * @return result: result needed by the game manager.
//   */
//  public BrickResult animate(MovementInfo brickMovementInfo) {
//    updatePositionAndVelocity(((BrickMovementInfo) brickMovementInfo).getNumSeconds());
//    BrickResult result = new BrickResult();
//
//    // Jumper jumper = ((JumpingMovementInfo) jumpingImportInfo).getJumper();
//    // if star is collected by the jumper,
//    // inform the jumping result to remove the star in the view
//    // and increment the numStar collected in the game manager
//    // if (this.isOverlapping(jumper)) {
//    //  result.setStarCollected(true);
//    //  result.addOldItem(this);
//    // }
//    //    else{
//    //      setXCoordinate(getxCoordinate() - 15);
//    //    }
//
//    return result;
//  }

  @Override
  public BrickResult update(MovementInfo brickMovementInfo) {
    return (new BrickResult());
  }
}
