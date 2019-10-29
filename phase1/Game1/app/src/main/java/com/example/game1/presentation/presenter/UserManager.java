package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;

public class UserManager {

    private UserService userService;

    // The current user logged in
    private static User currentUser;


    UserManager(){
        userService = new UserService();
    }

    /** Try to register a new User with username, userName, and password, password. If successful,
     * return true. Otherwise, return false. */
    public boolean registerUser(String userName, String password) {
        System.out.println("user manager register user");
        User user = new User(userName, password);
        if (userService.registerUser(user)) {
            setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }

    /** Try to login a User with username, userName, and password, password. If successfull, return
     * true. Otherwise, return false. */
    public boolean loginUser(String userName, String password) {
        User user = userService.getUser(userName, password);
        if (user != null) {
            setCurrentUser(user);
        }
        return user != null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserManager.currentUser = currentUser;
    }
}
