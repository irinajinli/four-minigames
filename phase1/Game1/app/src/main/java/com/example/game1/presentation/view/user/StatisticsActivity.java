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
        infoText.setText("Your score = points + (" + AppManager.STAR_FACTOR + " x stars)");

        EditText currPointsText = findViewById(R.id.currPointsText);
        currPointsText.setText(String.valueOf(userManager.getCurrentUser().getCurrentPoints()));

        EditText currStarsText = findViewById(R.id.currStarsText);
        currStarsText.setText(String.valueOf(userManager.getCurrentUser().getCurrentStars()));

        EditText currTapsText = findViewById(R.id.currTapsText);
        currTapsText.setText(String.valueOf(userManager.getCurrentUser().getCurrentTaps()));

        EditText topPointsText = findViewById(R.id.topPointsText);
        topPointsText.setText(String.valueOf(userManager.getCurrentUser().getTopPoints()));

        EditText topStarsText = findViewById(R.id.topStarsText);
        topStarsText.setText(String.valueOf(userManager.getCurrentUser().getTopStars()));

        EditText topTapsText = findViewById(R.id.topTapsText);
        topTapsText.setText(String.valueOf(userManager.getCurrentUser().getTopTaps()));

        if (userManager.getTopUser() != null) {

            System.out.println("=========================");
            System.out.println(userManager.getTopUser().getUserName());
            EditText topPlayerText = findViewById(R.id.topPlayerText);
            topPlayerText.setText("Top player: " + userManager.getTopUser().getUserName());

            EditText topPlayerPointsText = findViewById(R.id.topPlayerPointsText);
            topPlayerPointsText.setText(String.valueOf(userManager.getTopUser().getTopPoints()));

            EditText topPlayerStarsText = findViewById(R.id.topPlayerStarsText);
            topPlayerStarsText.setText(String.valueOf(userManager.getTopUser().getTopStars()));

            EditText topPlayerTapsText = findViewById(R.id.topPlayerTapsText);
            topPlayerTapsText.setText(String.valueOf(userManager.getTopUser().getTopTaps()));
        } else {
            EditText topPlayerText = findViewById(R.id.topPlayerText);
            topPlayerText.setText("Top player: " + userManager.getCurrentUser().getUserName());

            EditText topPlayerPointsText = findViewById(R.id.topPlayerPointsText);
            topPlayerPointsText.setText(String.valueOf(userManager.getCurrentUser().getTopPoints()));

            EditText topPlayerStarsText = findViewById(R.id.topPlayerStarsText);
            topPlayerStarsText.setText(String.valueOf(userManager.getCurrentUser().getTopStars()));

            EditText topPlayerTapsText = findViewById(R.id.topPlayerTapsText);
            topPlayerTapsText.setText(String.valueOf(userManager.getCurrentUser().getTopTaps()));
        }
    }

    /** Called when the user taps the Bac button */
    public void goBack(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);
        startActivity(intent);
    }
}
