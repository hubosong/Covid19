package com.example.covid19;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtQ, txtResult;
    private Button btnResult;
    private ImageButton btnSair;
    private LinearLayout llDiagnostic, llResult;

    private int result = 0;

    int countQ = 0;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        txtQ = findViewById(R.id.txtQ);
        txtResult = findViewById(R.id.txtResult);
        btnResult = findViewById(R.id.btnResult);
        llDiagnostic = findViewById(R.id.llDiagnostic);
        llResult = findViewById(R.id.llResult);
        progressBar = findViewById(R.id.progressBar);
        btnSair = findViewById(R.id.btnSair);

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
                btnResult.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                btnSair.setVisibility(View.GONE);

                txtResult.setText(String.valueOf(result));
                llResult.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.btnReStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llResult.setVisibility(View.GONE);
                btnResult.setVisibility(View.GONE);
                llDiagnostic.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                btnSair.setVisibility(View.VISIBLE);
                btnSair.setImageResource(R.drawable.ic_check_nok);
                btnSair.setEnabled(true);

                txtQ.setText(getString(R.string.q1));
                txtResult.setText(String.valueOf(0));
                progressBar.setProgress(0);
                countQ=0;
                result=0;
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQ.setText(getString(R.string.q1));
                txtResult.setText(String.valueOf(0));
                progressBar.setProgress(0);
                countQ=0;
                result=0;
            }
        });


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

                    llDiagnostic.setVisibility(View.GONE);
                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setProgress(100);

                    llDiagnostic.setVisibility(View.GONE);
                    btnSair.setImageResource(R.drawable.ic_check_ok);
                    btnSair.setEnabled(false);
                    btnResult.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }

    }

}
