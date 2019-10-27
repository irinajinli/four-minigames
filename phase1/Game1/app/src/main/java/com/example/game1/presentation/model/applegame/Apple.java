package com.example.game1.presentation.model.applegame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.GameView;

public class Apple extends GameItem {

    public Apple() {
        super("( )");
        paintText.setColor(Color.RED);
    }

    @Override
    public void move() {
        int newY = getY() + 1;
        //        if (newY > )
        // TODO: later, decide what to do when apple goes off screen/gets to bottom
        changeLocation(getX(), getY() + 1);
    }

    @Override
    public void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
    }
}
