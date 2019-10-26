package com.example.game1.Domain.applegame;

import com.example.game1.Domain.shared.GameManager;

public class AppleGameManager extends GameManager {

  public AppleGameManager() {
    super(10, 10);
  }

  public AppleGameManager(int height, int width) {
    super(height, width);
  }

  public void createGameItems() {
    Apple a1 = new Apple();
    Apple a2 = new Apple();
    Apple a3 = new Apple();
    a1.setLocation(this, 0, 0);
    a2.setLocation(this, 10, 0);
    a3.setLocation(this, 20, 0);
  }
}
