package com.example.game1.presentation.presenter;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

/**
 * A singleton class that is created upon opening the app.
 * <p>
 * It acts like a facade for interactions between each game and the system. (e.g. Creates an
 * instance of GameManagerFactory to provide a GameManager for a game. Creates an instance of
 * UserManager to provide information about the current user to a game.)
 */
public class AppManager {

    private static AppManager instance = null;

    private UserManager userManager ;

    /** The factory that creates GameManager objects */
    private GameManagerFactory gameManagerFactory;

    /** The context of the application */
    private Context context = null;

    /** The number of points that 1 star is equivalent to. Used by the data layer when calculating
     * a user's score to record their top game statistics. Displayed by the ui. */
    public static final int STAR_FACTOR = 5;


    private AppManager() {
    }

    /** Called upon opening the app. Sets the context of the application for file writing purposes.
     * Creates an instance of userManager and gameManagerFactory. */
    public void init(Context context) {
        setContext(context);
        userManager = new UserManager();
        gameManagerFactory = new GameManagerFactory();
    }

    /** Returns the single instance of AppManager */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
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
    public JumpingGameManager getJumpingGameManager(int height, int width) {
        JumpingGameManager gameManager = (JumpingGameManager) gameManagerFactory.getGameManager(
                GameManagerFactory.GameName.JUMPING, height, width);
        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());
        return gameManager;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /** Tells the user manager record that the given game is finished. */
    public void finishGame(Game game, AppCompatActivity activity) {
        userManager.updateCurrentUsersGame(game);
        userManager.goToUserMenu(activity);
    }
}
