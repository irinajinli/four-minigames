package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.applegame.AppleResult;
import com.example.game1.presentation.presenter.common.MovementInfo;

public class LivesCounter extends GameItem {
  /** A counter for points earned. */

  // variables
  /** How many lives are remaining. */
  private int livesRemaining = 10;

  /** Constructs a LivesCounter with white text. */
  public LivesCounter(int startingLives) {
    super("lives: " + startingLives);
  }

  /**
   * Gets livesRemaining.
   *
   * @return number of lives remaining
   */
  public int getLivesRemaining() {
    return livesRemaining;
  }

  /** Removes a life from this LivesCounter. */
  public void subtractLife() {
    livesRemaining -= 1;
    setDescription("lives: " + livesRemaining);
  }

  public AppleResult update(MovementInfo jumpingMovementInfo) {
    return (new AppleResult());
  }

//  public Result animate(MovementInfo movementInfo) {
//    return new Result();
//  }
}
