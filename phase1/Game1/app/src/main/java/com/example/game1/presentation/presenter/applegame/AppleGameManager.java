package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.presenter.common.GameManager;

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
    place(a1);
    a1.setLocation(0, 0);
    place(a2);
    a2.setLocation(10, 0);
    place(a3);
    a3.setLocation(20, 0);
  }

  public void update() {
    for (int i = 0; i < getGameItems().size(); i++) getGameItems().get(i).move();
  }
}
