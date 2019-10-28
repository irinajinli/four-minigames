package com.example.game1.data;

import com.example.game1.presentation.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserEntity extends User {

    List<FeatureEntity> features;
    List<GameEntity> games;


    public UserEntity(String userName, String password) {
        super(userName, password);
        this.features = new ArrayList<>();;
        this.games = new ArrayList<>();;
    }

    public UserEntity(String userName, String password, List<FeatureEntity> features,
                      List<GameEntity> games) {
        super(userName, password);
        this.features = features;
        this.games = games;
    }

}
