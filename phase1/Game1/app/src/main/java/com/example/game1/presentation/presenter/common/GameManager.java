package com.example.game1.presentation.presenter.common;

import android.graphics.Canvas;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.view.common.GameItem;

import java.util.ArrayList;

/** A fish tank manager. */
public abstract class GameManager {

  /** The Game that this GameManager manages */
  public Game game;
  /** A list of TankItems in this GameManager. */
  private ArrayList<GameItem> gameItems = new ArrayList<>();
  /** The width of this GameManager. */
  private int gridWidth;
  /** The height of this GameManager. */
  private int gridHeight;

  /**
   * Constructs a GameManager with the specified height and width.
   *
   * @param height the height of the GameManager
   * @param width the width of the GameManager
   */
  public GameManager(int height, int width) {
    gridHeight = height;
    gridWidth = width;
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
  public ArrayList<GameItem> getGameItems() {
    return gameItems;
  }

  /**
   * Places the specified item in this GameManager.
   *
   * @param item the item to be placed in the GameManager
   */
  public void place(GameItem item) {
    gameItems.add(item);
  }

  /**
   * Draws all GameItems in gameItems.
   *
   * @param canvas the canvas on which to draw
   */
  public void draw(Canvas canvas) {
    // iterate through GameItems in Fishies and draw them
    for (int i = 0; i < gameItems.size(); i++) {
      gameItems.get(i).draw(canvas);
    }
  }

  /** Updates this GameManager by moving all GameItems in it. */
  public abstract void update();

  /**
   * Removes the specified item from gameItems.
   *
   * @param item the item to be removed
   */
  public void removeItem(GameItem item) {
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
}
