package com.example.game1.presentation.presenter.common;

import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;

import java.util.ArrayList;
import java.util.Observable;

/**
 * A game manager. It acts as a mediator between the view and the model of a game.
 * It is observed by GameStateObserver.
 */
public abstract class GameManager extends Observable {

    /* The possible states of a GameManager. */
    public enum State {START, PAUSE, STOP, RESUME}

    /* The Game that this GameManager manages */
    public Game game;

    /* The state of this GameManager. */
    private State state;

    /* The list of GameItems in this GameManager. */
    private ArrayList<GameItem> gameItems = new ArrayList<>();

    /* The width of the game display. */
    private int width;

    /* The height of the game display. */
    private int height;

    /* The Activity class of the game this GameManager manages.*/
    private AppCompatActivity activity;

    /* The music player of the game that this GameManager manages. */
    private MediaPlayer musicPlayer;

    /**
     * Constructs a GameManager with the specified height, width, game, and activity.
     */
    public GameManager(int height, int width, Game game, AppCompatActivity activity) {
        this.height = height;
        this.width = width;
        this.game = game;
        this.activity = activity;
    }

    /**
     * Returns gameItems.
     */
    public ArrayList<GameItem> getGameItems() {
        return gameItems;
    }

    /**
     * Adds the specified item to gameItems.
     */
    public void place(GameItem item) {
        gameItems.add(item);
    }

    /**
     * Updates this GameManager by moving all GameItems in it.
     */
    public abstract boolean update();

    /**
     * Removes the specified item from gameItems.
     */
    protected void removeItem(GameItem item) {
        gameItems.remove(item);
    }

    /**
     * Creates some GameItems and adds them to this GameManager.
     */
    public abstract void createGameItems();

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setScreenWidth(int width) {
        this.width = width;
    }

    public int getScreenWidth() {
        return width;
    }

    public void setScreenHeight(int height) {
        this.height = height;
    }

    public int getScreenHeight() {
        return height;
    }

    public int getGridWidth() {
        return width;
    }

    public int getGridHeight() {
        return height;
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

    public void gameOver() {
        stopMusic();
        state = State.STOP;
        setChanged();
        notifyObservers(this);
    }

    public abstract Object getSkyColor();
}
