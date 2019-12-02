package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.GameItem;

/** A counter for to shown lives remaining. */
public class LivesCounter extends GameItem {

  /** Lives remaining. At the begining there are 10 lives remaining. */
  private int livesRemaining;

  /**
   * Constructs a LivesCounter with text description.
   *
   * @param startingLives
   */
  public LivesCounter(int startingLives) {
    super("lives: " + startingLives);
    livesRemaining = startingLives;
  }

  /**
   * Gets the number of livesRemaining.
   *
   * @return number of lives remaining
   */
  public int getLivesRemaining() {
    return livesRemaining;
  }

  /** Decrement liveRemaining by 1 in this LivesCounter. */
  public void subtractLife() {
    livesRemaining -= 1;
    setDescription("lives: " + livesRemaining);
  }
}
