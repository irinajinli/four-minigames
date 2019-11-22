package com.example.game1.presentation.presenter.tappinggame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.view.common.GameItemOld;
import com.example.game1.presentation.model.tappinggame.Runner;
import com.example.game1.presentation.model.tappinggame.SpeedDisplayer;
import com.example.game1.presentation.model.tappinggame.StarDisplayer;
import com.example.game1.presentation.model.tappinggame.TapCounter;
import com.example.game1.presentation.model.tappinggame.TappingCircle;
import com.example.game1.presentation.model.tappinggame.TimerDisplayer;

import java.util.ArrayList;
import java.util.List;

public class TappingGameManager extends GameManager {

  //
  //  protected TappingCircle tappingCircle;
  //  protected Runner runner;
  /**
   * A list with all Tapping items.
   */
 // static List<GameItemOld> myTappingItems;


  /**
   * The width of the fish tank.
   */
//  private int width;
  /**
   * The height of fish tank.
   */
//  private int height;
  /**
   * The height of fish tank.
   */
  private Bitmap tappingCircleBMP;
  private Bitmap runnerBMP;
  private Bitmap yellowPug;
  private Bitmap blueBird;
  private Bitmap redFish;
  /**
   * The height of fish tank.
   */

  public TappingCircle tappingCircle;







  public Runner runner;

  public TapCounter tapCounter;

  public TimerDisplayer timerDisplayer;

  public StarDisplayer starDisplayer;

  public SpeedDisplayer speedDisplayer;

  public boolean isCanRun() {
    return canRun;
  }

  public void setCanRun(boolean canRun) {
    this.canRun = canRun;
  }

  private boolean canRun;



  /**
   * The tapping game manager on a screen with height rows and width columns.
   */
  public TappingGameManager(int height, int width, Game game) {
    super(height, width, game);
//    this.height = height;
//    this.width = width;
    this.canRun = true;

    //myTappingItems = new ArrayList<>();

  }

//  /**
//   * The tapping game manager on a screen with height rows and width columns.
//   */
//  public TappingGameManager(int height, int width, Bitmap tappingCircleBMP, Bitmap runnerBMP) {
//    super(height, width);
//    this.game = new Game(Game.GameName.TAPPING);
////    this.height = height;
////    this.width = width;
////    this.tappingCircleBMP = tappingCircleBMP;
////    this.runnerBMP = runnerBMP;
//    this.canRun = true;
//    //myTappingItems = new ArrayList<>();
//
//  }

  public void setPictures(Bitmap tappingCircleBMP, Bitmap yellowPug, Bitmap blueBird, Bitmap redFish) {
    this.tappingCircleBMP = tappingCircleBMP;
    this.yellowPug = yellowPug;
    this.blueBird = blueBird;
    this.redFish = redFish;
    this.runnerBMP = yellowPug;
  }

  /**
   * Return the width of this fish tank.
   *
   * @return the width of this fish tank.
   */
//  int getWidth() {
//    return width;
//  }

  /**
   * Return the height of this fish tank.
   *
   * @return the height of this fish tank.
   */
//  int getHeight() {
//    return height;
//  }








  /**
   * execute animation on each item in myFishTank and update myFishTank accordingly.
   *
   */
  public boolean update() {
    //newItems list stores FishTankItems to be added to myFishTank
    List<GameItemOld> newItems = new ArrayList<>();
    // outItem list stores FishTankItem to be removed from myFishTank
    List<GameItemOld> outItems = new ArrayList<>();

    List<GameItemOld> Items = getGameItems();
    for (GameItemOld item : Items) {
        if(item.getClass() == Runner.class){
          canRun = ((Runner)item).move(getGridWidth());
        }
//      //Use a FishTankItem[] of length 2 to store value to be returned.
//      GameItemOld[] result = item.animate(getWidth(), getHeight());
//
//      //result[0] stores the FishTankItem to be removed from myFishTank
//      if (result[0] != null) {
//        // Add the FishTankItem to the outItems so that it can be removed from myFishTank later
//        outItems.add( result[0]);
//      }
//      //result[1] stores the FishTankItem to be added to myFishTank
//      if (result[1] != null) {
//        // Add the FishTankItem to the newItems List so that it can be added to myFishTank later
//        newItems.add(result[1]);
//      }
    }
//    // Iterate all items in outItems List, remove it from myFishTank.
//    for (GameItemOld outItem : outItems) {
//      items.remove(outItem);
//    }
    //Iterate all items in newItems List, add them to myFishTank.
    for (GameItemOld newItem : newItems) {
      place(newItem);
    }

    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  public void createGameItems() {

    Customization cust = game.getCustomization();
    if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      Background b = new Background();
      place(b);
      b.setLocation(0, 0);
    }
    if(cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)){
      this.runnerBMP = blueBird;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)){
      this.runnerBMP = redFish;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)){
      this.runnerBMP = yellowPug;
    }
    this.tappingCircle = new TappingCircle(tappingCircleBMP, 0, 0);
    place(tappingCircle);
    this.runner = new Runner(runnerBMP, 0, 35);
    place(runner);
    this.tapCounter = new TapCounter(10, 30);
    place(tapCounter);
    this.timerDisplayer = new TimerDisplayer(10, 31);
    place(timerDisplayer);
    this.speedDisplayer = new SpeedDisplayer(10, 32);
    place(speedDisplayer);
    this.starDisplayer = new StarDisplayer(10, 33);
    place(starDisplayer);
  }

  public void gameOver(int numTaps, int stars) {
    // TODO
    game.setNumPoints(numTaps);
    game.setNumStars(stars);
    game.setNumTaps(numTaps);
    super.gameOver();
  }
}
