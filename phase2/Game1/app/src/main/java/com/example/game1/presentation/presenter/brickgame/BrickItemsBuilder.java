package com.example.game1.presentation.presenter.brickgame;

import android.graphics.Bitmap;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.model.brickgame.Star;

import java.util.ArrayList;
import java.util.List;

public class BrickItemsBuilder {
  private Customization cust;
  private List paddleBmps;
  private Paddle paddle;
  private ArrayList<Brick> bricks;
  private Ball ball;
  private ArrayList<Star> stars = new ArrayList<Star>();

  BrickItemsBuilder(Customization cust) {
    this.cust = cust;
  }

  public void setTheme(BrickGameManager brickGameManager) {
    brickGameManager.setTheme(cust.getColourScheme());
  }

  void createPaddle(
      int PADDLE_HEIGHT,
      int PADDLE_WIDTH,
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

    paddle = new Paddle(PADDLE_HEIGHT, PADDLE_WIDTH, paddleBmps);
  }

  void createBricks(
      int screenWidth,
      int NUM_BRICKS_HORIZONTAL,
      int NUM_BRICK_LAYERS,
      int BRICK_HEIGHT,
      Bitmap brickBmp) {
    bricks = new ArrayList<Brick>();
    int brickWidth = screenWidth / NUM_BRICKS_HORIZONTAL;
    for (int i = 0; i < NUM_BRICK_LAYERS; i++) {
      for (int j = 0; j < NUM_BRICKS_HORIZONTAL; j++) {
        Brick newBrick = new Brick(BRICK_HEIGHT, brickWidth, brickBmp);
        newBrick.setPosition(j * brickWidth, i * BRICK_HEIGHT);
        bricks.add(newBrick);
      }
    }
  }

  void createBall(
      int BALL_WIDTH,
      int BALL_HEIGHT,
      List ballBmps,
      int screenWidth,
      int BRICK_HEIGHT,
      int NUM_BRICK_LAYERS,
      double BALL_VELOCITY_X,
      double BALL_VELOCITY_Y) {
    ball = new Ball(BALL_WIDTH, BALL_HEIGHT, ballBmps);
    ball.setPosition(screenWidth / 2, BRICK_HEIGHT * (NUM_BRICK_LAYERS + 1));
    ball.setXVelocity(BALL_VELOCITY_X);
    ball.setYVelocity(BALL_VELOCITY_Y);
  }

  void createStar() {
    stars = new ArrayList<Star>();
  }

  public void placeItems(BrickGameManager brickGameManager) {
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
