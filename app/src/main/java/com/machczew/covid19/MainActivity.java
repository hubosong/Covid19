package com.machczew.covid19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtQ, txtResult, txtResultLow, txtResultMiddle, txtResultHigh, btnResult;
    private ImageButton btnSair;
    private LinearLayout llDiagnostic, llResult, llViewResult, llViewResult0;
    private RelativeLayout rlResult;
    private ImageView imgResult;
    private ImageView imgLogoMain;
    private RadioGroup group;
    private ProgressBar progressBar;

    private int result = 0;
    private int countQ = 0;

    boolean doubleBackPressed = false;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        llViewResult0 = findViewById(R.id.llViewResult0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SlideUp0();
            }
        }, 100);
        findViewById(R.id.txtResult0).setVisibility(View.GONE);

        txtQ = findViewById(R.id.txtQ);
        txtResult = findViewById(R.id.txtResult);
        txtResultLow = findViewById(R.id.txtResultLow);
        txtResultMiddle = findViewById(R.id.txtResultMiddle);
        txtResultHigh = findViewById(R.id.txtResultHigh);
        imgResult = findViewById(R.id.imgResult);
        btnResult = findViewById(R.id.btnResult);
        llDiagnostic = findViewById(R.id.llDiagnostic);
        llResult = findViewById(R.id.llResult);
        progressBar = findViewById(R.id.progressBar);
        btnSair = findViewById(R.id.btnSair);
        llViewResult = findViewById(R.id.llViewResult);
        rlResult = findViewById(R.id.rlResult);
        imgLogoMain = findViewById(R.id.imgLogoMain);

        llViewResult.setEnabled(false);

        group = findViewById(R.id.toggle);
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
                imgLogoMain.setVisibility(View.GONE);
                SlideDown();

                llResult.animate().alpha(1f).setDuration(1000).setListener(null);
                txtResult.animate().alpha(1f).setDuration(1000).setListener(null);
                imgResult.animate().alpha(1f).setDuration(1000).setListener(null);

                if(result < 8){
                    txtResult.setText("Pontuação = " + result + " Pontos");
                    txtResultLow.animate().alpha(1f).setDuration(2000).setListener(null);
                    btnResult.setEnabled(false);
                }
                else if (result >= 8 && result <= 11 ) {
                    txtResult.setText("Pontuação = " + result + " Pontos");
                    txtResultMiddle.animate().alpha(1f).setDuration(2000).setListener(null);
                    btnResult.setEnabled(false);
                } else if (result > 11){
                    txtResult.setText("Pontuação = " + result + " Pontos");
                    txtResultHigh.animate().alpha(1f).setDuration(2000).setListener(null);
                    btnResult.setEnabled(false);
                }
            }
        });

        findViewById(R.id.btnReStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llResult.setAlpha(0);
                txtResult.setAlpha(0);

                txtResultLow.setAlpha(0);
                txtResultMiddle.setAlpha(0);
                txtResultHigh.setAlpha(0);
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

        imgLogoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "\nDesenvolvido por\nRobson Machczew 胡博嵩\n", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void RestartQuestions(){
        btnResult.setVisibility(View.GONE);
        btnSair.setImageResource(R.drawable.ic_check_nok);
        btnSair.setEnabled(true);
        txtQ.setText(getString(R.string.q1));
        txtResult.setText(String.valueOf(0));
        progressBar.setProgress(0);
        countQ=0;
        result=0;
        imgLogoMain.setVisibility(View.VISIBLE);
        EnableRadioButtons();
        btnResult.setEnabled(true);
    }

    public void CountQuestions( String rbQ){
        switch(countQ) {
            case 1:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q2));
                    result = result + 4;
                    progressBar.setProgress(8);
                } else {
                    txtQ.setText(getString(R.string.q2));
                    progressBar.setProgress(8);
                }
                break;
            case 2:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q3));
                    result = result + 4;
                    progressBar.setProgress(16);
                } else {
                    txtQ.setText(getString(R.string.q3));
                    progressBar.setProgress(16);
                }
                break;
            case 3:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q4));
                    result = result + 3;
                    progressBar.setProgress(24);
                } else {
                    txtQ.setText(getString(R.string.q4));
                    progressBar.setProgress(24);
                }
                break;
            case 4:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q5));
                    result = result + 3;
                    progressBar.setProgress(32);
                } else {
                    txtQ.setText(getString(R.string.q5));
                    progressBar.setProgress(32);
                }
                break;
            case 5:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q6));
                    result = result + 3;
                    progressBar.setProgress(40);
                } else {
                    txtQ.setText(getString(R.string.q6));
                    progressBar.setProgress(40);
                }
                break;
            case 6:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q7));
                    result = result + 1;
                    progressBar.setProgress(48);
                } else {
                    txtQ.setText(getString(R.string.q7));
                    progressBar.setProgress(48);
                }
                break;
            case 7:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q8));
                    result = result + 1;
                    progressBar.setProgress(56);
                } else {
                    txtQ.setText(getString(R.string.q8));
                    progressBar.setProgress(56);
                }
                break;
            case 8:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q9));
                    result = result + 1;
                    progressBar.setProgress(64);
                } else {
                    txtQ.setText(getString(R.string.q9));
                    progressBar.setProgress(64);
                }
                break;
            case 9:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q10));
                    result = result + 1;
                    progressBar.setProgress(72);
                } else {
                    txtQ.setText(getString(R.string.q10));
                    progressBar.setProgress(72);
                }
                break;
            case 10:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q11));
                    result = result + 1;
                    progressBar.setProgress(80);
                } else {
                    txtQ.setText(getString(R.string.q11));
                    progressBar.setProgress(80);
                }
                break;
            case 11:
                if(rbQ.equals("SIM")){
                    txtQ.setText(getString(R.string.q12));
                    result = result + 1;
                    progressBar.setProgress(88);
                } else {
                    txtQ.setText(getString(R.string.q12));
                    progressBar.setProgress(88);
                }
                break;
            case 12:
                if(rbQ.equals("SIM")){
                    result = result + 1;
                    progressBar.setProgress(100);

                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);

                    DisableRadioButtons();

                } else {
                    progressBar.setProgress(100);

                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);

                    DisableRadioButtons();
                }
                break;

            default:
                break;
        }

    }

    public void DisableRadioButtons(){
        group.findViewById(R.id.rbSIM).setEnabled(false);
        group.findViewById(R.id.rbNAO).setEnabled(false);
    }

    public void EnableRadioButtons(){
        group.findViewById(R.id.rbSIM).setEnabled(true);
        group.findViewById(R.id.rbNAO).setEnabled(true);
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

    @Override
    public void onBackPressed() {
        if (btnResult.isEnabled()){
            if (!doubleBackPressed) {
                this.doubleBackPressed = true;

                Snackbar snackBar = Snackbar.make(findViewById(R.id.clSnackbar), "Para SAIR pressione novamente o botão VOLTAR", Snackbar.LENGTH_LONG);
                snackBar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                snackBar.getView().findViewById(android.support.design.R.id.snackbar_text).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                snackBar.show();

            /*
            Snackbar.make(findViewById(R.id.clSnackbar), "Você realmente deseja SAIR?", Snackbar.LENGTH_LONG)
                    .setAction("SAIR", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //button action here
                            System.exit(0);
                        }
                    }).setActionTextColor(Color.RED)
                    .show();
                    */

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackPressed = false;
                    }
                }, 2000);
            }
            else {
                System.exit(0);
            }
        }

    }

}
