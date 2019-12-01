package com.example.game1.presentation.model.common;

import com.example.game1.presentation.model.common.GameItem;

public class MovementInfo {
    private double numSeconds;
    int screenWidth;

    int screenHeight;

    public MovementInfo(double numSeconds, int screenWidth, int screenHeight){
        this.numSeconds = numSeconds;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public double getNumSeconds() {
        return numSeconds;
    }

    public void setNumSeconds(double numSeconds) {
        this.numSeconds = numSeconds;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}
