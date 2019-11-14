package com.example.game1.domain;

import com.example.game1.presentation.model.User;
import com.example.game1.data.DataManager;

/**
 * The domain service. It implements business logic, such as user validation.
 */
public class UserService {

    private DataManager dataManager;

    /**
     * Constructs a UserService object
     */
    public UserService() {
        dataManager = new DataManager();
    }

    /**
     * Verifies if the user's username is not already taken. If it is not taken, register the user
     * and return true. Otherwise, return false
     */
    public boolean registerUser(User user) {
        System.out.println("user service register user");
        if (dataManager.getUser(user.getUserName()) != null) {
            return false;
        } else {
            dataManager.createUser(user);
            return true;
        }
    }

    /**
     * Verifies if userName and password correspond to an existing user. If they do, return the
     * the existing user. Otherwise, return null
     */
    public User getUser(String userName, String password) {
        User user = dataManager.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Updates user's information
     */
    public void updateUser(User user) {
        dataManager.updateUser(user);
    }

    /**
     * Returns the user with the top score
     */
    public User getTopUser() {
        return dataManager.getTopUser();
    }
}
