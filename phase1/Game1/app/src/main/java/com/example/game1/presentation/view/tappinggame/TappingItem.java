package com.example.game1.presentation.view.tappinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class TappingItem {

    /**
     * How this fish tank item appears on the screen.
     */
    Object appearance;

    /**
     * This fish tank item's x coordinate.
     */
    int xCoordinate;

    /**
     * This fish tank item's y coordinate.
     */
    int yCoordinate;

    public Paint paintText = new Paint();


    /**
     * Constructs a new fish tank item with specified appearance
     * at the specified cursor location (xCoordinate, yCoordinate).
     */
    TappingItem(Object appearance, int xCoordinate, int yCoordinate) {
        this.appearance = appearance;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    TappingItem(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    /**
     * Draws the given string in the given graphics context at
     * the current location.
     *
     * @param canvas the graphics context in which to draw this item.
     */
    public void draw(Canvas canvas) {
        if(appearance.getClass() == String.class) {
            canvas.drawText((String) appearance, xCoordinate * TappingGameView.charWidth, yCoordinate * TappingGameView.charHeight, paintText);
        } else if (appearance.getClass() == Bitmap.class){
            canvas.drawBitmap((Bitmap) appearance, xCoordinate* TappingGameView.charWidth, yCoordinate* TappingGameView.charHeight, paintText);
        }
    }

    public TappingItem[] animate(int tankWidth, int tankHeight) {
        //Use a FishTankItem[] of length 2 to store value to be returned.
        //result[0] store the FishTankItem to be removed from myFishTank
        //result[1] store the FishTankItem to be added to myFishTank
        TappingItem[] result = new TappingItem[2];
        return result;
    }
}
