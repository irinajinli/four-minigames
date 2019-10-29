package com.example.game1.presentation.view.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.UserManager;
import com.example.game1.presentation.view.applegame.AppleActivity;

public class LoginActivity extends AppCompatActivity {

  private UserManager userManager = AppManager.getInstance().getUserManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  /** Called when the user taps the Login button */
  public void sendLoginCredentials(View view) {
    Intent intent = new Intent(this, AppleActivity.class);

    EditText userNameText = findViewById(R.id.userNameText);
    String userName = userNameText.getText().toString();
    EditText passwordText = findViewById(R.id.passwordText);
    String password = passwordText.getText().toString();

    /** TODO: This method should send the login credentials to another class to validate it.
     *     In order to compile + run the app now, just register the user as a new user
     */
    userManager.registerUser(this, userName, password);

    startActivity(intent);
  }


}
