package com.example.game1.presentation.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;

import java.util.Observable;
import java.util.Observer;

/** An observer class that responds to game state changes. */
public class GameStateObserver implements Observer {

  private UserManager userManager;

  /**
   * This method is called when the state of a game has changed. It notifies the rest of the system
   * of this state change.
   */
  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof GameManager) {
      GameManager gameManager = (GameManager) o;
      // Currently, this method only takes action if a game has stopped.
      if (GameManager.State.STOP.equals(gameManager.getState())) {
        finishGame(gameManager.getGame(), gameManager.getActivity());
      }
    }
  }

  /** Injects the user manager into this GameStateObserver. */
  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  /** Notifies the system that a game has finished. */
  private void finishGame(Game game, AppCompatActivity activity) {
    // Currently, this method only notifies userManager.
    if (userManager != null) {
      userManager.updateCurrentUsersGame(game);
      userManager.goToStatsPage(activity);
    }
  }
}
