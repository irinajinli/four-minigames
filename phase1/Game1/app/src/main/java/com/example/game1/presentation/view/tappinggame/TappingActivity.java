package com.example.game1.presentation.view.tappinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.game1.R;
import com.example.game1.presentation.presenter.AppManager;
import com.example.game1.presentation.presenter.common.GameManager;
import com.example.game1.presentation.presenter.tappinggame.TappingGameManager;

/** The Tapping Activity Class */
public class TappingActivity extends AppCompatActivity {
//
//  /** Taping Circle Image */
//  ImageView iv_tap;
//  /** Runnning character Image */
//  //    ImageView iv_character;
//
//  /** TextView to display result */
//  TextView tv_result;
//  /** TextView to display Tapping info */
//  TextView tv_info;
//
//  /** TextView to display speed */
//  TextView tv_speed;
//
//  /** TextView to display points */
//  TextView tv_points;
//
//  /** TextView to display star */
//  TextView tv_star;
//
//  /** current number of Taps */
//  int currentTaps = 0;
//
//  /** number of points earned */
//  int points = 0;
//
//  /** boolean to check if the game is started */
//  boolean gameStarted = false;
//
//  /** Timer to count down seconds left */
//  CountDownTimer timer;
//
//  /** best result */
//  int bestResult = 0;
//
//  /** number of stars earnd. */
//  int star = 0;

  //  /** Screen width. */
  //  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  //  /** Screen height. */
  //  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  /** The activity class corresponding this view */

  //  TappingGameView tappingGameView = new TappingGameView(this);
  //  GameManager gameManager =
  //      AppManager.getInstance().getTappingGameManager((int) (screenHeight), (int) (screenWidth));
//

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
            .setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(new TappingGameView(this));
  }


//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_tapping);
//
//    // mainLayout = (RelativeLayout) findViewById(R.id.tapping);
//
//    // Using code from https://www.youtube.com/watch?v=Kzv3oMK3PEg
//    // adding logic to generate points, stars speed, taps and updating statistics logic using
//    // TappingGameManager class.
//    // code is done but there is a problem when exit game. So I just comment the code for now.
//    // will use them later once the problem is fixed.
//
//    // set image view for tapping cicle
//    iv_tap = findViewById(R.id.iv_tap);
//    // iv_character = findViewById(R.id.iv_pug);
//    // set text view to the text view on the screen
//    tv_result = findViewById(R.id.tv_result);
//    tv_info = findViewById(R.id.tv_info);
//    tv_speed = findViewById(R.id.tv_speed);
//    tv_points = findViewById(R.id.tv_points);
//    tv_star = findViewById(R.id.tv_star);
//
//    // get and display the best result
//    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
//    bestResult = preferences.getInt("hightScore", 0);
//
//    // display textview best result
//    tv_result.setText("Best Result: " + bestResult);
//
//    // set onClinckListener to track number of taps and update points owned
//    iv_tap.setOnClickListener(
//        new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            if (gameStarted) {
//              // count the taps if the game is started
//              currentTaps++;
//              points++;
//
//            } else {
//              // start the game if it is not started
//              tv_info.setText("Tap, tap, tap...");
//              gameStarted = true;
//              timer.start();
//            }
//          }
//        });
//
//    // timer for 10 seconds with interval 1 second
//    timer =
//        new CountDownTimer(10000, 1000) {
//          @Override
//          public void onTick(long millisUntilFinished) {
//            // display the remaining time
//            long timeTillEnd = (millisUntilFinished / 1000) + 1;
//            long secondsPassed = 10 - timeTillEnd;
//
//            // add logic to catch speed for the time passed.
//            double speed;
//
//            if (0 == secondsPassed) {
//              speed = 0;
//
//            } else {
//              speed = currentTaps / secondsPassed;
//
//              // set star to be the maximum speed reached for now
//              if (star < (int) speed) {
//                star = (int) speed;
//              }
//            }
//
//            // Set TextView to its info accordingly
//            tv_result.setText("Time Remaining: " + timeTillEnd);
//            tv_speed.setText("Current Speed: " + speed);
//            tv_star.setText("Current Star: " + star);
//            tv_points.setText("Current Points: " + points);
//          }
//
//          @Override
//          public void onFinish() {
//            // the game is over
//            iv_tap.setEnabled(false);
//            gameStarted = false;
//            tv_info.setText("Game Over!");
//
//            // check the high score and save the new result if better
//            if (currentTaps > bestResult) {
//              bestResult = currentTaps;
//
//              SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
//              SharedPreferences.Editor editor = preferences1.edit();
//              editor.putInt("highScore", bestResult);
//              editor.apply();
//              ;
//            }
//
//            // display the best result and the current one
//            tv_result.setText("Best Result: " + bestResult + "\nCurrent Taps: " + currentTaps);
//
//            // prepare for new game after 2 seconds
//            new Handler()
//                .postDelayed(
//                    new Runnable() {
//                      @Override
//                      public void run() {
//                        iv_tap.setEnabled(true);
//                        tv_info.setText("Start Tapping");
//                        currentTaps = 0;
//                      }
//                    },
//                    2000);
//
//            // using gameManager to trigger gameOver() and passing statistics
//            //            ((TappingGameManager) gameManager).updateResult(points, star,
//            // currentTaps);
//            //            gameManager.gameOver();
//          }
//        };
//  }
}
