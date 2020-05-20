package com.machczew.covid19;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtQ;
    private TextView txtTitleResult, txtResult, txtResultText;
    private TextView btnResult;
    private ImageButton btnSair;
    private LinearLayout llDiagnostic, llResult, llViewResult, llViewResult0;
    private RelativeLayout rlResult;
    private ImageView txtResult0, imgResult;

    private int result = 0;

    int countQ = 0;

    private ProgressBar progressBar;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        llViewResult0 = findViewById(R.id.llViewResult0);
        txtResult0 = findViewById(R.id.txtResult0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SlideUp0();
            }
        }, 100);
        txtResult0.setVisibility(View.GONE);

        txtQ = findViewById(R.id.txtQ);

        txtTitleResult = findViewById(R.id.txtTitleResult);
        txtResult = findViewById(R.id.txtResult);
        txtResultText = findViewById(R.id.txtResultText);
        imgResult = findViewById(R.id.imgResult);

        btnResult = findViewById(R.id.btnResult);
        llDiagnostic = findViewById(R.id.llDiagnostic);
        llResult = findViewById(R.id.llResult);
        progressBar = findViewById(R.id.progressBar);
        btnSair = findViewById(R.id.btnSair);
        llViewResult = findViewById(R.id.llViewResult);
        rlResult = findViewById(R.id.rlResult);

        llViewResult.setEnabled(false);

        final RadioGroup group = findViewById(R.id.toggle);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if(rb.isClickable()) {
                    String rbQ = rb.getText().toString();
                    rb.setChecked(false);
                    countQ++;
                    CountQuestions(rbQ);
                }
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlResult.setVisibility(View.VISIBLE);
                SlideDown();

                llResult.animate().alpha(1f).setDuration(1000).setListener(null);
                txtResult.animate().alpha(1f).setDuration(1000).setListener(null);
                txtTitleResult.animate().alpha(1f).setDuration(1000).setListener(null);
                txtResultText.animate().alpha(1f).setDuration(1000).setListener(null);
                imgResult.animate().alpha(1f).setDuration(1000).setListener(null);

                if(result >= 7){
                    txtResult.setText(String.valueOf("Pontuação = "+result+" Pontos"));
                    txtResultText.setText(getResources().getString(R.string.resultLow));
                }
                if (result > 7 && result < 11){
                    txtResult.setText(String.valueOf("Pontuação = "+result+" Pontos"));
                    txtResultText.setText(getResources().getString(R.string.resultMiddle));
                }
                else {
                    txtResult.setText(String.valueOf("Pontuação = "+result+" Pontos"));
                    txtResultText.setText(getResources().getString(R.string.resultHigh));
                }


            }
        });

        findViewById(R.id.btnReStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llResult.setAlpha(0);
                txtResult.setAlpha(0);

                txtTitleResult.setAlpha(0);
                txtResultText.setAlpha(0);
                imgResult.animate().alpha(0).setDuration(500).setListener(null);

                SlideUp();
                RestartQuestions();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestartQuestions();
            }
        });

        findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finishAffinity();
            }
        });


    }

    public void RestartQuestions(){
        btnResult.setVisibility(View.GONE);
        btnSair.setImageResource(R.drawable.ic_check_nok);
        btnSair.setEnabled(true);
        txtQ.setText(getString(R.string.q1));
        txtResult.setText(String.valueOf(0));
        txtResultText.setText("");
        progressBar.setProgress(0);
        countQ=0;
        result=0;
    }

    public void CountQuestions( String rbQ){
        switch(countQ) {
            case 1:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q2));
                    result = result + 4;
                    progressBar.setProgress(35);
                } else {
                    txtQ.setText(getString(R.string.q2));
                    progressBar.setProgress(35);
                }
                break;
            case 2:

                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q3));
                    result = result + 4;
                    progressBar.setProgress(70);
                } else {
                    txtQ.setText(getString(R.string.q3));
                    progressBar.setProgress(70);
                }
                break;
            case 3:
                if(rbQ.equals("SIM")){
                    result = result + 1;
                    progressBar.setProgress(100);

                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setProgress(100);

                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }

    }

    public void SlideDown(){
        llViewResult.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(0,0,0, llViewResult.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        llViewResult.startAnimation(animate);
    }

    public void SlideUp(){
        TranslateAnimation animate = new TranslateAnimation(0,0, llViewResult.getHeight(),0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        llViewResult.startAnimation(animate);
    }

    public void SlideUp0(){
        TranslateAnimation animate = new TranslateAnimation(0,0, llViewResult0.getHeight(),0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        llViewResult0.startAnimation(animate);
    }

}
