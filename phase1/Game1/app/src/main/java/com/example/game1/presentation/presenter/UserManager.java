package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;

public class UserManager {

    private UserService userService;

    // The current user logged in
    private static User currentUser;


    public UserManager(){
        userService = new UserService();
    }

    /** Register a new user */
    public void registerUser(String userName, String password) {
        System.out.println("user manager register user");
        User user = new User(userName, password);
        userService.registerUser(user);

        setCurrentUser(user);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserManager.currentUser = currentUser;
    }
}
