package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;

public class UserManager {

    private UserService userService;

    public static Context context;

    public UserManager(){
        userService = new UserService();
    }

    public void registerUser(Context context, String userName, String password) {
        this.context = context;
        System.out.println("user manager register user");
        User user = new User(userName, password);
        userService.registerUser(user);
    }
}
