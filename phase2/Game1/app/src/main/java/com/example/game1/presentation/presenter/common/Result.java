package com.example.game1.presentation.presenter.common;

import com.example.game1.presentation.model.common.GameItem;

import java.util.ArrayList;
import java.util.List;

public class Result {
    /** A class to store infromation that is needed for game manager to update the list */

    /** Items to be added to the list */
    List<GameItem> newItems = new ArrayList<>();
    /** Items to be removed from the list */
    List<GameItem> oldItems = new ArrayList<>();

    public List<GameItem> getNewItems() {
        return newItems;
    }

    public void setNewItems(List<GameItem> newItems) {
        this.newItems = newItems;
    }

    public void addNewItem(GameItem item) {
        this.newItems.add(item);
    }

    public List<GameItem> getOldItems() {
        return oldItems;
    }

    public void setOldItems(List<GameItem> oldItems) {
        this.oldItems = oldItems;
    }

    public void addOldItem(GameItem item) {
        this.oldItems.add(item);
    }



}
