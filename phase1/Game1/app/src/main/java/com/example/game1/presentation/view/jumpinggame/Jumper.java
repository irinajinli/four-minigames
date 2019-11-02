package com.example.game1.presentation.view.jumpinggame;


import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

public class Jumper extends GameObject {

    public Customization.CharacterColour characterColour;

    public Jumper(int width, int height, JumpingGameManager jgm) {
        super(width, height, jgm);
    }

    public void update(){
        super.update();
        if (this.isOverlapping(jgm.getTerrain())){
            this.setPositionY(jgm.getTerrain().getPositionY() - this.getHeight());
            this.setVelocityY(0);
            this.setAccelerationY(0);
        }
    }
}
