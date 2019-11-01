package com.example.game1.presentation.view.tappinggame;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;

import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.applegame.AppleGameManager;
import com.example.game1.presentation.view.applegame.MainThread;
import com.example.game1.presentation.view.common.GameView;

public class TappingGameView extends GameView {

  public TappingGameView(Context context) {
    super(context);

    //thread = new MainThread(getHolder(), this);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

    // Figure out the size of a letter.
//    Paint paintText = new Paint();
//    paintText.setTextSize(36);
//    paintText.setTypeface(Typeface.DEFAULT_BOLD);
//    charWidth = paintText.measureText("W");
//    charHeight = -paintText.ascent() + paintText.descent();

    // Use the letter size and screen height to determine the size of the GameManager.
    gameManager =
        AppManager.getInstance()
            .getTappingGameManager(
                (int) (getScreenHeight() / charHeight), (int) (getScreenWidth() / charWidth));
    gameManager.createGameItems();

    gameManager.setActivity(activity);

//    thread.setRunning(true);
//    thread.start();
  }
}
