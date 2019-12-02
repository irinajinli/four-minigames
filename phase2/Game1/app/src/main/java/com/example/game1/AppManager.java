package com.example.game1;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.data.DataManagerIntf;
import com.example.game1.data.FileDataManager;
import com.example.game1.domain.UserService;
import com.example.game1.domain.UserServiceIntf;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.GameManagerFactory;
import com.example.game1.presentation.presenter.GameStateObserver;
import com.example.game1.presentation.presenter.UserManager;
import com.example.game1.presentation.presenter.common.GameManager;

/**
 * A singleton class that is created upon opening the app. This represents the application resource
 * manager that manages resource creation and lookup. The resources can be injected by the
 * application configuration (not available in this current project) or instantiated in this class.
 */
public class AppManager {

  private static AppManager instance = null;

  private UserManager userManager;

  private GameManagerFactory gameManagerFactory;

  private GameStateObserver gameStateObserver;

  /* The context of the application */
  private Context context = null;

  /** Constructs an AppManager */
  private AppManager() {}

  /** Returns the single instance of AppManager */
  public static AppManager getInstance() {
    if (instance == null) {
      instance = new AppManager();
    }
    return instance;
  }

  /**
   * Called upon opening the app. Sets the context of the application. Creates an instance of
   * UserManager, GameStateObserver, and GameManagerFactory.
   */
  public void init(Context context) {
    this.context = context;
    userManager = buildUserManager();
    gameStateObserver = buildGameStateObserver();
    gameManagerFactory = new GameManagerFactory();
  }

  /** Builds a UserManager. */
  private UserManager buildUserManager() {
    UserManager userManager = new UserManager();
    // inject the user service into userManager
    userManager.setUserService(lookupUserService());
    return userManager;
  }

  /** Builds a GameStateObserver. */
  private GameStateObserver buildGameStateObserver() {
    GameStateObserver gameStateObserver = new GameStateObserver();
    // inject the user manager into gameStateObserver
    gameStateObserver.setUserManager(getUserManager());
    return gameStateObserver;
  }

  /** Builds a GameManager with the specified game, height, width, and activity. */
  public GameManager buildGameManager(
      Game.GameName game, int height, int width, AppCompatActivity activity) {
    GameManager gameManager = gameManagerFactory.getGameManager(game, height, width, activity);
    gameManager.getGame().setCustomization(userManager.getCurrentUser().getCustomization());
    gameManager.addObserver(gameStateObserver);
    switch (userManager.getCurrentUser().getCustomization().getMusicPath()) {
      case SONG1:
        gameManager.setMusicPlayer(MediaPlayer.create(context, R.raw.chibi_ninja));
        break;
      case SONG2:
        gameManager.setMusicPlayer(MediaPlayer.create(context, R.raw.arpanauts));
        break;
      case SONG3:
        gameManager.setMusicPlayer(MediaPlayer.create(context, R.raw.a_night_of_dizzy_spells));
        break;
    }
    return gameManager;
  }

  /** Returns the application context */
  public Context getContext() {
    return context;
  }

  /** Sets the application context */
  public void setContext(Context context) {
    this.context = context;
  }

  /** Returns the user manager */
  public UserManager getUserManager() {
    return userManager;
  }

  /** Returns an instance of the user service */
  public UserServiceIntf lookupUserService() {
    // Return a service that implements UserServiceIntf
    return new UserService();
  }

  /** Returns an instance of the data manager */
  public DataManagerIntf lookupDataManager() {
    // Currently we only provide a file based data manager
    return new FileDataManager();
  }
}
