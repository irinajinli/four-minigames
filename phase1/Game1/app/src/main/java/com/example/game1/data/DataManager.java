package com.example.game1.data;

import android.util.Log;
import com.example.game1.presentation.model.User;
import com.example.game1.presentation.presenter.UserManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Responsible for storing and retrieving data.
 */
public class DataManager {

    /** For logging output. */
    private static final String TAG = "Data Manager"; //tag helps to easily identify

    /** The  file to write and read. */
    private static final String DATA_FILE = "game_data.txt";

    private List<UserEntity> userEntities = new ArrayList<>();


    public void init() {}


    /** Create a file and write a line of text to it. */
    private void writeToFile() {
        PrintWriter out = null;

        try {
            String filePath = UserManager.context.getFilesDir().getPath().toString() + DATA_FILE;
            System.out.println("*****************************************");
            System.out.println("*****************************************");
            System.out.println("*****************************************");
            System.out.println("*****************************************");
            System.out.println(filePath);

            File file = new File(filePath);
//            OutputStream outStream = openFileOutput(DATA_FILE, Context.MODE_PRIVATE);
            FileOutputStream outStream = new FileOutputStream(file);
            out = new PrintWriter(outStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "Error encountered trying to open file for writing: " + DATA_FILE);
        }

//        out.print(userEntities.get(0).toString());
        out.write("hello i am testing writing to file");
        out.close();
    }


    /**
     * Read and return the information in EXAMPLE_FILE.
     *
     * @return the contents of EXAMPLE_FILE.
     */
    private String readFromFile() {
        StringBuffer buffer = new StringBuffer(); //can also use StringBuilder
        String filePath = UserManager.context.getFilesDir().getPath().toString() + DATA_FILE;
        try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                buffer.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error encountered trying to open file for reading: " + DATA_FILE);
        }
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println(buffer.toString());
        return buffer.toString();
    }


    public void createUser(String userName, String password) {
        System.out.println("data manager create user");
        UserEntity userEntity = new UserEntity(userName, password);
        userEntities.add(userEntity);
        writeToFile();
        readFromFile();
    }

    public User getUser(String userName) {
        return null;
    }


}
