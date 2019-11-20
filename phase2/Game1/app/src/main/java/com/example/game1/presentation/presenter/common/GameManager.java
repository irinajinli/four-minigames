package com.example.game1.presentation.presenter.common;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.view.common.GameItemOld;

import java.util.ArrayList;

/** A game manager. */
public abstract class GameManager {

  /** The Game that this GameManager manages */
  public Game game;
  /** A list of TankItems in this GameManager. */
  private ArrayList<GameItemOld> gameItems = new ArrayList<>();
  /** The width of this GameManager. */
  private int gridWidth;
  /** The height of this GameManager. */
  private int gridHeight;
  /** The Activity class of the game this GameManager manages. */
  private AppCompatActivity activity;
  /** The music player of the game that this GameManager manages. */
  private MediaPlayer musicPlayer;

  /**
   * Constructs a GameManager with the specified height and width.
   *
   * @param height the height of the GameManager
   * @param width the width of the GameManager
   */
  public GameManager(int height, int width, Game game ) {
    gridHeight = height;
    gridWidth = width;
    this.game = game;
  }

  public int getGridWidth() {
    return gridWidth;
  }

  public int getGridHeight() {
    return gridHeight;
  }

  /**
   * Returns gameItems.
   *
   * @return gameItems
   */
  public ArrayList<GameItemOld> getGameItems() {
    return gameItems;
  }

  /**
   * Places the specified item in this GameManager.
   *
   * @param item the item to be placed in the GameManager
   */
  public void place(GameItemOld item) {
    gameItems.add(item);
  }

  /**
   * Draws all GameItems in gameItems.
   *
   * @param canvas the canvas on which to draw
   */
  public void draw(Canvas canvas) {
    // iterate through gameItems and draw them
    for (int i = 0; i < gameItems.size(); i++) {
      gameItems.get(i).draw(canvas);
    }
  }

  /** Updates this GameManager by moving all GameItems in it. */
  public abstract boolean update();

  /**
   * Removes the specified item from gameItems.
   *
   * @param item the item to be removed
   */
  public void removeItem(GameItemOld item) {
    gameItems.remove(item);
  }

  /** Creates some GameItems and adds them to this GameManager. */
  public abstract void createGameItems();

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public AppCompatActivity getActivity() {
    return activity;
  }

  public void setActivity(AppCompatActivity activity) {
    this.activity = activity;
  }

  public void setMusicPlayer(MediaPlayer musicPlayer) {
    this.musicPlayer = musicPlayer;
  }

  public void startMusic() {
    musicPlayer.start();
  }

  public void stopMusic() {
    musicPlayer.stop();
    musicPlayer.release();
  }

  public void gameOver(){
    stopMusic();
    AppManager.getInstance().finishGame(game, activity);
  }
}
