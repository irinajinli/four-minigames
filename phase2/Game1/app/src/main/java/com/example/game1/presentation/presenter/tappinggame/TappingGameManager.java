package com.example.game1.presentation.presenter.tappinggame;



import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.common.Background;
import com.example.game1.presentation.model.tappinggame.Runner;
import com.example.game1.presentation.model.tappinggame.SpeedDisplayer;
import com.example.game1.presentation.model.tappinggame.StarDisplayer;
import com.example.game1.presentation.model.tappinggame.TapCounter;
import com.example.game1.presentation.model.tappinggame.TappingCircle;
import com.example.game1.presentation.model.tappinggame.TimerDisplayer;

import java.util.ArrayList;
import java.util.List;

public class TappingGameManager extends GameManager {

  private Object tappingCircleAppearance;
  private List runnerAppearances;
  private List yellowPugAppearances;
  private List blueBirdAppearances;
  private List redFishAppearances;
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

  public static final double RUNNER_WIDTH_MULTIPLIER = 0.1;
  public static final double RUNNER_HEIGHT_MULTIPLIER = 0.1;

  private Object skyColor;
  private Object skyColorDark;
  private Object skyColorLight;


  /**
   * Constructs a TappingGameManager with the specified height, width, game, and activity.
   */
  public TappingGameManager(int height, int width, Game game, AppCompatActivity activity) {
    super(height, width, game, activity);
    this.canRun = true;
  }

  public void setAppearance(Object tappingCircleAppearance, List yellowPugAppearances,
                            List blueBirdAppearances, List redFishAppearances) {
    this.tappingCircleAppearance = tappingCircleAppearance;
    this.yellowPugAppearances = yellowPugAppearances;
    this.blueBirdAppearances = blueBirdAppearances;
    this.redFishAppearances = redFishAppearances;
    this.runnerAppearances = yellowPugAppearances;
  }

  /**
   * execute animation on each item in myFishTank and update myFishTank accordingly.
   *
   */
  public boolean update() {
    //newItems list stores FishTankItems to be added to myFishTank
    List<GameItem> newItems = new ArrayList<>();
    // outItem list stores FishTankItem to be removed from myFishTank
    List<GameItem> outItems = new ArrayList<>();

    List<GameItem> Items = getGameItems();
    for (GameItem item : Items) {
      if(item.getClass() == Runner.class){
        canRun = ((Runner)item).move(getScreenWidth());
      }

    }

    //Iterate all items in newItems List, add them to myFishTank.
    for (GameItem newItem : newItems) {
      place(newItem);
    }

    // TODO: temporary return true; decide when you want to return true/false
    return true;
  }

  public void createGameItems() {

    Customization cust = game.getCustomization();
    if (cust.getColourScheme().equals(Customization.ColourScheme.DARK)) {
//      Background b = new Background();
//      place(b);
//      b.setPosition(0, 0);
      setSkyColor(skyColorDark);
    } else if (cust.getColourScheme().equals(Customization.ColourScheme.LIGHT)) {
      setSkyColor(skyColorLight);
    }
    if(cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)){
      this.runnerAppearances = blueBirdAppearances;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)){
      this.runnerAppearances = redFishAppearances;
    } else{ // if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)){
      this.runnerAppearances = yellowPugAppearances;
    }


    this.tappingCircle = new TappingCircle(tappingCircleAppearance, 0, 0);
    place(tappingCircle);
    this.runner = new Runner((int)(getScreenWidth() * RUNNER_WIDTH_MULTIPLIER), (int)(getScreenHeight()* RUNNER_HEIGHT_MULTIPLIER), runnerAppearances);
    runner.setPosition(0, 1550);
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

  public void setSkyColors(Object skyColorDark, Object skyColorLight){
    this.skyColorDark = skyColorDark;
    this.skyColorLight = skyColorLight;
  }

  public Object getSkyColor() {
    return skyColor;
  }

  public void setSkyColor(Object skyColor) {
    this.skyColor = skyColor;
  }
}
