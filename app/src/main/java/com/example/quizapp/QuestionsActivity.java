package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    ArrayList<String> questions  = new ArrayList<>();
    ArrayList<String[]> answerOptions = new ArrayList<>();
    List<String> answers = new ArrayList<>();
    Button submit,quit;
    RadioButton o1, o2,o3,o4;
    private TextView displayName,scoreBoard,quesBox,remQues;
    int questionCount=0,score=0,wrongAnswer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        displayName=findViewById(R.id.nameText);
        quesBox =findViewById(R.id.questionBox);
        scoreBoard = findViewById(R.id.scoreBoard);
        o1 = findViewById(R.id.opt1);
        o2 = findViewById(R.id.opt2);
        o3 = findViewById(R.id.opt3);
        o4 = findViewById(R.id.opt4);
        submit = findViewById(R.id.submitButton);
        remQues = findViewById(R.id.quesCount);
        quit= findViewById(R.id.quitBtn);

        Intent i= getIntent();
        String name= i.getStringExtra("Player_Name");
        displayName.append("\n"+name);
        addQueAndAns();
        updateQues(questionCount);
        remQues.setText(Integer.toString(questionCount+1) + "/" + Integer.toString(questions.size()));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCount>questions.size())
                    Toast.makeText(QuestionsActivity.this, "Quiz Completed ", Toast.LENGTH_SHORT).show();
                else
                {
                    if(o1.isChecked()){
                        checkAnswer(o1.getText().toString());
                        o1.setChecked(false);
                    }
                    else if(o2.isChecked()){
                        checkAnswer(o2.getText().toString());
                        o2.setChecked(false);
                    }
                    else if(o3.isChecked()){
                        checkAnswer(o3.getText().toString());
                        o3.setChecked(false);
                    }
                    else if(o4.isChecked()){
                        checkAnswer(o4.getText().toString());
                        o4.setChecked(false);
                    }
                    else{
                        Toast.makeText(QuestionsActivity.this, "Please select any option to continue!!", Toast.LENGTH_SHORT).show();
                    }
                    if(questionCount<questions.size())
                        updateQues(questionCount);
                    else{
                        callResPage();
                    }
                }
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertUser();
            }
        });



    }
    private void alertUser(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(QuestionsActivity.this);
        builder1.setMessage("Unanswered questions will be marked as 0.\n" +
                "Are you sure? ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        callResPage();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    private void callResPage(){
        Intent resPage= new Intent(QuestionsActivity.this, Result_page.class);
        resPage.putExtra("answered", score);
        resPage.putExtra("total",questions.size());
        startActivity(resPage);
        finish();
    }
    private void addQueAndAns(){
        questions.add("1.Which method can be defined only once in a program?");
        questions.add("2.Which keyword is used by method to refer to the current object that invoked it?");
        questions.add("3.Which of these access specifiers can be used for an interface?");
        questions.add("4.Which of the following is correct way of importing an entire package‘pkg’?");
        questions.add("5.What is the return type of Constructors?");
        String[] ans1 ={"Finalize Method","Main Method","Static Method","Private Method"};
        answerOptions.add(ans1);
        String[] ans2 ={"import","this","catch","abstract"};
        answerOptions.add(ans2);
        String[] ans3 ={"public","protected","private","All of the mentioned"};
        answerOptions.add(ans3);
        String[] ans4 ={"Import pkg.","import pkg.*","Import pkg.*","import pkg."};
        answerOptions.add(ans4);
        String[] ans5 ={"int","float","void","None of the above"};
        answerOptions.add(ans5);

        answers.add("Main Method");
        answers.add("this");
        answers.add("public");
        answers.add("import pkg.*");
        answers.add("None of the above");
    }
    private void checkAnswer(String answer){
        if(questionCount<questions.size())
        {
            if(answer.equals(answers.get(questionCount)))
            {
                scoreBoard.setText(Integer.toString(++score));
                Toast.makeText(this, "Correct Answer!", Toast.LENGTH_SHORT).show();
            }
            else{
                wrongAnswer++;
                Toast.makeText(this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
            }
            questionCount++;
        }
        else{
            Toast.makeText(this, "Quiz Completed!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQues(int qno){
        if(qno<=questions.size()){
            remQues.setText(Integer.toString(qno+1) + "/" + Integer.toString(questions.size()));
            quesBox.setText(questions.get(qno));
            String[] answer = answerOptions.get(qno);
            o1.setText(answer[0]);
            o2.setText(answer[1]);
            o3.setText(answer[2]);
            o4.setText(answer[3]);
        }
        else
            Toast.makeText(this, "Question Limit Exceeded", Toast.LENGTH_SHORT).show();
    }

}