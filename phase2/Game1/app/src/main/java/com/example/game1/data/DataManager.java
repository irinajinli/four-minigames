package com.example.game1.data;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.User;
import com.example.game1.presentation.presenter.AppManager;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * Responsible for storing, retrieving, and loading data.
 */
public class DataManager {

//    private static final String USERNAME = "UserName";
//    private static final String PASSWORD = "Password";
//    private static final String CHARAC_COLOUR = "CharacterColour";
//    private static final String COLOUR_SCHEME = "ColourScheme";
//    private static final String MUSIC = "Music";
//    private static final String TOP_GAME_POINTS = "TopPoints";
//    private static final String TOP_GAME_STARS = "TopStars";
//    private static final String TOP_GAME_TAPS = "TopTaps";
//    private static final String CURR_GAME_POINTS = "CurrPoints";
//    private static final String CURR_GAME_STARS = "CurrStars";
//    private static final String CURR_GAME_TAPS = "CurrTaps";
//    private static final String LAST_COMP_LVL = "LastCompletedLevel";

    private static final String USERNAME = "UserName";
    private static final String PASSWORD = "Password";
    private static final String CHARAC_COLOUR = "CharacterColour";
    private static final String COLOUR_SCHEME = "ColourScheme";
    private static final String MUSIC = "Music";
    private static final String TOP_GAME_POINTS = "PointsOfTopGame";
    private static final String TOP_GAME_STARS = "StarsOfTopGame";
    private static final String TOP_GAME_TAPS = "TapsOfTopGame";
    private static final String CURR_GAME_POINTS = "PointsOfCurrentGame";
    private static final String CURR_GAME_STARS = "StarsOfCurrentGame";
    private static final String CURR_GAME_TAPS = "TapsOfCurrentGame";
    private static final String TOP_IND_POINTS = "TopPoints";
    private static final String TOP_IND_STARS = "TopStars";
    private static final String TOP_IND_TAPS = "TopTaps";
    private static final String LAST_COMP_LVL = "LastCompletedLevel";

    /* For logging output. */
    private static final String TAG = "Data Manager"; //tag helps to easily identify

    /* The file to write and read. */
    private static final String DATA_FILE = "game_data.txt";
//    private static final String DATA_FILE = "migrationTest1.txt";

    /* The map that stores all the users. */
    private Map<String, User> userMap = new HashMap<>();

    /**
     * Constructs a DataManager
     */
    public DataManager() {
        readFromFile();

        writeToFile(userMap.values());

        testPrintTopIndividualStatistics();
    }

    private void testPrintTopIndividualStatistics() {
        // Test prints
        System.out.println("------------------------ SORT BY POINTS ------------------------");
        List<User> lst = sortUsersByPoints();
        for (User user : lst) {
            System.out.println("Username: " + user.getUserName() + " Points: " + user.getTopIndividualStats().getPoints());
        }
        System.out.println("------------------------ SORT BY STARS ------------------------");
        lst = sortUsersByStars();
        for (User user : lst) {
            System.out.println("Username: " + user.getUserName() + " Stars: " + user.getTopIndividualStats().getStars());
        }
        System.out.println("------------------------ SORT BY TAPS ------------------------");
        lst = sortUsersByTaps();
        for (User user : lst) {
            System.out.println("Username: " + user.getUserName() + " Taps: " + user.getTopIndividualStats().getTaps());
        }
    }

    /**
     * Writes all the users to DATA_FILE.
     */
    private void writeToFile(Collection<User> users) {
        PrintWriter out = null;

        try {
            String filePath = AppManager.getInstance().getContext().getFilesDir().getPath() + DATA_FILE;
            System.out.println("*****************************************");
            System.out.println(filePath);

            File file = new File(filePath);
            FileOutputStream outStream = new FileOutputStream(file);
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "Error encountered trying to open file for writing: " + DATA_FILE);
        }

        Iterator<User> iter = users.iterator();
        while (iter.hasNext()) {
            User user = iter.next();
            out.println(USERNAME + ":" + user.getUserName());
            out.println(PASSWORD + ":" + user.getPassword());
            out.println(CHARAC_COLOUR + ":" + user.getCustomization().getCharacterColour());
            out.println(COLOUR_SCHEME + ":" + user.getCustomization().getColourScheme());
            out.println(MUSIC + ":" + user.getCustomization().getMusicPath());
            out.println(TOP_IND_POINTS + ":" + user.getTopIndividualStats().getPoints());
            out.println(TOP_IND_STARS + ":" + user.getTopIndividualStats().getStars());
            out.println(TOP_IND_TAPS + ":" + user.getTopIndividualStats().getTaps());
            out.println(TOP_GAME_POINTS + ":" + user.getStatsOfTopGame().getPoints());
            out.println(TOP_GAME_STARS + ":" + user.getStatsOfTopGame().getStars());
            out.println(TOP_GAME_TAPS + ":" + user.getStatsOfTopGame().getTaps());
            out.println(CURR_GAME_POINTS + ":" + user.getStatsOfCurrentGame().getPoints());
            out.println(CURR_GAME_STARS + ":" + user.getStatsOfCurrentGame().getStars());
            out.println(CURR_GAME_TAPS + ":" + user.getStatsOfCurrentGame().getTaps());
            out.println(LAST_COMP_LVL + ":" + user.getLastCompletedLevel());
        }

        out.close();
    }

    /**
     * Reads the information in DATA_FILE.
     */
    private void readFromFile() {
        StringBuffer buffer = new StringBuffer(); // TESTING PURPOSES
        String filePath = AppManager.getInstance().getContext().getFilesDir().getPath() + DATA_FILE;

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("PATH: " + filePath);

        userMap.clear();

        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            User user = null;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str != null && str.trim().length() > 0 && str.split(":").length > 1) {
                    String[] line = str.split(":");
                    String key = line[0];
                    String value;
                    if (USERNAME.equals(key)) {
                        if (line.length > 1) {
                            value = line[1];
                            buffer.append(key + ": " + value).append('\n');  // TESTING PURPOSES
                            user = new User(value, "");
                            userMap.put(value.toLowerCase(), user);
                        } else {
                            user = null;
                        }
                    } else if (user != null && line.length > 1) {
                        value = line[1];
                        buffer.append(key + ": " + value).append('\n');  // TESTING PURPOSES
                        if (PASSWORD.equals(key)) {
                            user.setPassword(value);
                        } else if (CHARAC_COLOUR.equals(key)) {
                            String characColour = value;
                            if (characColour.equals(Customization.CharacterColour.RED.toString())) {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.RED);
                            } else if (characColour.equals(Customization.CharacterColour.YELLOW.toString())) {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.YELLOW);
                            } else {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.BLUE);
                            }
                        } else if (COLOUR_SCHEME.equals(key)) {
                            String colourScheme = value;
                            if (colourScheme.equals(Customization.ColourScheme.LIGHT.toString())) {
                                user.getCustomization().setColourScheme(Customization.ColourScheme.LIGHT);
                            } else {
                                user.getCustomization().setColourScheme(Customization.ColourScheme.DARK);
                            }
                        } else if (MUSIC.equals(key)) {
                            String music = value;
                            if (music.equals(Customization.MusicPath.SONG2.toString())) {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG2);
                            } else if (music.equals(Customization.MusicPath.SONG3.toString())) {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG3);
                            } else {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG1);
                            }
                        } else if (TOP_GAME_POINTS.equals(key) || "TopPoints".equals(key)) { //TODO: correct this line
                            user.getStatsOfTopGame().setPoints(Integer.parseInt(value));
                            user.getTopIndividualStats().setPoints(Integer.parseInt(value)); //TODO: Delete this line
                        } else if (TOP_GAME_STARS.equals(key) || "TopStars".equals(key)) { //TODO: correct this line
                            user.getStatsOfTopGame().setStars(Integer.parseInt(value));
                            user.getTopIndividualStats().setStars(Integer.parseInt(value)); //TODO: Delete this line
                        } else if (TOP_GAME_TAPS.equals(key) || "TopTaps".equals(key)) { //TODO: correct this line
                            user.getStatsOfTopGame().setTaps(Integer.parseInt(value));
                            user.getTopIndividualStats().setTaps(Integer.parseInt(value)); //TODO: Delete this line
                        } else if (CURR_GAME_POINTS.equals(key) || "CurrPoints".equals(value)) { //TODO: correct this line
                            user.getStatsOfCurrentGame().setPoints(Integer.parseInt(value));
                        } else if (CURR_GAME_STARS.equals(key) || "CurrStars".equals(value)) { //TODO: correct this line
                            user.getStatsOfCurrentGame().setStars(Integer.parseInt(value));
                        } else if (CURR_GAME_TAPS.equals(key) || "CurrTaps".equals(value)) { //TODO: correct this line
                            user.getStatsOfCurrentGame().setTaps(Integer.parseInt(value));
                        }  else if (TOP_IND_POINTS.equals(key)){
                            user.getTopIndividualStats().setPoints(Integer.parseInt(value));
                        } else if (TOP_IND_STARS.equals(key)){
                            user.getTopIndividualStats().setStars(Integer.parseInt(value));
                        } else if (TOP_IND_TAPS.equals(key)) {
                            user.getTopIndividualStats().setTaps(Integer.parseInt(value));
                        } else if (LAST_COMP_LVL.equals(key)) {
                            user.setLastCompletedLevel(Integer.parseInt(value));
                        } else {
                            Log.e(TAG, "Invalid key: " + DATA_FILE);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error encountered trying to open file for reading: " + DATA_FILE);
        }

        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println(buffer.toString());  // TESTING PURPOSES
    }

    /**
     * Adds the given user to userMap and write userMap to file.
     */
    public void createUser(User user) {
        userMap.put(user.getUserName().toLowerCase(), user);
        Collection<User> users = userMap.values();
        writeToFile(users);

        // TESTING PURPOSES (to see if the user was actually added to userMap and written to file
        // correctly
        readFromFile();
    }

    /**
     * Adds the given user's updated information to userMap and write userMap to file.
     */
    public void updateUser(User user) {
        // Calculate the user's current score and top score
        int currentPoints = user.getStatsOfCurrentGame().getPoints();
        int currentStars = user.getStatsOfCurrentGame().getStars();
        int currentTaps = user.getStatsOfCurrentGame().getTaps();
        int currentScore = calculateScore(currentPoints, currentStars, currentTaps);
        int topScore = calculateScore(
                user.getStatsOfTopGame().getPoints(),
                user.getStatsOfTopGame().getStars(),
                user.getStatsOfTopGame().getTaps());

        // If the user's current score is higher than their top score, update the statistics of
        // their top game
        if (currentScore > topScore) {
            user.getStatsOfTopGame().setPoints(currentPoints);
            user.getStatsOfTopGame().setStars(currentStars);
            user.getStatsOfTopGame().setTaps(currentTaps);
        }

        // Update the user's top individual statistics
        if (currentPoints > user.getTopIndividualStats().getPoints()) {
            user.getTopIndividualStats().setPoints(currentPoints);
        }
        if (currentStars > user.getTopIndividualStats().getStars()) {
            user.getTopIndividualStats().setStars(currentStars);
        }
        if (currentTaps > user.getTopIndividualStats().getTaps()) {
            user.getTopIndividualStats().setTaps(currentTaps);
        }

        // Add the user to userMap and write userMap to file
        createUser(user);
    }

    /**
     * Returns the user in userMap with the given username. If no such user exists, return null.
     */
    public User getUser(String userName) {
        return userMap.get(userName);
    }

    /**
     * Returns the user with the top score.
     */
    public User getTopUser() {
        Collection<User> users = userMap.values();
        Iterator<User> iter = users.iterator();

        User topUser = null;
        int topScore = 0;
        while (iter.hasNext()) {
            User user = iter.next();
            int userScore = calculateScore(
                    user.getStatsOfTopGame().getPoints(),
                    user.getStatsOfTopGame().getStars(),
                    user.getStatsOfTopGame().getTaps());
            if (userScore >= topScore) {
                topUser = user;
                topScore = userScore;
            }
        }
        return topUser;
    }

    /**
     * Returns the score calculated fromm the given number of points, stars, and taps.
     */
    private int calculateScore(int points, int stars, int taps) {
//        return points + (AppManager.STAR_FACTOR * stars);
        return points + stars + taps;
    }

    /**
     * Returns a list of users sorted by number of points
     */
    public List sortUsersByPoints() {
        return sortUsers(new PointsComparator());
    }

    /**
     * Returns a list of users sorted by number of stars
     */
    public List sortUsersByStars() {
        return sortUsers(new StarsComparator());
    }

    /**
     * Returns a list of users sorted by number of taps
     */
    public List sortUsersByTaps() {
        return sortUsers(new TapsComparator());
    }

    /**
     * Returns a list of users sorted using the given comparator
     */
    private List sortUsers(Comparator comparator) {
        List<User> userList = new ArrayList(userMap.values());
        Collections.sort(userList, comparator);
        return userList;
    }

}
