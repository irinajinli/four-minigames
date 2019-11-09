package com.example.game1.presentation.presenter.tappinggame;

import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;

import com.example.game1.R;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.tappinggame.Runner;
import com.example.game1.presentation.view.tappinggame.SpeedDisplayer;
import com.example.game1.presentation.view.tappinggame.StarDisplayer;
import com.example.game1.presentation.view.tappinggame.TapCounter;
import com.example.game1.presentation.view.tappinggame.TappingCircle;
import com.example.game1.presentation.view.tappinggame.TappingItem;
import com.example.game1.presentation.view.tappinggame.TimerDisplayer;

import java.util.ArrayList;
import java.util.List;

public class TappingGameManager extends GameManager {

  //
  //  protected TappingCircle tappingCircle;
  //  protected Runner runner;
  /**
   * A list with all Tapping items.
   */
  static List<TappingItem> myTappingItems;


  /**
   * The width of the fish tank.
   */
  private int width;
  /**
   * The height of fish tank.
   */
  private int height;
  /**
   * The height of fish tank.
   */
  private Bitmap tappingCircleBMP;
  /**
   * The height of fish tank.
   */

  public TappingCircle tappingCircle;

  private Bitmap runnerBMP;

  public TapCounter tapCounter;

  public TimerDisplayer timerDisplayer;

  public StarDisplayer starDisplayer;

  public SpeedDisplayer speedDisplayer;


  /**
   * The tapping game manager on a screen with height rows and width columns.
   */
  public TappingGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.TAPPING);
    this.height = height;
    this.width = width;


    myTappingItems = new ArrayList<TappingItem>();

  }

  /**
   * The tapping game manager on a screen with height rows and width columns.
   */
  public TappingGameManager(int height, int width, Bitmap tappingCircleBMP, Bitmap runnerBMP) {
    super(height, width);
    this.game = new Game(Game.GameName.TAPPING);
    this.height = height;
    this.width = width;
    this.tappingCircleBMP = tappingCircleBMP;
    this.runnerBMP = runnerBMP;

    myTappingItems = new ArrayList<>();

  }

  /**
   * Return the width of this fish tank.
   *
   * @return the width of this fish tank.
   */
  int getWidth() {
    return width;
  }

  /**
   * Return the height of this fish tank.
   *
   * @return the height of this fish tank.
   */
  int getHeight() {
    return height;
  }


//@Override
//  /**
//   * draw each item in myFishTank on the canvas.
//   *
//   * @param canvas the graphics context in which to draw this item.
//   */
//  public void draw(Canvas canvas) {
//    for (TappingItem item : myTappingItems) {
//      item.draw(canvas);
//    }
//  }





  /**
   * execute animation on each item in myFishTank and update myFishTank accordingly.
   *
   */
  public void update() {
    //newItems list stores FishTankItems to be added to myFishTank
    List<TappingItem> newItems = new ArrayList<>();
    // outItem list stores FishTankItem to be removed from myFishTank
    List<TappingItem> outItems = new ArrayList<>();

    for (TappingItem item : myTappingItems) {

      //Use a FishTankItem[] of length 2 to store value to be returned.
      TappingItem[] result = item.animate(getWidth(), getHeight());

      //result[0] stores the FishTankItem to be removed from myFishTank
      if (result[0] != null) {
        // Add the FishTankItem to the outItems so that it can be removed from myFishTank later
        outItems.add( result[0]);
      }
      //result[1] stores the FishTankItem to be added to myFishTank
      if (result[1] != null) {
        // Add the FishTankItem to the newItems List so that it can be added to myFishTank later
        newItems.add(result[1]);
      }
    }
    // Iterate all items in outItems List, remove it from myFishTank.
    for (TappingItem outItem : outItems) {
      myTappingItems.remove(outItem);
    }
    //Iterate all items in newItems List, add them to myFishTank.
    for (TappingItem newItem : newItems) {
      myTappingItems.add(newItem);
    }

  }

  public void createGameItems() {
//    gameItems.add(new TappingCircle(tappingCircleBMP, 0, 0));

  }

  public void createTappingItems() {
    TappingCircle tappingCircle = new TappingCircle(tappingCircleBMP, 0, 0);
    myTappingItems.add(tappingCircle);
    myTappingItems.add(new Runner(runnerBMP, 0, 35));
    this.tapCounter = new TapCounter(10, 30);
    myTappingItems.add(this.tapCounter);
    this.timerDisplayer = new TimerDisplayer(10, 31);
    myTappingItems.add(timerDisplayer);
    this.speedDisplayer = new SpeedDisplayer(10, 32);
    myTappingItems.add(speedDisplayer);
    this.starDisplayer = new StarDisplayer(10, 33);
    myTappingItems.add(starDisplayer);



  }


  /**
   * draw each item in myFishTank on the canvas.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  public void draw(Canvas canvas) {
    for (TappingItem item : myTappingItems) {

      item.draw(canvas);
    }
  }

  public void gameOver(int numTaps, int stars) {
    // TODO
    game.setNumPoints(numTaps);
    game.setNumStars(stars);
    game.setNumTaps(numTaps);
    super.gameOver();
  }
}
