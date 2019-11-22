package com.example.game1.presentation.model.jumpinggame;

import com.example.game1.presentation.presenter.jumpinggame.JumpingGameManager;
import com.example.game1.presentation.view.jumpinggame.GameObject;

public class Terrain extends GameObject {
    public Terrain (int width, int height, JumpingGameManager jgm){
        super(width, height, jgm);
    }
}
