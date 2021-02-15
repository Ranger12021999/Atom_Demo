package com.bhai.aman.atomdemo;
//created by AMan KUmar SHarma

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;

    private ImageView logoSplash;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scr);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }
    private void init(){

        logoSplash = findViewById(R.id.ivLogoSplash);
        textView = findViewById(R.id.title1);
        Typeface typeface1 = ResourcesCompat.getFont(this, R.font.acme_regular);
        textView.setTypeface(typeface1);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);


    }


}