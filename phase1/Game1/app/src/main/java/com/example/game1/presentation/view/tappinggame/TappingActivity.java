package com.example.game1.presentation.view.tappinggame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.game1.R;

public class TappingActivity extends AppCompatActivity {
    ImageView iv_tap, iv_character;
    TextView tv_result, tv_info, tv_speed, tv_points, tv_star;

    int currentTaps = 0;
    int points = 0;
    boolean gameStarted = false;

    CountDownTimer timer;
    int bestResult = 0;

    int star = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapping);

        // mainLayout = (RelativeLayout) findViewById(R.id.tapping);

        iv_tap = findViewById(R.id.iv_tap);
        iv_character = findViewById(R.id.iv_pug);
        tv_result = findViewById(R.id.tv_result);
        tv_info = findViewById(R.id.tv_info);
        tv_speed = findViewById(R.id.tv_speed);
        tv_points = findViewById(R.id.tv_points);
        tv_star = findViewById(R.id.tv_star);

        // get and display the best result
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestResult = preferences.getInt("hightScore", 0);

        tv_result.setText("Best Result: " + bestResult);

        iv_tap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (gameStarted) {
                            // count the taps if the game is started
                            currentTaps++;
                            points++;

                        } else {
                            // start the game if it is not started
                            tv_info.setText("Tap, tap, tap...");
                            gameStarted = true;
                            timer.start();
                        }
                    }
                });

        // timer for 10 seconds with interval 1 second
        timer =
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // display the remaining time
                        long timeTillEnd = (millisUntilFinished / 1000) + 1;
                        long secondsPassed = 10 - timeTillEnd;

                        double speed;


                        if (0 == secondsPassed) {
                            speed = 0;

                        } else {
                            speed = currentTaps / secondsPassed;
                            if (star < (int) speed) {
                                star = (int) speed;
                            }
                        }

                        tv_result.setText("Time Remaining: " + timeTillEnd);
                        tv_speed.setText("Current Speed: " + speed);
                        tv_star.setText("Current Star: " + star);
                        tv_points.setText("Current Points: " + points);



                    }

                    @Override
                    public void onFinish() {
                        // the game is over
                        iv_tap.setEnabled(false);
                        gameStarted = false;
                        tv_info.setText("Game Over!");

                        // check the high score and save the new result if better
                        if (currentTaps > bestResult) {
                            bestResult = currentTaps;

                            SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                            SharedPreferences.Editor editor = preferences1.edit();
                            editor.putInt("highScore", bestResult);
                            editor.apply();
                            ;
                        }

                        // display the best result and the current one
                        tv_result.setText("Best Result: " + bestResult + "\nCurrent Taps: " + currentTaps);

                        // prepare for new game after 2 seconds
                        new Handler()
                                .postDelayed(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                iv_tap.setEnabled(true);
                                                tv_info.setText("Start Tapping");
                                                currentTaps = 0;
                                            }
                                        },
                                        2000);
                    }
                };
    }
}
