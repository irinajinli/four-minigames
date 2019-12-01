package com.example.game1.presentation.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Information generated after a Game Item performed update. This information will be sent to game
 * manager for further process
 */
public class Result {
  /** A class to store infromation that is needed for game manager to update the list */

  /** Items to be added to the list */
  List<GameItem> newItems = new ArrayList<>();
  /** Items to be removed from the list */
  List<GameItem> oldItems = new ArrayList<>();

  /**
   * Set the list of game items that are to be added
   *
   * @param newItems the list of game items that are to be added
   */
  public void setNewItems(List<GameItem> newItems) {
    this.newItems = newItems;
  }

  /**
   * Return the List of game items that are to be added
   *
   * @return the List of game items that are to be added
   */
  public List<GameItem> getNewItems() {
    return newItems;
  }

  /**
   * Add a new game item to the newItems list so that it will be added later
   *
   * @param item add a game item to the newItems list so that it will be added later
   */
  public void addNewItem(GameItem item) {
    this.newItems.add(item);
  }

  /**
   * Set the list of game items that are to be removed
   *
   * @param oldItems the list of game items that are to be removed
   */
  public void setOldItems(List<GameItem> oldItems) {
    this.oldItems = oldItems;
  }

  /**
   * Return the List of game items that are to be removed
   *
   * @return the List of game items that are to be removed
   */
  public List<GameItem> getOldItems() {
    return oldItems;
  }

  /**
   * Add a new game item to the oldItems list so that it will be removed later
   *
   * @param item add a new game item to the oldItems list so that it will be removed later
   */
  public void addOldItem(GameItem item) {
    this.oldItems.add(item);
  }
}
