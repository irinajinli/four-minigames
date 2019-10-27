package com.example.game1.domain.tappinggame;

import com.example.game1.domain.shared.GameManager;

public class TappingGameManager extends GameManager {
    public TappingGameManager() {
        super(10, 10);
    }

    public TappingGameManager(int height, int width) {
        super(height, width);
    }

    public void createGameItems() {
        Character c1 = new Character();
        Star s1 = new Star();

        c1.setLocation(this, 0, 0);
        s1.setLocation(this, 10, 0);
//        a3.setLocation(this, 20, 0);
    }
}
