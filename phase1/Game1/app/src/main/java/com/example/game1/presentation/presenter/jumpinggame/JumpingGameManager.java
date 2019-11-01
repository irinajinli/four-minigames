package com.example.game1.presentation.presenter.jumpinggame;

import android.graphics.Color;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.view.applegame.Apple;
import com.example.game1.presentation.view.applegame.Background;
import com.example.game1.presentation.view.applegame.Basket;
import com.example.game1.presentation.view.applegame.MainThread;
import com.example.game1.presentation.view.applegame.PointsCounter;
import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.Star;
import com.example.game1.presentation.view.jumpinggame.JumpingGameView;

import java.util.Random;

public class JumpingGameManager extends GameManager {
  /**
   * A GameManager for an Apple minigame. Includes an extra variable numDroppedApples and extra
   * methods for handling Apples.
   */

  JumpingGameView jgv;

  /** Constructs an AppleGameManager with a height and width of 10. */
  public JumpingGameManager() {
    super(10, 10);
  }

  /**
   * Constructs an AppleGameManager with the specified height and width.
   *
   * @param height the height of the AppleGameManager
   * @param width the width of the AppleGameManager
   */
  public JumpingGameManager(int height, int width) {
    super(height, width);
    this.game = new Game(Game.GameName.APPLE);
  }

  public void setJumpingGameView (JumpingGameView jgv){
    this.jgv = jgv;
  }
  /** Creates GameItems required at the beginning of the minigame. */
  public void createGameItems() {
    // create background according to Customization
    Customization cust = game.getCustomization();
    jgv.setTheme(cust.getColourScheme());

   jgv.setCharacterColor(cust.getCharacterColour());


  }


  /** Moves, removes, and catches GameItems. */
  public void update() {
  }



  /** Ends this minigame. */
  public void gameOver() {
    // set points here
    game.setNumPoints(jgv.numJumped);
    game.setNumStars(jgv.numCoins);
    game.setNumPoints(jgv.numTaps);
    System.out.println(jgv.numJumped + "  " + jgv.numCoins + "  " + jgv.numTaps);
    super.gameOver();
  }
}
