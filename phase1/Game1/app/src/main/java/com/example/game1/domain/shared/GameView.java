package com.example.game1.domain.shared;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game1.domain.applegame.MainThread;

/**
 * The fish tank view.
 */
public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // TODO: make variables private where possible
    /**
     * The width of a character.
     */
    public static float charWidth;
    /**
     * The height of a character.
     */
    public static float charHeight;
    /**
     * The fish tank contents.
     */
    public GameManager gameManager;

    /**
     * Screen width.
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    /**
     * Screen height.
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    /**
     * The part of the program that manages time.
     */
    public MainThread thread;

    /**
     * Create a new fish tank in the context environment.
     *
     * @param context the environment.
     */
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public static float getCharWidth() {
        return charWidth;
    }

    public static float getCharHeight() {
        return charHeight;
    }

    @Override
    public abstract void surfaceCreated(SurfaceHolder holder);

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Update the fish tank.
     */
    public void update() {
        gameManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            gameManager.draw(canvas);
        }
    }
}