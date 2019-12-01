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
  }
}
