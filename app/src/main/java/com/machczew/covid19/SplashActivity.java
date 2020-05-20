package com.machczew.covid19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(500);
                } catch (Exception e) {

                } finally {
                    startActivity(new Intent(getBaseContext(), IntroActivity.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        };
        th.start();
    }
}
