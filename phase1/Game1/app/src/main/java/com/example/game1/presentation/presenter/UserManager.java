package com.example.game1.presentation.presenter;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.Game;
import com.example.game1.presentation.model.User;
import com.example.game1.domain.UserService;
import com.example.game1.presentation.view.user.UserMenuActivity;

public class UserManager {

    private UserService userService;

    // The current user that is logged in
    private static User currentUser;


    UserManager(){
        userService = new UserService();
    }

    /** Try to register a new User with username, userName, and password, password. If successful,
     * return true. Otherwise, return false. */
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

    /** Try to login a User with username, userName, and password, password. If successful, return
     * true. Otherwise, return false. */
    public boolean loginUser(String userName, String password) {
        // Send the user to userService for user validation
        User user = userService.getUser(userName, password);
        if (user != null) {
            setCurrentUser(user);
        }
        return user != null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserManager.currentUser = currentUser;
    }

    public void updateCurrentUsersCustomization(String characterColour, String colourScheme,
                                                String music){
        Customization newCustomization = new Customization();

        if ("Red".equals(characterColour)){
            newCustomization.setCharacterColour(Customization.CharacterColour.RED);
        } else if ("Yellow".equals(characterColour)){
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

    void updateCurrentUsersGame(Game game) {
        updateGameInfo(game.getNumPoints(), game.getNumStars(), game.getNumTaps(), game.getLevel());
        updateUserInfo();
    }

    public void restartCurrentUsersGame() {
        updateGameInfo(0 ,0, 0, 0);
        updateUserInfo();
    }

    /** Updates any changes to the current user's game statistics and level*/
    private void updateGameInfo(int points, int stars, int taps, int level) {
        currentUser.setCurrentPoints(points);
        currentUser.setCurrentStars(stars);
        currentUser.setCurrentTaps(taps);
        currentUser.setLastCompletedLevel(level);
    }

    /** Sends any changes to the current user's info to the backend through UserService */
    private void updateUserInfo() {
        userService.updateUser(currentUser);
    }

    void goToUserMenu(AppCompatActivity activity) {
        Intent intent = new Intent(activity, UserMenuActivity.class);
        activity.startActivity(intent);
    }

    public User getTopUser(){
        return userService.getTopUser();
    }

}
