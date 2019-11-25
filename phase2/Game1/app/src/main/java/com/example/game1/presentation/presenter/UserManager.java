package com.example.game1.presentation.presenter;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.domain.UserServiceIntf;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.User;
import com.example.game1.presentation.view.user.UserMenuActivity;

import java.util.List;

/**
 * A controller class for managing user activity.
 * It is also a proxy to classes in the domain layer.
 */
public class UserManager {

    /**
     * An instance of UserService from the domain layer.
     */
    private UserServiceIntf userService;

    /**
     * The current user that is logged in.
     */
    private User currentUser;

    /**
     * Constructs a UserManager
     */
    public UserManager() {
    }

    /**
     * Inject user service
     */
    public void setUserService(UserServiceIntf userService) {
        this.userService = userService;
    }

    /**
     * Registers a new User instance with username, userName, and password, password. If
     * registration is successful return true. Otherwise, return false.
     */
    public boolean registerUser(String userName, String password) {
        User user = new User(userName, password);

        // Send the user to userService to check for duplicate user name
        if (userService.registerUser(user)) {
            setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Logs in a User with username, userName, and password, password. If login is successful,
     * return true. Otherwise, return false.
     */
    public boolean loginUser(String userName, String password) {

        // Send the user to userService for user validation
        User user = userService.getUser(userName, password);
        if (user != null) {
            setCurrentUser(user);
        }
        return user != null;
    }

    /**
     * Returns the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Updates the customization choices of the current user
     */
    public void updateCurrentUsersCustomization(String characterColour, String colourScheme,
                                                String music) {
        Customization newCustomization = new Customization();

        if ("Red".equals(characterColour)) {
            newCustomization.setCharacterColour(Customization.CharacterColour.RED);
        } else if ("Yellow".equals(characterColour)) {
            newCustomization.setCharacterColour(Customization.CharacterColour.YELLOW);
        } else {
            newCustomization.setCharacterColour(Customization.CharacterColour.BLUE);
        }

        if ("Light".equals(colourScheme)) {
            newCustomization.setColourScheme(Customization.ColourScheme.LIGHT);
        } else {
            newCustomization.setColourScheme(Customization.ColourScheme.DARK);
        }

        if ("Arpanauts".equals(music)) {
            newCustomization.setMusicPath(Customization.MusicPath.SONG2);
        } else if ("A Night Of Dizzy Spells".equals(music)) {
            newCustomization.setMusicPath(Customization.MusicPath.SONG3);
        } else {
            newCustomization.setMusicPath(Customization.MusicPath.SONG1);
        }

        currentUser.setCustomization(newCustomization);
        updateUserInfo();
    }

    /**
     * Updates the current user's game statistics and level using the information in the given Game
     * object.
     */
    public void updateCurrentUsersGame(Game game) {
        updateGameInfo(currentUser.getStatsOfCurrentGame().getPoints() + game.getStatistics().getPoints(),
                currentUser.getStatsOfCurrentGame().getStars() + game.getStatistics().getStars(),
                currentUser.getStatsOfCurrentGame().getTaps() + game.getStatistics().getTaps(),
                game.getLevel());
        updateUserInfo();
    }

    /**
     * Restarts the current user's game
     */
    public void restartCurrentUsersGame() {
        // Set all the current statistics and the current game level to 0
        updateGameInfo(0, 0, 0, 0);
        updateUserInfo();
    }

    /**
     * Sets the current user's game statistics and level to the given values.
     */
    private void updateGameInfo(int points, int stars, int taps, int level) {
        currentUser.getStatsOfCurrentGame().setPoints(points);
        currentUser.getStatsOfCurrentGame().setStars(stars);
        currentUser.getStatsOfCurrentGame().setTaps(taps);
        currentUser.setLastCompletedLevel(level);
    }

    /**
     * Sends any changes to the current user's info to the backend through UserService
     */
    private void updateUserInfo() {
        userService.updateUser(currentUser);
    }

    /**
     * Redirects the user to the user menu screen
     */
    public void goToUserMenu(AppCompatActivity activity) {
        Intent intent = new Intent(activity, UserMenuActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Returns the current score of the user with the given username. If the user does not exist,
     * this method returns 0.
     */
    public int getCurrentScore(String username) {
        return userService.getCurrentScore(username);
    }

    /**
     * Returns the top score of the user with the given username. If the user does not exist,
     * this method returns 0.
     */
    public int getTopScore(String username) {
        return userService.getTopScore(username);
    }

    /**
     * Returns the top x users (sorted in non-increasing order) based on the given criterion.
     */
    public List<User> getTopUsers(int x, String criterion) {
        return userService.getTopUsers(x, criterion);
    }

}
