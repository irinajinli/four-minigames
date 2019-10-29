package com.example.game1.presentation.view.jumpinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;

import com.example.game1.presentation.view.applegame.AppleGameView;

/**
 * ADD TO NOTES
 * <p>
 * error encountered: Activity not registered with Manifest --> had to manually add it to xml
 */
public class JumpingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new JumpingGameView(this));
    }
}
