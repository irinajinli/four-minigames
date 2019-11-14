package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.game1.R;
import com.example.game1.presentation.model.User;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.UserManager;
import com.example.game1.presentation.view.applegame.AppleActivity;

public class RegistrationActivity extends AppCompatActivity {

    private UserManager userManager = AppManager.getInstance().getUserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * Called when the user taps the Register button
     */
    public void sendRegistrationInput(View view) {
        Intent intent = new Intent(this, UserMenuActivity.class);

//        EditText outputText = (EditText) findViewById(R.id.outputText);
//        outputText.setText("");

        EditText userNameText = findViewById(R.id.userNameText);
        String userName = userNameText.getText().toString();
        EditText passwordText = findViewById(R.id.passwordText);
        String password = passwordText.getText().toString();
        EditText confirmPasswordText = findViewById(R.id.confirmPasswordText);
        String confirmPassword = confirmPasswordText.getText().toString();

        if (password.equals(confirmPassword)){
            if (userManager.registerUser(userName.toLowerCase(), password)) {
                // Registration successful
                startActivity(intent);
            } else {
                // Username already taken
                // TODO: Display message that the username is already taken
            }
        } else {
            // TODO: Display message that the passwords don't match
//            outputText.setText("Passwords don't match");
        }

    }
}
