package com.machczew.covid19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    private LinearLayout llViewResult;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        llViewResult = findViewById(R.id.llViewResult);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SlideDown();
            }
        }, 200);

        final Button btnStart = findViewById(R.id.btnStart);
        final ImageView imgLogo = findViewById(R.id.txtResult);
        final TextView txtInfo = findViewById(R.id.txtInfo);
        final LinearLayout llLogos = findViewById(R.id.llLogos);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                imgLogo.animate().alpha(1f).setDuration(1000).setListener(null);
                txtInfo.animate().alpha(1f).setDuration(1000).setListener(null);
                llLogos.animate().alpha(1f).setDuration(1000).setListener(null);
                btnStart.animate().alpha(1f).setDuration(1000).setListener(null);
            }
        }, 300);




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public void SlideDown(){
        TranslateAnimation animate = new TranslateAnimation(0,0,0, llViewResult.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        llViewResult.startAnimation(animate);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finishAffinity();

    }
}
