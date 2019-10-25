package com.example.game1.Domain.AppleGame;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * A fish tank manager.
 */
class AppleGameManager {

    /**
     * A list of TankItems in this AppleGameManager.
     */
    // todo: change to private
    ArrayList<GameItem> fishies = new ArrayList<>();

    /**
     * The width of this fish tank.
     */
    private int gridWidth;

    /**
     * The height of this fish tank.
     */
    private int gridHeight;


    int getGridWidth() {
        return gridWidth;
    }

    int getGridHeight() {
        return gridHeight;
    }

    /**
     * Constructs a AppleGameManager with the specified height and width.
     *
     * @param height the height of the AppleGameManager
     * @param width  the width of the AppleGameManager
     */
    AppleGameManager(int height, int width) {
        gridHeight = height;
        gridWidth = width;
    }

    /**
     * Returns fishies.
     *
     * @return fishies
     */
    ArrayList<GameItem> getFishies() {
        return fishies;
    }

    /**
     * Places the specified item in this AppleGameManager.
     *
     * @param item the item to be placed in the AppleGameManager
     */
    void place(GameItem item) {
        fishies.add(item);
    }

    /**
     * Draws all TankItems in fishies.
     *
     * @param canvas the canvas on which to draw
     */
    void draw(Canvas canvas) {
        // iterate through TankItems in Fishies and draw them
        for (int i = 0; i < fishies.size(); i++) {
            fishies.get(i).draw(canvas);
        }
    }

    /**
     * Updates by moving all TankItems in this AppleGameManager.
     */
    void update() {
        for (int i = 0; i < fishies.size(); i++)
            fishies.get(i).move();
    }

    /**
     * Removes the specified item from fishies.
     *
     * @param item the item to be removed
     */
    void removeItem(GameItem item) {
        fishies.remove(item);
    }

    /**
     * Creates some TankItems and adds them to this AppleGameManager.
     */
    void createTankItems() {
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Apple a3 = new Apple();
        a1.setLocation(this, 0, 0);
        a2.setLocation(this, 10, 0);
        a3.setLocation(this, 20, 0);
    }
}
