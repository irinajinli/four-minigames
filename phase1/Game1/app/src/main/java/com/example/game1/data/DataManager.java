package com.example.game1.data;

import android.util.Log;

import com.example.game1.presentation.model.Customization;
import com.example.game1.presentation.model.User;
import com.example.game1.presentation.presenter.AppManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


/**
 * Responsible for storing and retrieving data.
 */
public class DataManager {

    private static final String USERNAME_KEY = "UserName";
    private static final String PASSWORD_KEY = "Password";
    private static final String CHARAC_COLOUR_KEY = "CharacterColour";
    private static final String COLOUR_SCHEME_KEY = "ColourScheme";
    private static final String MUSIC_KEY = "Music";
    private static final String POINTS_KEY = "Points";
    private static final String STARS_KEY = "Stars";
    private static final String TAPS_KEY = "Taps";
    private static final String LAST_COMP_LVL_KEY = "LastCompletedLevel";

    /**
     * For logging output.
     */
    private static final String TAG = "Data Manager"; //tag helps to easily identify

    /**
     * The  file to write and read.
     */
    private static final String DATA_FILE = "game_data.txt";


    /**
     * The map that stores all the users.
     */
    private Map<String, User> userMap = new HashMap<>();


    public DataManager() {
        readFromFile();
    }


    /**
     * Create all the users to DATA_FILE.
     */
    private void writeToFile(Collection<User> users) {
        PrintWriter out = null;

        try {
            String filePath = AppManager.getInstance().getContext().getFilesDir().getPath() + DATA_FILE;
            System.out.println("*****************************************");
            System.out.println("*****************************************");
            System.out.println("*****************************************");
            System.out.println("*****************************************");
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
            out.println(USERNAME_KEY + ":" + user.getUserName());
            out.println(PASSWORD_KEY + ":" + user.getPassword());
            out.println(CHARAC_COLOUR_KEY + ":" + user.getCustomization().getCharacterColour());
            out.println(COLOUR_SCHEME_KEY + ":" + user.getCustomization().getColourScheme());
            out.println(MUSIC_KEY + ":" + user.getCustomization().getMusicPath());
            out.println(POINTS_KEY + ":" + user.getTotalPoints());
            out.println(STARS_KEY + ":" + user.getTotalStars());
            out.println(TAPS_KEY + ":" + user.getTotalTaps());
            out.println(LAST_COMP_LVL_KEY + ":" + user.getLastCompletedLevel());
        }

        out.close();
    }


    /**
     * Read the information in DATA_FILE.
     */
    private void readFromFile() {
        StringBuffer buffer = new StringBuffer(); // TESTING PURPOSES
        String filePath = AppManager.getInstance().getContext().getFilesDir().getPath() + DATA_FILE;

        userMap.clear();

        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            User user = null;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str != null && str.trim().length() > 0 && str.split(":").length > 1) {
//                    System.out.println("?????????????????????????????????????????");
//                    System.out.println(str); // TESTING PURPOSES
                    String[] line = str.split(":");
                    String key = line[0];
//                    String value = line[1];
                    String value;
                    if (USERNAME_KEY.equals(key)) {
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
                        if (PASSWORD_KEY.equals(key)) {
                            user.setPassword(value);
                        } else if (CHARAC_COLOUR_KEY.equals(key)) {
                            String characColour = value;
                            if (characColour.equals(Customization.CharacterColour.RED.toString())) {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.RED);
                            } else if (characColour.equals(Customization.CharacterColour.YELLOW.toString())) {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.YELLOW);
                            } else {
                                user.getCustomization().setCharacterColour(Customization.CharacterColour.BLUE);
                            }
                        } else if (COLOUR_SCHEME_KEY.equals(key)) {
                            String colourScheme = value;
                            if (colourScheme.equals(Customization.ColourScheme.LIGHT.toString())) {
                                user.getCustomization().setColourScheme(Customization.ColourScheme.LIGHT);
                            } else {
                                user.getCustomization().setColourScheme(Customization.ColourScheme.DARK);
                            }
                        } else if (MUSIC_KEY.equals(key)) {
                            String music = value;
                            if (music.equals(Customization.MusicPath.SONG2.toString())) {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG2);
                            } else if (music.equals(Customization.MusicPath.SONG3.toString())) {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG3);
                            } else {
                                user.getCustomization().setMusicPath(Customization.MusicPath.SONG1);
                            }
                        } else if (POINTS_KEY.equals(key)) {
                            user.setTotalPoints(Integer.parseInt(value));
                        } else if (STARS_KEY.equals(key)) {
                            user.setTotalStars(Integer.parseInt(value));
                        } else if (TAPS_KEY.equals(key)) {
                            user.setTotalTaps(Integer.parseInt(value));
                        } else if (LAST_COMP_LVL_KEY.equals(key)) {
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
     * Add the user, user, to userMap and write the user's information file.
     */
    public void createUser(User user) {
        System.out.println("data manager create user");
        userMap.put(user.getUserName().toLowerCase(), user);
        Collection<User> users = userMap.values();
        writeToFile(users);

        // TESTING PURPOSES (to see if the user was actually added to userMap and written to file
        // correctly
        readFromFile();
    }


    /**
     * Add user's updated information to userMap and write to file.
     */
    public void updateUser(User user) {
        createUser(user);
    }

    /**
     * Return the user in userMap with username, userName. If no such user exists, return null.
     */
    public User getUser(String userName) {
        return userMap.get(userName);
    }

}
