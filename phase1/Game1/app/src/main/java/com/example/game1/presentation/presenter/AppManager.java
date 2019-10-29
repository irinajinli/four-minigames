package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

/**
 * The manager for the app.
 * <p>
 * It acts like a facade for interactions between each game and the system. (e.g. creating an
 * instance of GameManager for a game)
 */
public class AppManager {

    private static AppManager instance = null;

    private UserManager userManager;

    /** The factory that creates GameManager objects */
    private GameManagerFactory gameManagerFactory = new GameManagerFactory();

    /** The context of the application */
    Context context = null;


    private AppManager() {
        userManager = new UserManager();
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public AppleGameManager getAppleGameManager(int height, int width) {
        AppleGameManager gameManager = (AppleGameManager) gameManagerFactory.getGameManager(
                GameManagerFactory.GameName.APPLE, height, width);
        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());
        return gameManager;
    }

    public TappingGameManager getTappingGameManager(int height, int width) {
        TappingGameManager gameManager = (TappingGameManager) gameManagerFactory.getGameManager(
                GameManagerFactory.GameName.TAPPING, height, width);
        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());
        return gameManager;
    }

    // TODO change return type to JumpingGameManager once that class is created
    public AppleGameManager getJumpingGameManager(int height, int width) {
//        JumpingGameManager gameManager = (JumpingGameManager) gameManagerFactory.getGameManager(
//                GameManagerFactory.GameName.JUMPING, height, width);
//        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());
//        return gameManager;
        AppleGameManager gameManager = (AppleGameManager) gameManagerFactory.getGameManager(
                GameManagerFactory.GameName.APPLE, height, width);
        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());
        return gameManager;
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

    /**
     * Record that the given Game is finished.
     */
    public void finishGame(Game game) {

    }
}
