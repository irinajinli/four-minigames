package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.game1.R;
import com.example.game1.presentation.MainActivity;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.UserManager;
import com.example.game1.presentation.view.applegame.AppleActivity;
import com.example.game1.presentation.view.tappinggame.TappingActivity;

public class UserMenuActivity extends AppCompatActivity {

    private UserManager userManager = AppManager.getInstance().getUserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        EditText userNameText = findViewById(R.id.userNameText);
        userNameText.setText(userManager.getCurrentUser().getUserName());
    }

    /** Called when the user taps the Logout button */
    public void logout(View view) {
        userManager.setCurrentUser(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the 1st Play button */
    public void goToLevel1(View view) {
        Intent intent = new Intent(this, AppleActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the 2nd Play button */
    public void goToLevel2(View view) {
        Intent intent = new Intent(this, TappingActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the 3rd Play button */
    public void goToLevel3(View view) {
//        Intent intent = new Intent(this, JumpingActivity.class);
//        startActivity(intent);
    }

    /** Called when the user taps the Customize button */
    public void goToCustomization(View view) {
        Intent intent = new Intent(this, CustomizationActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the View Statistics button */
    public void goToStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

}
