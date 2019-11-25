package com.example.game1.presentation.view.brickgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.game1.R;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.AppManager;
import com.example.game1.presentation.presenter.brickgame.BrickGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

import java.util.ArrayList;
import java.util.List;


/** The view of the jumping game presented to the user. */
public class BrickGameView extends GameView implements View.OnClickListener {


    private GameThread thread;


    private OnClickListener listener;
    private int numTaps = 0;
    private List<Bitmap> ballBmps;
    private List<Bitmap> starBmps;
    private Bitmap brickBmp;
    private Bitmap brickDamagedBmp;
    private List<Bitmap> paddleBlueBmps;
    private List<Bitmap> paddleRedBmps;
    private List<Bitmap> paddleYellowBmps;
    private final int SKY_COLOR_DARK = Color.rgb(83, 92, 104);
    private final int SKY_COLOR_LIGHT = Color.rgb(223, 249, 251);
    /**
     * creates a new BrickView *
     *
     * @param context the context in which to create the view
     */
    public BrickGameView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
        this.listener =
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (true) {
                            numTaps++;
                            ((BrickGameManager) gameManager).setNumTaps(numTaps);
                        }
                    }
                };
    }


    /**
     * initialiezes the game view when a surface is created
     *
     * @param holder the surface holder holding the view
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameManager = AppManager.getInstance().buildGameManager(
                Game.GameName.BRICK,
                (int) (getScreenHeight() / charHeight),
                (int) (getScreenWidth() / charWidth),
                activity);
        gameManager.setScreenHeight(this.getScreenHeight());
        gameManager.setScreenWidth(this.getScreenWidth());
        ((BrickGameManager)gameManager).setNumOfSeconds(GameThread.FRAME_DURATION_NS / 1000000000.);

    extractBmpFiles();

        ((BrickGameManager) gameManager)
                .setBmpfiles(
                        ballBmps,
                        starBmps,
                        brickBmp,
                        brickDamagedBmp,
                        paddleBlueBmps,
                        paddleRedBmps,
                        paddleYellowBmps);
        setSkyColorDark(SKY_COLOR_DARK);
        setSkyColorLight(SKY_COLOR_LIGHT);

        generateSkyColor();

        gameManager.createGameItems();
        gameManager.startMusic();

        thread.setRunning(true);
        thread.start();

        this.setOnClickListener(this.listener);



    }



    /** Updates this game view */
    @Override
    public void update() {
        // get amount of time in seconds);

        boolean updated = gameManager.update();
        // stop thread if update fails
        if (!updated) {
            thread.setRunning(false);
        }

    }

    /**
     * draws this game view on the canvas
     *
     * @param canvas the canvas on which to draw
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(((BrickGameManager) gameManager).getBackgroundColor());
            //gameManager.draw(canvas);
            List<GameItem> items = gameManager.getGameItems();
            for (GameItem item : items) {

                drawItem(canvas, item);
            }
        }
    }

    /**
     * Handles the jumper's jump when the user taps the screen
     *
     * @param event the event for the screen tap
     * @return true or false depending on the implementation of the superclass
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ((BrickGameManager) gameManager).onTouchEvent(event.getX());
        return super.onTouchEvent(event);
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                gameManager.gameOver();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void onClick(View v) {

        if (true) {
            numTaps++;
            System.out.println(numTaps);
        }
    }



  public void extractBmpFiles(){

    brickBmp = getNewBitmap(R.drawable.brick_blue, getScreenWidth()/BrickGameManager.NUM_BRICKS_HORIZONTAL, BrickGameManager.BRICK_HEIGHT);
    brickDamagedBmp = getNewBitmap(R.drawable.brick_blue_damaged, getScreenWidth()/BrickGameManager.NUM_BRICKS_HORIZONTAL, BrickGameManager.BRICK_HEIGHT);

    int[] ballFiles = {R.drawable.ball_blue};
    int[]starFiles = {R.drawable.star_6};
    int[] paddleBlueFiles = {R.drawable.paddle_blue};
    int[] paddleRedFiles = {R.drawable.paddle_red};
    int[] paddleYellowFiles = {R.drawable.paddle_yellow};

    ballBmps = new ArrayList<Bitmap>();
    starBmps = new ArrayList<Bitmap>();
    paddleBlueBmps = new ArrayList<Bitmap>();
    paddleRedBmps= new ArrayList<Bitmap>();
    paddleYellowBmps = new ArrayList<Bitmap>();

    generateAnimatedBmps(ballBmps, ballFiles, BrickGameManager.BALL_WIDTH, BrickGameManager.BALL_HEIGHT);
    generateAnimatedBmps(starBmps, starFiles, BrickGameManager.STAR_WIDTH, BrickGameManager.STAR_HEIGHT);
    generateAnimatedBmps(paddleBlueBmps, paddleBlueFiles, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);
    generateAnimatedBmps(paddleRedBmps, paddleRedFiles, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);
    generateAnimatedBmps(paddleYellowBmps, paddleYellowFiles, BrickGameManager.PADDLE_WIDTH, BrickGameManager.PADDLE_HEIGHT);
  }

}
