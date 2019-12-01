package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.model.common.Result;

public class TappingResult extends Result {


    boolean canRun = true;
    public boolean isCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }
}
