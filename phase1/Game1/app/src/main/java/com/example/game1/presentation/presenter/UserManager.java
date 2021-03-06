package com.example.game1.presentation.presenter;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;
import com.example.game1.presentation.view.user.UserMenuActivity;

/**
 * A controller class for managing user activity.
 * It is also a proxy to classes in the domain layer.
 */
public class UserManager {

    /**
     * An instance of UserService from the domain layer.
     */
    private UserService userService;

    /**
     * The current user that is logged in.
     */
    private static User currentUser;

    /**
     * Contructs a UserManager
     */
    public UserManager() {
        userService = new UserService();
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
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user
     */
    public static void setCurrentUser(User currentUser) {
        UserManager.currentUser = currentUser;
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

        if ("Song2".equals(music)) {
            newCustomization.setMusicPath(Customization.MusicPath.SONG2);
        } else if ("Song3".equals(music)) {
            newCustomization.setMusicPath(Customization.MusicPath.SONG3);
        } else {
            newCustomization.setMusicPath(Customization.MusicPath.SONG1);
        }

        currentUser.setCustomization(newCustomization);
        updateUserInfo();
    }

    /**
     * Sets the current user's game statistics and level to the information passed in the Game
     * object, game.
     *
     * @param game the Game object
     */
    void updateCurrentUsersGame(Game game) {
        updateGameInfo(currentUser.getCurrentPoints() + game.getNumPoints(),
                currentUser.getCurrentStars() + game.getNumStars(),
                currentUser.getCurrentTaps() + game.getNumTaps(),
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
     * Sets the current user's game statistics and level to the given values
     *
     * @param points the number of points
     * @param stars  the number of stars
     * @param taps   the number of taps
     * @param level  the game level
     */
    private void updateGameInfo(int points, int stars, int taps, int level) {
        currentUser.setCurrentPoints(points);
        currentUser.setCurrentStars(stars);
        currentUser.setCurrentTaps(taps);
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
    void goToUserMenu(AppCompatActivity activity) {
        Intent intent = new Intent(activity, UserMenuActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Returns the user with the top score
     */
    public User getTopUser() {
        return userService.getTopUser();
    }

}
