package com.example.game1.domain;

import com.example.game1.presentation.model.User;
import com.example.game1.data.DataManager;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Returns the top x users (sorted in non-increasing order) based on the given criterion.
     */
    public List<User> getTopUsers(int x, String criterion) {
        List<User> users;

        if ("Points".equals(criterion)) {
            users = dataManager.sortUsersByPoints();
        } else if ("Stars".equals(criterion)) {
            users = dataManager.sortUsersByStars();
        } else if ("Taps".equals(criterion)) {
            users = dataManager.sortUsersByTaps();
        } else {
            // "Total Score".equals(criterion)
            users = dataManager.sortUsersByScore();
        }
        return getLastXElementsReversed(users, x);
    }

    /**
     * Return a new list containing the last x elements in the given list in reverse order.
     * If the given list has less than x elements, return a copy of the given list in reverse order.
     */
    private List<User> getLastXElementsReversed(List<User> list, int x) {
        List<User> newList = new ArrayList<>();
        int sizeOfNewList = Math.min(x, list.size());

        for (int i = 0; i < sizeOfNewList; i++) {
            newList.add(list.get(list.size() - 1 - i));
        }
        return newList;
    }

}
