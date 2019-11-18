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
        Display the current score of the current user
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

        EditText currScoreText = findViewById(R.id.currScoreText);
        currScoreText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfCurrentGame().getPoints() +
                userManager.getCurrentUser().getStatsOfCurrentGame().getStars() +
                userManager.getCurrentUser().getStatsOfCurrentGame().getTaps()));

        /*
        Display the top score of the current user
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

        EditText topScoreText = findViewById(R.id.topScoreText);
        topScoreText.setText(String.valueOf(
                userManager.getCurrentUser().getStatsOfTopGame().getPoints() +
                        userManager.getCurrentUser().getStatsOfTopGame().getStars() +
                        userManager.getCurrentUser().getStatsOfTopGame().getTaps()));

        /*
        Display the top individual statistics of the current user
         */
        EditText topPlayerPointsText = findViewById(R.id.topIndPointsText);
        topPlayerPointsText.setText(String.valueOf(
                userManager.getCurrentUser().getTopIndividualStats().getPoints()));

        EditText topPlayerStarsText = findViewById(R.id.topIndStarsText);
        topPlayerStarsText.setText(String.valueOf(
                userManager.getCurrentUser().getTopIndividualStats().getStars()));

        EditText topPlayerTapsText = findViewById(R.id.topIndTapsText);
        topPlayerTapsText.setText(String.valueOf(
                userManager.getCurrentUser().getTopIndividualStats().getTaps()));
    }

    /**
     * Called when the user taps the Back button
     */
    public void goBack(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}
