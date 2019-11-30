package com.example.game1.presentation.presenter.applegame;

import com.example.game1.presentation.presenter.common.Result;

public class AppleResult extends Result {


    private boolean appleCollected = false;
    private boolean appleDropped = false;
    private boolean starCollected = false;


    public boolean isAppleCollected() {
        return appleCollected;
    }

    public void setAppleCollected(boolean appleCollected) {
        this.appleCollected = appleCollected;
    }

    public boolean isAppleDropped() {
        return appleDropped;
    }

    public void setAppleDropped(boolean appleDropped) {
        this.appleDropped = appleDropped;
    }

    public boolean isStarCollected() {
        return starCollected;
    }

    public void setStarCollected(boolean starCollected) {
        this.starCollected = starCollected;
    }

}
