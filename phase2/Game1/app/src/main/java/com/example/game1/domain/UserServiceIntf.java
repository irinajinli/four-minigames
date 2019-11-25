package com.example.game1.domain;

import com.example.game1.presentation.model.User;

import java.util.List;

/**
 * This is the interface that defines the responsibilities/functions of the user service.
 */
public interface UserServiceIntf {
    /**
     * If the given user's username is not already taken, register the given user and return true.
     * Otherwise, return false
     */
    boolean registerUser(User user);

    /**
     * If the given username and password correspond to an existing user, return the existing user.
     * Otherwise, return null
     */
    User getUser(String username, String password);

    /**
     * Updates the given user's information
     */
    void updateUser(User user);

    /**
     * Return the top score of the user with the given username. If the user does not exist,
     * return 0.
     */
    int getTopScore(String username);

    /**
     * Return the current score of the user with the given username. If the user does not exist,
     * return 0.
     */
    int getCurrentScore(String username);

    /**
     * Returns the top x users (sorted in non-increasing order) based on the given criterion.
     */
    List<User> getTopUsers(int x, String criterion);
}
