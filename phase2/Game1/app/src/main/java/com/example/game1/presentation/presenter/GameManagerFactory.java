package com.example.game1.presentation.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

/**
 * A factory for GameManager objects
 */
public class GameManagerFactory {
    /**
     * Returns a GameManager with the specified game, height, width, and activity.
     *
     * @param game     the game this method creates and returns a game manager for
     * @param height   the height of the game manager
     * @param width    the width of the game manager
     * @param activity the activity associated with the game manager
     */
    public GameManager getGameManager(Game.GameName game, int height, int width, AppCompatActivity activity) {
        switch (game) {
            case APPLE:
                return new AppleGameManager(height, width, new Game(Game.GameName.APPLE), activity);
            case TAPPING:
                return new TappingGameManager(height, width, new Game(Game.GameName.TAPPING), activity);
            case JUMPING:
                return new JumpingGameManager(height, width, new Game(Game.GameName.JUMPING), activity);
            case BRICK:
                return new BrickGameManager(height, width, new Game(Game.GameName.BRICK), activity);
            default:
                return null;
        }
    }
}
