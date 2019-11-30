package com.example.game1.presentation.model.tappinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.model.common.MovementInfo;
import com.example.game1.presentation.model.common.Result;

/** a tapping circle */
public class TappingCircle extends GameItem {
  /** construct a tapping circle at the specified cursor location (x, y). */
  public TappingCircle(int x, int y) {
    // Call super() to set appearance, location (x, y), appearance and
    // type face.
    super(x, y);
  }

  @Override
  public Result update(MovementInfo jumpingMovementInfo) {
    return (new Result());
  }
  // public Result animate(MovementInfo importInfo){return new Result();}
}
