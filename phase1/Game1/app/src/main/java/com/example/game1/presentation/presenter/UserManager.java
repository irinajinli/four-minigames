package com.example.game1.presentation.presenter;

import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;

public class UserManager {

    UserService userService;

    public UserManager(){
        userService = new UserService();
    }

    public void registerUser(String userName, String password) {
        User user = new User(userName, password);
        userService.registerUser(user);
    }

}
