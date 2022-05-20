package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button about,start;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.getNameText);
        start=findViewById(R.id.button);
        about=findViewById(R.id.button2);

        about.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                v.getContext().startActivity(new Intent(MainActivity.this,About.class));
                finish();
           }
        });
    }

    public void startQuiz(View v)
    {
        String playerName= name.getText().toString();
        if(!playerName.isEmpty()){
            Intent i = new Intent(getApplicationContext(),QuestionsActivity.class);
            i.putExtra("Player_Name",playerName);
            startActivity(i);
        }else {
            Toast.makeText(this, "Enter your name to start the game!!", Toast.LENGTH_SHORT).show();
        }
    }
}