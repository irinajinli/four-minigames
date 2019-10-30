package com.example.game1.domain;

import com.example.game1.presentation.model.User;
import com.example.game1.data.DataManager;


public class UserService {

    private DataManager dataManager;

    public UserService() {
        dataManager = new DataManager();
    }

    /** If user's username is not already taken, register user and return true. Otherwise, return
     * false */
    public boolean registerUser(User user){
        System.out.println("user service register user");
        if (dataManager.getUser(user.getUserName()) != null) {
            return false;
        } else {
            dataManager.createUser(user);
            return true;
        }
    }

    /** If the userName and password are correct, return the User with username, userName Otherwise,
     *  return null */
    public User getUser(String userName, String password) {
        User user =  dataManager.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    /** Update user's information */
    public void updateUser(User user) {dataManager.updateUser(user);}

}
