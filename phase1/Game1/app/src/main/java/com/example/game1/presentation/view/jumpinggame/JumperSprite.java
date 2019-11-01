package com.example.game1.presentation.view.jumpinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.game1.presentation.view.common.GameThread;
import com.example.game1.presentation.view.common.GameView;

public class JumperSprite extends GameSprite {

   /** public JumperSprite(Bitmap image, int width, int height, GameView gameView) {
        super(image, width, height, gameView);
    }

    @Override
    public void update(){
        JumpingGameView jgv = (JumpingGameView)gameView;
        super.update();
        if (this.isOverlapping(jgv.terrainSprite)){
            this.setPositionY(jgv.terrainSprite.getPositionY() - this.getHeight());
            this.setVelocityY(0);
            this.setAccelerationY(0);
        }
    }*/
}
