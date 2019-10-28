package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.presenter.common.GameManager;

import java.util.Random;

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
    a1.setLocation(0, 15);
    place(a2);
    a2.setLocation(10, 0);
    place(a3);
    a3.setLocation(20, 8);
  }

  public void update() {
    for (int i = 0; i < getGameItems().size(); i++) getGameItems().get(i).move();
    spawnNew();
  }

  private void spawnNew() {
    // get a random x-coordinate to spawn the new Apple/Star at
    double r = Math.random();
    Random rand = new Random();
    int randint = rand.nextInt(getGridWidth());

    // decide whether to spawn an Apple or a Star or nothing
    // TODO: add if (r < ...) ... spawn Star
    if (r < 0.1) {
      // spawn new Apple
      Apple nextApple = new Apple();
      place(nextApple);
      nextApple.setLocation(randint, 0);
    }

    // else do nothing
  }
}
