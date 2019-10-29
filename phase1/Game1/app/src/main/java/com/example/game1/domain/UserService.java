package com.example.game1.domain;

import com.example.game1.presentation.model.User;
import com.example.game1.data.DataManager;


public class UserService {

    DataManager dataManager;

    public UserService() {
        dataManager = new DataManager();
    }

    public void registerUser(User user){
        System.out.println("user service register user");
        if (dataManager.getUser(user.getUserName()) != null) {
            // TODO: send notification that username already exists
        } else {
            dataManager.createUser(user);
        }
    }

}
