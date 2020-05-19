package com.example.covid19;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtQ, txtResult;
    private Button btnResult, btnReStart;
    private LinearLayout llDiagnostic, llResult;

    private int result = 0;
    private String q1, q2, q3;

    int countQ = 0;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQ = findViewById(R.id.txtQ);
        txtResult = findViewById(R.id.txtResult);
        btnResult = findViewById(R.id.btnResult);
        btnReStart = findViewById(R.id.btnReStart);
        llDiagnostic = findViewById(R.id.llDiagnostic);
        llResult = findViewById(R.id.llResult);
        progressBar = findViewById(R.id.progressBar);

        q1 = "1. Você está com FALTA DE AR?";
        q2 = "2. Você está com FEBRE?";
        q3 = "3. Você está com TOSSE?";
        txtQ.setText(q1);

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

                txtResult.setText(String.valueOf(result));
                llResult.setVisibility(View.VISIBLE);
            }
        });

        btnReStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llResult.setVisibility(View.GONE);
                btnResult.setVisibility(View.GONE);
                llDiagnostic.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                txtQ.setText(q1);
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
                    txtQ.setText(q2);
                    result = result + 4;
                    progressBar.setProgress(35);
                } else {
                    txtQ.setText(q2);
                    progressBar.setProgress(35);
                }
                break;
            case 2:

                if(rbQ.equals("SIM")){
                    txtQ.setText(q3);
                    result = result + 4;
                    progressBar.setProgress(70);
                } else {
                    txtQ.setText(q3);
                    progressBar.setProgress(70);
                }
                break;
            case 3:
                if(rbQ.equals("SIM")){
                    result = result + 1;
                    progressBar.setProgress(100);

                    llDiagnostic.setVisibility(View.GONE);
                    btnResult.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setProgress(100);

                    llDiagnostic.setVisibility(View.GONE);
                    btnResult.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }

    }

}
