package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.AnimatedGameItem;
import com.example.game1.presentation.model.common.MovementInfo;

public class Jumper extends AnimatedGameItem {
  /** The jumper. */

  /**
   * Constructs a Jumper with the specified height, width, and appearance.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public Jumper(int height, int width) {
    super(height, width);
  }

  @Override
  /**
   * @param jumpingMovementInfo: importInfo needed for this jumper to animate
   * @return the info needed by game manager after the animation
   */
  public JumpingResult animate(MovementInfo jumpingMovementInfo) {
    return (new JumpingResult());
//    JumpingResult jumpingResult = new JumpingResult();
//    // Jumper land on the terrain
//    Terrain terrain = ((JumpingMovementInfo) jumpingMovementInfo).getTerrain();
//    updatePositionAndVelocity(((JumpingMovementInfo) jumpingMovementInfo).getNumSeconds());
//    if (this.isOverlapping(terrain)) {
//      this.setYCoordinate(terrain.getYCoordinate() - this.getHeight());
//      this.setYVelocity(0);
//      this.setYAcceleration(0);
//    }
//    return jumpingResult;
  }

  @Override
  public JumpingResult update(MovementInfo jumpingMovementInfo) {
    updatePositionAndVelocity(jumpingMovementInfo.getNumSeconds());
    JumpingResult jumpingResult = new JumpingResult();
    // Jumper land on the terrain
    if (jumpingMovementInfo instanceof JumpingMovementInfo){
    Terrain terrain = ((JumpingMovementInfo) jumpingMovementInfo).getTerrain();

    if (this.isOverlapping(terrain)) {
      this.setYCoordinate(terrain.getYCoordinate() - this.getHeight());
      this.setYVelocity(0);
      this.setYAcceleration(0);
    }}
    return jumpingResult;
//    return (new JumpingResult());
  }
}
