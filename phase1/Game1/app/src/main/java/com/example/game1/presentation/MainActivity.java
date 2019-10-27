package com.example.game1.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game1.R;
import com.example.game1.presentation.applegame.AppleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the user taps the Login button
     */
    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class
        );
        startActivity(intent);
    }

    /**
     * Called when the user taps the Register button
     */
    public void goToRegistration(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
