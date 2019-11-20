package com.example.game1.presentation.presenter;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

import static com.example.game1.presentation.model.Customization.MusicPath.SONG1;

/**
 * A singleton class that is created upon opening the app.
 * It acts like a facade for interactions between each game and the rest of the system.
 */
public class AppManager {

    private static AppManager instance = null;

    private UserManager userManager;

    /**
     * The factory that creates GameManager objects
     */
    private GameManagerFactory gameManagerFactory;

    /**
     * The context of the application
     */
    private Context context = null;

    /**
     * The number of points that 1 star is equivalent to. It is used to calculate user's score.
     */
    public static final int STAR_FACTOR = 5;

    /**
     * Constructs an AppManager
     */
    private AppManager() {
    }

    /**
     * Called upon opening the app.
     * Sets the context of the application.
     * Creates an instance of userManager and gameManagerFactory.
     */
    public void init(Context context) {
        setContext(context);
        userManager = new UserManager();
        gameManagerFactory = new GameManagerFactory();
    }

    /**
     * Returns the single instance of AppManager
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * Returns the instance of UserManager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Returns a GameManager for the game, game
     */
    private GameManager getGameManager(Game.GameName game, int height, int width) {
        GameManager gameManager = gameManagerFactory.getGameManager(game, height, width);
        gameManager.getGame().setCustomization(UserManager.getCurrentUser().getCustomization());

        /*
         Music: Eric Skiff - Song Name - Resistor Anthems - Available at http://EricSkiff.com/music
         */
        switch (UserManager.getCurrentUser().getCustomization().getMusicPath()) {
            case SONG1:
                gameManager.setMusicPlayer(MediaPlayer.create(getContext(), R.raw.chibi_ninja));
                break;
            case SONG2:
                gameManager.setMusicPlayer(MediaPlayer.create(getContext(), R.raw.arpanauts));
                break;
            case SONG3:
                gameManager.setMusicPlayer(MediaPlayer.create(getContext(),
                        R.raw.a_night_of_dizzy_spells));
                break;
        }

        return gameManager;
    }

    /**
     * Returns an AppleGameManager. It calls the generic method, getGameManager.
     */
    public AppleGameManager getAppleGameManager(int height, int width) {
        return (AppleGameManager) getGameManager(Game.GameName.APPLE, height, width);
    }

    /**
     * Returns an TappingGameManger. It calls the generic method, getGameManager.
     */
    public TappingGameManager getTappingGameManager(int height, int width) {
        return (TappingGameManager) getGameManager(Game.GameName.TAPPING, height, width);
    }

    /**
     * Returns an JumpingGameManager. It calls the generic method, getGameManager.
     */
    public JumpingGameManager getJumpingGameManager(int height, int width) {
        return (JumpingGameManager) getGameManager(Game.GameName.JUMPING, height, width);
    }

    /**
     * Returns the application context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the application context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Notifies the system that a game is finished. Currently, it routes the control to userManager
     */
    public void finishGame(Game game, AppCompatActivity activity) {
        userManager.updateCurrentUsersGame(game);
        userManager.goToUserMenu(activity);
    }
}
