package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.UserManager;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {

    private UserManager userManager = AppManager.getInstance().getUserManager();

    private final String[] spinnerChoices = {"Total Score", "Points", "Stars", "Taps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // Set up the spinner choices
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerChoices);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // Create the spinner listener
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        // Update header 2
                        Object item = parent.getItemAtPosition(pos);
                        EditText header2 = findViewById(R.id.tableHeader2);
                        header2.setText(item.toString() + ":");
                        // Update the values displayed in the scoreboard
                        displayValues(item.toString());
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        // Select the spinner's default option
        spinner.setSelection(0);
    }

    /**
     * Updates the values displayed in the scoreboard based on the current spinner selection
     */
    private void displayValues(String selection) {
        EditText username1 = findViewById(R.id.username1);
        EditText username2 = findViewById(R.id.username2);
        EditText username3 = findViewById(R.id.username3);
        EditText username4 = findViewById(R.id.username4);
        EditText username5 = findViewById(R.id.username5);
        username1.setText("N/A");
        username2.setText("N/A");
        username3.setText("N/A");
        username4.setText("N/A");
        username5.setText("N/A");
        EditText stat1 = findViewById(R.id.stat1);
        EditText stat2 = findViewById(R.id.stat2);
        EditText stat3 = findViewById(R.id.stat3);
        EditText stat4 = findViewById(R.id.stat4);
        EditText stat5 = findViewById(R.id.stat5);
        stat1.setText("N/A");
        stat2.setText("N/A");
        stat3.setText("N/A");
        stat4.setText("N/A");
        stat5.setText("N/A");

        int numUsers;
        List<Pair<String, String>> usernameToStat = new ArrayList<>();
        if ("Points".equals(selection)) {
            numUsers = userManager.getTopUsers(5, "Points").size();
            for (int i = 0; i < numUsers; i++) {
                String username = userManager.getTopUsers(5,
                        "Points").get(i).getUserName();
                int points = userManager.getTopUsers(5,
                        "Points").get(i).getTopIndividualStats().getPoints();
                usernameToStat.add(Pair.create(username, String.valueOf(points)));
            }
        } else if ("Stars".equals(selection)) {
            numUsers = userManager.getTopUsers(5, "Stars").size();
            for (int i = 0; i < numUsers; i++) {
                String username = userManager.getTopUsers(5,
                        "Points").get(i).getUserName();
                int stars = userManager.getTopUsers(5,
                        "Stars").get(i).getTopIndividualStats().getStars();
                usernameToStat.add(Pair.create(username, String.valueOf(stars)));
            }
        } else if ("Taps".equals(selection)) {
            numUsers = userManager.getTopUsers(5, "Taps").size();
            for (int i = 0; i < numUsers; i++) {
                String username = userManager.getTopUsers(5,
                        "Points").get(i).getUserName();
                int taps = userManager.getTopUsers(5,
                        "Taps").get(i).getTopIndividualStats().getTaps();
                usernameToStat.add(Pair.create(username, String.valueOf(taps)));
            }
        } else {
            // Display total scores
            numUsers = userManager.getTopUsers(5, "Total Score").size();
            for (int i = 0; i < numUsers; i++) {
                String username = userManager.getTopUsers(5,
                        "Points").get(i).getUserName();
                int points = userManager.getTopUsers(5,
                        "v").get(i).getStatsOfTopGame().getPoints();
                int stars = userManager.getTopUsers(5,
                        "v").get(i).getStatsOfTopGame().getStars();
                int taps = userManager.getTopUsers(5,
                        "v").get(i).getStatsOfTopGame().getTaps();
                int score = points + stars + taps;
                usernameToStat.add(Pair.create(username, String.valueOf(score)));
            }
        }

        if (numUsers >= 1) {
            username1.setText(usernameToStat.get(0).first);
            stat1.setText(usernameToStat.get(0).second);
            if (numUsers >= 2) {
                username2.setText(usernameToStat.get(1).first);
                stat2.setText(usernameToStat.get(1).second);
                if (numUsers >= 3) {
                    username3.setText(usernameToStat.get(2).first);
                    stat3.setText(usernameToStat.get(2).second);
                    if (numUsers >= 4) {
                        username4.setText(usernameToStat.get(3).first);
                        stat4.setText(usernameToStat.get(3).second);
                        if (numUsers >= 5) {
                            username5.setText(usernameToStat.get(4).first);
                            stat5.setText(usernameToStat.get(4).second);
                        }
                    }
                }
            }
        }
    }

    /**
     * Called when the user taps the Back button
     */
    public void goBack(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }

}