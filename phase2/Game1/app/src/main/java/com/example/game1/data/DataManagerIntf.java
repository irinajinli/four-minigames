package com.example.game1.data;

import com.example.game1.presentation.model.User;

import java.util.List;

/**
 * This is the interface that defines the responsibilities/functions of the data manager.
 * The data manager is responsible for storing, retrieving, and loading persistence data.
 * The implementation of this data manager interface can be database based, file based
 * or cloud based.
 */
public interface DataManagerIntf {
    /**
     * Adds the given user.
     */
    void createUser(User user);

    /**
     * Updates the given user's information.
     */
    void updateUser(User user);

    /**
     * Returns the user with the given username. If no such user exists, return null.
     */
    User getUser(String userName);

    /**
     * Returns the given's user's top score.
     */
    int getTopScore(User user);

    /**
     * Returns the given's user's top score.
     */
    int getCurrentScore(User user);

    /**
     * Returns a list of users sorted (in non-decreasing order) by score
     */
    List sortUsersByScore();

    /**
     * Returns a list of users sorted (in non-decreasing order) by number of points
     */
    List sortUsersByPoints();

    /**
     * Returns a list of users sorted (in non-decreasing order) by number of stars
     */
    List sortUsersByStars();

    /**
     * Returns a list of users sorted (in non-decreasing order) by number of taps
     */
    List sortUsersByTaps();
}