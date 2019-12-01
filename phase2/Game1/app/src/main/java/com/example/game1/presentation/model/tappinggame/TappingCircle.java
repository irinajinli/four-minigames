package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;
import com.example.game1.presentation.model.jumpinggame.TappingResult;

/** a tapping circle */
public class TappingCircle extends GameItem {
  /** construct a tapping circle at the specified cursor location (x, y). */
  public TappingCircle(double xCoordinate, double yCoordinate) {
    // Call super() to set appearance, location (x, y), appearance and
    // type face.
    super(xCoordinate, yCoordinate);
  }

  @Override
  public TappingResult update(MovementInfo tappingMovementInfo) {
    return (new TappingResult());
  }

}
