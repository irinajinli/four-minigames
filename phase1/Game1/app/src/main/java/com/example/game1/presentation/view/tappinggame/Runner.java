package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;

public class Runner extends ImageGameItem {
    public Runner(Bitmap imageBMP, int width, int height, int xCoordinate, int yCoordinate){
        super(imageBMP, width, height, xCoordinate, yCoordinate);
    }

    public void run(int speed){
        this.xCoordinate += speed;
    }
}
