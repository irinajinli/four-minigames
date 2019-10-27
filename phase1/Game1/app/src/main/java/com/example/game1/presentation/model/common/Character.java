package com.example.game1.presentation.model.common;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.presenter.common.GameView;


public class Character extends GameItem {

    public Character(){
        super("/\\--/\\@@");
        paintText.setColor(Color.YELLOW);
    }

    @Override
    public void move(){
        // moving forward
        int newX = getX() + 1;
        changeLocation(newX, getY());
    }

    @Override
    public void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
    }
}
