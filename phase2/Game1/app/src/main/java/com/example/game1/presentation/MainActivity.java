package com.example.game1.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.view.applegame.AppleActivity;
import com.example.game1.presentation.view.user.LoginActivity;
import com.example.game1.presentation.view.user.RegistrationActivity;

public class MainActivity extends AppCompatActivity {

  private AppManager appManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    appManager = AppManager.getInstance();
    appManager.init(this.getApplicationContext());
  }

  /** Called when the user taps the Login button */
  public void goToLogin(View view) {
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }

  /** Called when the user taps the Register button */
  public void goToRegistration(View view) {
    Intent intent = new Intent(this, RegistrationActivity.class);
    startActivity(intent);
  }

  public void goToJumpingGame(View view){
    Intent intent = new Intent(this, AppleActivity.class);
    startActivity(intent);
  }
}
