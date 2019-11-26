package com.example.game1.presentation.presenter.brickgame;
import com.example.game1.presentation.model.brickgame.Ball;
import com.example.game1.presentation.model.brickgame.Brick;
import com.example.game1.presentation.model.brickgame.Paddle;
import com.example.game1.presentation.presenter.common.ImportInfo;

import java.util.List;

public class BrickImportInfo extends ImportInfo {

    double numOfSeconds;
    private Ball ball;
    private List<Brick> bricks;
    private Paddle paddle;

    private int screenHeight;
    private int screenWidth;





    public BrickImportInfo(int screenHeight, int screenWidth, Ball ball, List<Brick> bricks, Paddle paddle,
                             double numOfSeconds){
        //this.numOfSeconds = numOfSeconds;
        //this.item = item;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.ball = ball;
        this.bricks = bricks;
        this.paddle = paddle;
        this.numOfSeconds = numOfSeconds;
    }

    public double getNumOfSeconds() {
        return numOfSeconds;
    }

    public void setNumOfSeconds(double numOfSeconds) {
        this.numOfSeconds = numOfSeconds;
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
