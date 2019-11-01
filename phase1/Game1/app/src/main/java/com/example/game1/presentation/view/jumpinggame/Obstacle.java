package com.example.game1.presentation.view.jumpinggame;


import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;

public class Obstacle extends GameObject {

    public Obstacle(int width, int height, JumpingGameManager jgm) {
        super(width, height, jgm);
    }

    public void update(){
        super.update();
        if (this.isOverlapping(jgm.jumper)){
            //TODO coupling fix
            jgm.jgv.gameOver();
        }
        else if (this.getPositionX() + this.getWidth() < 0){
            this.setPositionX(jgm.getScreenWidth() * 4 / 3);
            jgm.numJumped += 1;
            if (Math.random() > 0.7){
                jgm.addStar(jgm.getScreenWidth() * 4 / 3 + this.getWidth() / 2 - 80/2); // 80 is the diameter of the coin
            }
        }
    }
}
