package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.MovementInfo;
import com.example.game1.presentation.presenter.jumpinggame.JumpingResult;

public class Terrain extends GameItem {
  /** The terrain. */

  /**
   * Constructs a Terrain with the specified height and width.
   *
   * @param height the height of this GameItem
   * @param width the width of this GameItem
   */
  public Terrain(int height, int width) {

    super(height, width);
  }

  @Override
  /**
   * @param jumpingMovementInfo: import info needed for this terrain to animate
   * @return the info needed by game manager after the animation
   */
  public JumpingResult update(MovementInfo jumpingMovementInfo) {
    return (new JumpingResult());
  }
}
