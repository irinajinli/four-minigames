package com.example.game1.presentation.model.common;

import com.example.game1.presentation.model.common.GameItem;

public class MovementInfo {
    private double numSeconds;
    private int screenHeight, screenWidth;


    public MovementInfo(double numSeconds, int screenHeight, int screenWidth){
        this.numSeconds = numSeconds;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight(){
        return screenHeight;
    }

    public int getScreenWidth(){
        return screenWidth;
    }

    public double getNumSeconds() {
        return numSeconds;
    }

    public void setNumSeconds(double numSeconds) {
        this.numSeconds = numSeconds;
    }


}
