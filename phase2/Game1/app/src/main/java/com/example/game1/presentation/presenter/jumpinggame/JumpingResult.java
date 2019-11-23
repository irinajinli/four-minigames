package com.example.game1.presentation.presenter.jumpinggame;

import com.example.game1.presentation.model.common.GameItem;
import com.example.game1.presentation.presenter.common.Result;

import java.util.ArrayList;
import java.util.List;

public class JumpingResult extends Result {

    /**
     * A class to store infromation that is needed for game manager to update the list
     */

    /**
     * Items to be added to the list
     */
    List<GameItem> inItems = new ArrayList<>();
    /**
     * Items to be removed from the list
     */
    List<GameItem> outItems = new ArrayList<>();



    /**
     * if result need to be updated
     */
    boolean obstacleJumped = false;

    /**
     * if result need to be updated
     */
    boolean starCollected = false;



    /**
     * if result need to be updated
     */
    boolean needNewStar = false;

    /**
     * if game is over
     */
    boolean gameOver = false;







    public List<GameItem> getInItems() {
        return inItems;
    }

    public void setInItems(List<GameItem> inItems) {
        this.inItems = inItems;
    }

    public void addInItem(GameItem item){
        this.inItems.add(item);
    }

    public List<GameItem> getOutItems() {
        return outItems;
    }

    public void setOutItems(List<GameItem> outItems) {
        this.outItems = outItems;
    }

    public void addOutItem(GameItem item){
        this.outItems.add(item);
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isObstacleJumped() {
        return obstacleJumped;
    }

    public void setObstacleJumped(boolean obstacleJumped) {
        this.obstacleJumped = obstacleJumped;
    }

    public boolean isStarCollected() {
        return starCollected;
    }

    public void setStarCollected(boolean starCollected) {
        this.starCollected = starCollected;
    }

    public boolean isNeedNewStar() {
        return needNewStar;
    }

    public void setNeedNewStar(boolean needNewStar) {
        this.needNewStar = needNewStar;
    }

}