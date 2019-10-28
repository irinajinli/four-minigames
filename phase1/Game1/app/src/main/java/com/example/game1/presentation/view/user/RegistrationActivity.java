package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game1.R;
import com.example.game1.presentation.view.applegame.AppleActivity;

public class RegistrationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);
  }

  /** Called when the user taps the Register button */
  public void sendRegistrationInput(View view) {
    /* TODO: This method should send the registration input to another class to validate it.
    For now, its just redirects the user to a game*/
    Intent intent = new Intent(this, AppleActivity.class);
    //        EditText editText = (EditText) findViewById(R.id.editText);
    //        String message = editText.getText().toString();
    //        intent.putExtra(EXTRA_MESSAGE, message); // store as many key value pairs in intent as
    // you want
    startActivity(intent);
  }
}
