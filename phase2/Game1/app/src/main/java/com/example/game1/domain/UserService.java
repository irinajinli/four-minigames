package com.example.game1.domain;

import com.example.game1.AppManager;
import com.example.game1.data.DataManagerIntf;
import com.example.game1.presentation.model.User;

import java.util.ArrayList;
import java.util.List;

/** The domain service. It implements business logic, such as user validation. */
public class UserService implements UserServiceIntf {

  private DataManagerIntf dataManager;

  /** Constructs a UserService object */
  public UserService() {
    dataManager = AppManager.getInstance().lookupDataManager();
  }

  /**
   * If the given user's username is not already taken, register the given user and return true.
   * Otherwise, return false
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
   * If the given username and password correspond to an existing user, return the existing user.
   * Otherwise, return null
   */
  public User getUser(String username, String password) {
    User user = dataManager.getUser(username);
    if (user != null && user.getPassword().equals(password)) {
      return user;
    } else {
      return null;
    }
  }

  /** Updates the given user's information */
  public void updateUser(User user) {
    dataManager.updateUser(user);
  }

  /**
   * Return the top score of the user with the given username. If the user does not exist, return 0.
   */
  public int getTopScore(String username) {
    User user = dataManager.getUser(username);
    if (user != null) {
      return dataManager.getTopScore(user);
    } else {
      return 0;
    }
  }

  /**
   * Return the current score of the user with the given username. If the user does not exist,
   * return 0.
   */
  public int getCurrentScore(String username) {
    User user = dataManager.getUser(username);
    if (user != null) {
      return dataManager.getCurrentScore(user);
    } else {
      return 0;
    }
  }

  /** Returns the top x users (sorted in non-increasing order) based on the given criterion. */
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
   * Return a new list containing the last x elements in the given list in reverse order. If the
   * given list has less than x elements, simply return a copy of the given list in reverse order.
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
