package com.example.game1.data;

import com.example.game1.presentation.model.User;

import java.util.Comparator;

public class TapsComparator implements Comparator<User> {
  /**
   * Compares its two arguments for order.
   *
   * <p>Returns a negative integer, zero, or a positive integer as user1 is less than, equal to, or
   * greater than user2 in terms of the top number of taps in their history.
   */
  @Override
  public int compare(User user1, User user2) {
    return user1.getTopIndividualStats().getTaps() - user2.getTopIndividualStats().getTaps();
  }
}
