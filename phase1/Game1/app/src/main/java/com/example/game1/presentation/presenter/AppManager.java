package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

/**
 * The manager for the app.
 *
 * It acts like a facade for interactions between each game and the system. (e.g. creating an
 * instance of GameManager for a game)
 */
public class AppManager {

    static AppManager instance = null;

    private UserManager userManager;

    /**
     * The factory that creates GameManager objects
     */
    private GameManagerFactory gameManagerFactory = new GameManagerFactory();

    Context context = null;


    private AppManager() {
        userManager = new UserManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public AppleGameManager getAppleGameManager(int height, int width) {
        return (AppleGameManager) gameManagerFactory.getGameManager(GameManagerFactory.Game.APPLE, height, width);
    }

    public TappingGameManager getTappingGameManager(int height, int width) {
        return (TappingGameManager) gameManagerFactory.getGameManager(GameManagerFactory.Game.TAPPING, height, width);
    }

    // TODO change return type to JumpingGameManager once that class is created
    public AppleGameManager getJumpingGameManager(int height, int width) {
        return (AppleGameManager) gameManagerFactory.getGameManager(GameManagerFactory.Game.APPLE, height, width);
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
