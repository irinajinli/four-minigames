package com.example.game1.presentation.applegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.game1.domain.applegame.AppleGameView;

// public class AppleActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_apple);
//    }
// }

// TODO: above is og AppleActivity; below is Fish Tank AppleActivity

// package com.example.game1.Data;

import android.view.Window;
import android.view.WindowManager;

public class AppleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new AppleGameView(this));
    }
}
