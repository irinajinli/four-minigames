package com.example.game1.presentation.view.applegame;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;
import com.example.game1.presentation.view.common.MainThread;

/** Based on Fish Tank's FishTankView. */
public class AppleGameView extends GameView {

  /** Construct an AppleGameView with the specified Context. */
  public AppleGameView(Context context) {
    super(context);
    thread = new GameThread(getHolder(), this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Figure out the size of a letter.
    Paint paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    charWidth = paintText.measureText("W");
    charHeight = -paintText.ascent() + paintText.descent();

    // Use the letter size and screen height to determine the size of the GameManager.
    gameManager =
        AppManager.getInstance()
            .getAppleGameManager(
                (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    gameManager.createGameItems();
    gameManager.setActivity(activity);

    thread.setRunning(true);
    thread.start();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    // count tap in game's numTaps
    Game game = gameManager.getGame();
    game.setNumTaps(game.getNumTaps() + 1);

    double touchX = event.getX() / charWidth;
    ((AppleGameManager) gameManager).moveBasket((int) touchX);

    return true;
  }
}
