package com.example.game1.presentation.presenter.brickgame;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.presenter.common.MovementInfo;

import java.util.List;

public class BrickMovementInfo extends MovementInfo {

    double numSeconds;
    private Ball ball;
    private List<Brick> bricks;
    private Paddle paddle;

    private int screenHeight;
    private int screenWidth;





    public BrickMovementInfo(int screenHeight, int screenWidth, Ball ball, List<Brick> bricks, Paddle paddle,
                             double numSeconds){
        //this.numSeconds = numSeconds;
        //this.item = item;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.ball = ball;
        this.bricks = bricks;
        this.paddle = paddle;
        this.numSeconds = numSeconds;
    }

    public double getNumSeconds() {
        return numSeconds;
    }

    public void setNumSeconds(double numSeconds) {
        this.numSeconds = numSeconds;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

/**
    public Jumper getJumper() {
        return jumper;
    }

    public void setJumper(Jumper jumper) {
        this.jumper = jumper;
    }

    public Terrain getTerrain() {
        return terrian;
    }

    public void setTerrain(Terrain terrian) {
        this.terrian = terrian;
    }
*/

}
