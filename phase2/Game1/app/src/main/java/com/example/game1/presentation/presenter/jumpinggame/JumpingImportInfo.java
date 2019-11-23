package com.example.game1.presentation.presenter.jumpinggame;
import com.example.game1.presentation.model.jumpinggame.Jumper;
import com.example.game1.presentation.model.jumpinggame.Terrain;
import com.example.game1.presentation.presenter.common.ImportInfo;

public class JumpingImportInfo extends ImportInfo {

    double numOfSeconds;
    Jumper jumper;


    Terrain terrian;
    int screenHeight;
    int screenWidth;





    public JumpingImportInfo(int screenHeight, int screenWidth, Jumper jumper, Terrain terrian,
                             double numOfSeconds){
        //this.numOfSeconds = numOfSeconds;
        //this.item = item;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.jumper = jumper;
        this.terrian = terrian;
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


    public Jumper getJumper() {
        return jumper;
    }

    public void setJumper(Jumper jumper) {
        this.jumper = jumper;
    }

    public Terrain getTerrian() {
        return terrian;
    }

    public void setTerrian(Terrain terrian) {
        this.terrian = terrian;
    }


}