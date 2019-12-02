package com.example.game1.presentation.model.applegame;

import com.example.game1.presentation.model.common.Result;

/** Result after apple game item perform update */
public class AppleResult extends Result {

  /** Indicate if apple is collected */
  private boolean appleCollected = false;
  /** Indicate if apple is dropped */
  private boolean appleDropped = false;
  /** Indicate if star is collected */
  private boolean starCollected = false;

  /**
   * Set if apple is collected
   *
   * @param appleCollected
   */
  public void setAppleCollected(boolean appleCollected) {
    this.appleCollected = appleCollected;
  }

  /**
   * Return if apple is collected
   *
   * @return if apple is collected
   */
  public boolean isAppleCollected() {
    return appleCollected;
  }

  /**
   * Set if apple is dropped
   *
   * @param appleDropped
   */
  public void setAppleDropped(boolean appleDropped) {
    this.appleDropped = appleDropped;
  }

  /**
   * Return if apple is dropped
   *
   * @return
   */
  public boolean isAppleDropped() {
    return appleDropped;
  }

  /**
   * Set if star is collected
   *
   * @param starCollected
   */
  public void setStarCollected(boolean starCollected) {
    this.starCollected = starCollected;
  }
  /**
   * Return if star is collected
   *
   * @return
   */
  public boolean isStarCollected() {
    return starCollected;
  }
}
