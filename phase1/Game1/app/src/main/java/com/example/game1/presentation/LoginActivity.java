package com.example.game1.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.game1.R;
import com.example.game1.presentation.applegame.AppleActivity;
import com.example.game1.presentation.tappinggame.TappingActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Called when the user taps the Login button
     */
    public void sendLoginCredentials(View view) {
        /* TODO: This method should send the login credentials to another class to validate it.
            For now, its just redirects the user to a game*/
        Intent intent = new Intent(this, AppleActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message); // store as many key value pairs in intent as you want
        startActivity(intent);
    }
}
