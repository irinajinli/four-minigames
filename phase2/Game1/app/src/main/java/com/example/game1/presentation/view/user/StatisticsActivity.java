package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.UserManager;

public class StatisticsActivity extends AppCompatActivity {

    private UserManager userManager = AppManager.getInstance().getUserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        EditText infoText = findViewById(R.id.infoText);
        infoText.setText("Your score = points + stars + taps");

        /*
        Display the current statistics of the current user
         */
        EditText currPointsText = findViewById(R.id.currPointsText);
        currPointsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfCurrentGame().getPoints()));

        EditText currStarsText = findViewById(R.id.currStarsText);
        currStarsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfCurrentGame().getStars()));

        EditText currTapsText = findViewById(R.id.currTapsText);
        currTapsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfCurrentGame().getTaps()));

        /*
        Display the top statistics of the current user
         */
        EditText topPointsText = findViewById(R.id.topPointsText);
        topPointsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfTopGame().getPoints()));

        EditText topStarsText = findViewById(R.id.topStarsText);
        topStarsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfTopGame().getStars()));

        EditText topTapsText = findViewById(R.id.topTapsText);
        topTapsText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfTopGame().getTaps()));

        /*
        Display the top statistics of the top user
         */
        if (userManager.getTopUser() != null) {
            // If a top user exists, display the top statistics of the top user
            EditText topPlayerText = findViewById(R.id.topPlayerText);
            topPlayerText.setText("Top player: " + userManager.getTopUser().getUserName());

            EditText topPlayerPointsText = findViewById(R.id.topPlayerPointsText);
            topPlayerPointsText.setText(String.valueOf(
                    userManager.getTopUser().getStatsOfTopGame().getPoints()));

            EditText topPlayerStarsText = findViewById(R.id.topPlayerStarsText);
            topPlayerStarsText.setText(String.valueOf(
                    userManager.getTopUser().getStatsOfTopGame().getStars()));

            EditText topPlayerTapsText = findViewById(R.id.topPlayerTapsText);
            topPlayerTapsText.setText(String.valueOf(
                    userManager.getTopUser().getStatsOfTopGame().getTaps()));
        } else {
            // Else, the current user must be the only user, so display the top statistics of the
            // current user.
            EditText topPlayerText = findViewById(R.id.topPlayerText);
            topPlayerText.setText("Top player: " + userManager.getCurrentUser().getUserName());

            EditText topPlayerPointsText = findViewById(R.id.topPlayerPointsText);
            topPlayerPointsText.setText(String.valueOf(
                    userManager.getCurrentUser().getStatsOfTopGame().getPoints()));

            EditText topPlayerStarsText = findViewById(R.id.topPlayerStarsText);
            topPlayerStarsText.setText(String.valueOf(
                    userManager.getCurrentUser().getStatsOfTopGame().getStars()));

            EditText topPlayerTapsText = findViewById(R.id.topPlayerTapsText);
            topPlayerTapsText.setText(String.valueOf(
                    userManager.getCurrentUser().getStatsOfTopGame().getTaps()));
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
