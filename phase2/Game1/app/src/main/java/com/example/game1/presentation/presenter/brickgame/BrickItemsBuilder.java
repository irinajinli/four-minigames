package com.example.game1.presentation.presenter.brickgame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;

import java.util.ArrayList;
import java.util.List;

class BrickItemsBuilder {
  private Customization cust;
  private List paddleBmps;
  private Paddle paddle;
  private ArrayList<Brick> bricks;
  private Ball ball;
  private ArrayList<Star> stars = new ArrayList<Star>();

  BrickItemsBuilder(Customization cust) {
    this.cust = cust;
  }

  void setTheme(BrickGameManager brickGameManager) {
    brickGameManager.setTheme(cust.getColourScheme());
  }

  void createPaddle(
      int paddleHeight,
      int paddleWidth,
      List paddleBlueBmps,
      List paddleRedBmps,
      List paddleYellowBmps) {
    if (cust.getCharacterColour().equals(Customization.CharacterColour.BLUE)) {
      paddleBmps = paddleBlueBmps;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.RED)) {
      paddleBmps = paddleRedBmps;
    } else if (cust.getCharacterColour().equals(Customization.CharacterColour.YELLOW)) {
      paddleBmps = paddleYellowBmps;
    } else { // default blue
      paddleBmps = paddleBlueBmps;
    }

    paddle = new Paddle(paddleHeight, paddleWidth);
  }

  void createBricks(
      int screenWidth,
      int numBricksHorizontal,
      int numBrickLayers,
      int brickHeight,
      Bitmap brickBmp) {
    bricks = new ArrayList<Brick>();
    int brickWidth = screenWidth / numBricksHorizontal;
    for (int i = 0; i < numBrickLayers; i++) {
      for (int j = 0; j < numBricksHorizontal; j++) {
        Brick newBrick = new Brick(brickHeight, brickWidth);
        newBrick.setPosition(j * brickWidth, i * brickHeight);
        bricks.add(newBrick);
      }
    }
  }

  void createBall(
      int ballWidth,
      int ballHeight,
      List ballBmps,
      int screenWidth,
      int brickHeight,
      int numBrickLayers,
      double ballXVelocity,
      double ballYVelocity) {
    ball = new Ball(ballWidth, ballHeight);
    ball.setPosition(screenWidth / 2, brickHeight * (numBrickLayers + 1));
    ball.setXVelocity(ballXVelocity);
    ball.setYVelocity(ballYVelocity);
  }

  void createStar() {
    stars = new ArrayList<Star>();
  }

  void placeItems(BrickGameManager brickGameManager) {
    // paddle
    brickGameManager.setPaddle(paddle);
    brickGameManager.setPaddlePosition(paddle);
    brickGameManager.place(paddle);

    // bricks
    brickGameManager.setBricks(bricks);
    for (Brick brick : bricks) {
      brickGameManager.place(brick);
    }

    // ball
    brickGameManager.setBall(ball);
    brickGameManager.place(ball);

    // stars
    brickGameManager.setStars(stars);
  }
}
