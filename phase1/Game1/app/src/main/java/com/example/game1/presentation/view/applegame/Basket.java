package com.example.game1.presentation.view.applegame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game1.presentation.view.common.GameItem;
import com.example.game1.presentation.view.common.GameView;

public class Basket extends GameItem {

    public Basket() {
        super("|_|");
    paintText.setColor(Color.LTGRAY);
    }

    @Override
    public void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * GameView.charWidth, y * GameView.charHeight, super.paintText);
    }

    @Override
    public void move() {
        // do nothing!
        // this move method shouldn't be used
        // figure out a better way to do this
    }

    /**
     * Move this Basket to the specified x coordinate.
     * @param x the x coordinate to move this Basket to
     */
    public void move(int x) {
        changeLocation(x, getY());
    }
}
