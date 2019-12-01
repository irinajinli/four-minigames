package com.example.game1.presentation.model.tappinggame;


import com.example.game1.presentation.model.common.MovementInfo;


public class TappingMovementInfo extends MovementInfo {

    int tappingSpeed;



    int secondsLeft;

    int numTaps;
    public TappingMovementInfo(
            int screenHeight, int screenWidth, int tappingSpeed, int secondsLeft, int numTaps, double numSeconds) {

        super(numSeconds, screenHeight, screenWidth);

        this.tappingSpeed = tappingSpeed;
        this.secondsLeft = secondsLeft;
        this.numTaps = numTaps;
    }

    public int getTappingSpeed() {

        return tappingSpeed;
    }

    public int getNumTaps() {
        return numTaps;
    }

    public void setNumTaps(int numTaps) {
        this.numTaps = numTaps;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }


}
