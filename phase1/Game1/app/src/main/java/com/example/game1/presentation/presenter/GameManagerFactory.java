package com.example.game1.presentation.presenter;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

/** Creates GameManager objects */
public class GameManagerFactory {

  /**
   * Return a GameManager for the Game, game
   *
   * @param game the game this method creates and returns a game manager for
   * @param height the height of the game manager
   * @param width the width of the game manager
   */
  GameManager getGameManager(Game.GameName game, int height, int width) {
    switch (game) {
      case APPLE:
        return new AppleGameManager(height, width);
      case TAPPING:
        return new TappingGameManager(height, width);
      case JUMPING:
        return new JumpingGameManager(height, width);
      default:
        return null;
    }
  }
}
