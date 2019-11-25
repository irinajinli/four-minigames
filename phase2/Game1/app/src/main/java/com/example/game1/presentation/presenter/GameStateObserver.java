package com.example.game1.presentation.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;

import java.util.Observable;
import java.util.Observer;

public class GameStateObserver implements Observer {

    private UserManager userManager;

    /**
     * Event notified
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameManager) {
            GameManager gameManager = (GameManager)o;
            if (GameManager.State.STOP.equals(gameManager.getState())) {
                finishGame(gameManager.getGame(), gameManager.getActivity());
            }
        }
    }

    /**
     * Injects the user manager
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Notifies the system that a game is finished. Currently, it routes the control to userManager
     */
    private void finishGame(Game game, AppCompatActivity activity) {
        if (userManager!=null) {
            userManager.updateCurrentUsersGame(game);
            userManager.goToUserMenu(activity);
        }
    }
}
