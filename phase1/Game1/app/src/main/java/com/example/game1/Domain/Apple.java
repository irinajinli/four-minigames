package com.example.game1.Domain;

import android.graphics.Canvas;
import android.graphics.Color;

public class Apple extends GameItem {

    Apple() {
        super("( )");
        paintText.setColor(Color.RED);
    }

    @Override
    void move() {
        int newY = getY() + 1;
//        if (newY > )
        // TODO: later, decide what to do when apple goes off screen/gets to bottom
        changeLocation(getX(), getY() + 1);
    }

    @Override
    void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * AppleGameView.charWidth, y * AppleGameView.charHeight, super.paintText);
    }
}
