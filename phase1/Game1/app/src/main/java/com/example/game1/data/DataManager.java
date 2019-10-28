package com.example.game1.data;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.example.game1.presentation.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/** Responsible for storing and retrieving data. */
public class DataManager extends ContextWrapper {

  /** For logging output. */
  private static final String TAG = "Data Manager"; // tag helps to easily identify

  /** The file to write and read. */
  private static final String DATA_FILE = "game.txt";

  public DataManager() {
    super(null);
  }

  public void init() {}

  /** Create a file and write a line of text to it. */
  private void writeToFile() {
    PrintWriter out = null;

    try {
      OutputStream outStream = openFileOutput(DATA_FILE, Context.MODE_PRIVATE);
      out = new PrintWriter(outStream);
    } catch (FileNotFoundException e) {
      Log.e(TAG, "Error encountered trying to open file for writing: " + DATA_FILE);
    }

    out.println("abc");
    out.close();
  }

  /**
   * Read and return the information in EXAMPLE_FILE.
   *
   * @return the contents of EXAMPLE_FILE.
   */
  private String readFromFile() {
    StringBuffer buffer = new StringBuffer(); // can also use StringBuilder
    try (Scanner scanner = new Scanner(openFileInput(DATA_FILE))) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        buffer.append(line).append('\n');
      }
    } catch (IOException e) {
      Log.e(TAG, "Error encountered trying to open file for reading: " + DATA_FILE);
    }

    return buffer.toString();
  }

  public void createUser(User user) {}

  public User getUser(String userName) {
    return null;
  }
}
