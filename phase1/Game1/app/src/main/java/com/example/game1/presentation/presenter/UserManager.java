package com.example.game1.presentation.presenter;

import android.content.Context;

import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;

public class UserManager {

    private UserService userService;

    public static Context context;

    // The current user logged in
    private static User currentUser;


    public UserManager(){
        userService = new UserService();
    }

    /**
     * Register a new user
     * @param context the context of the application
     * @param userName the user's username
     * @param password the user's password
     */
    public void registerUser(Context context, String userName, String password) {
        this.context = context;
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
