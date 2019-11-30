package com.example.game1.presentation.model.common;

import com.example.game1.presentation.model.common.GameItem;

public class MovementInfo {
    private double numSeconds;

    public MovementInfo(double numSeconds){
        this.numSeconds = numSeconds;
    }

    public double getNumSeconds() {
        return numSeconds;
    }

    public void setNumSeconds(double numSeconds) {
        this.numSeconds = numSeconds;
    }


}
