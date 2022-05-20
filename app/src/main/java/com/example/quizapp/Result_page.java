package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result_page extends AppCompatActivity {
    private TextView fs,correct,wrong;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        fs= findViewById(R.id.textView);
        correct= findViewById(R.id.textView3);
        wrong= findViewById(R.id.textView5);
        restart= findViewById(R.id.restartBtn);
        Intent i =getIntent();
        int score = i.getIntExtra("answered",0);
        int total= i.getIntExtra("total",0);
        setResults(score,total);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result_page.this,MainActivity.class));
                finish();
            }
        });
    }
    private void setResults(int score,int t){
        fs.append(" " + Integer.toString(score));
        correct.append(" " + Integer.toString(score));
        wrong.append(" " + Integer.toString(t-score));
    }


}