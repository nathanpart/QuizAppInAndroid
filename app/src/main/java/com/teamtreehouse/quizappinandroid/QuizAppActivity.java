package com.teamtreehouse.quizappinandroid;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class QuizAppActivity extends AppCompatActivity {
    private TextView leftInteger;
    private TextView rightInteger;
    private TextView operator;
    private RadioButton [] answers;
    private MathQuestion mathQuestion;
    private Button submit;
    private RadioGroup answerGroup;

    private void generateQuestion() {

        // Have model create a new question
        mathQuestion.generateQuestion();

        // Set the view fields to the generated question
        leftInteger.setText(String.format(Locale.getDefault(), "%d", mathQuestion.getLeftSide()));
        rightInteger.setText(String.format(Locale.getDefault(), "%d", mathQuestion.getRightSide()));
        operator.setText(String.format(" %s ", mathQuestion.getOperator()));

        // Set the radio button's fields to the questions' answers
        int [] questionAnswers = mathQuestion.getAnswers();
        for(int i = 0; i < 3; i++) {
            answers[i].setText(String.format(Locale.getDefault(), "%2d", questionAnswers[i]));
        }

        // Clear all selections and hide the Sumit button
        answerGroup.clearCheck();
        submit.setVisibility(View.INVISIBLE);
    }

    //Activate the Submit button when one of the radio buttons is selected
    private RadioGroup.OnCheckedChangeListener answerListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            QuizAppActivity.this.submit.setVisibility(View.VISIBLE);
        }
    };

    //Submit button listener
    private View.OnClickListener submitAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean selectionFound = false;
            for (int i = 0; i < 3; i++) {
                if (answers[i].isChecked()) {
                    selectionFound = true;
                    if (i == mathQuestion.getAnswerIndex()) {
                        Toast.makeText(QuizAppActivity.this, "Correct!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(QuizAppActivity.this, "Incorrect!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            if (selectionFound) {
                generateQuestion();
            } else {
                Toast.makeText(QuizAppActivity.this,
                        "Please select an answer.",
                        Toast.LENGTH_LONG).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);

        //Obtain access to view widgets
        leftInteger = (TextView) findViewById(R.id.leftInteger);
        rightInteger = (TextView) findViewById(R.id.rightInteger);
        operator = (TextView) findViewById(R.id.operator);

        // The readio buttons
        answers = new RadioButton[3];
        answers[0] = (RadioButton) findViewById(R.id.answer1);
        answers[1] = (RadioButton) findViewById(R.id.answer2);
        answers[2] = (RadioButton) findViewById(R.id.answer3);

        // Setup the listener and access to the radio button's group
        answerGroup = (RadioGroup) findViewById(R.id.radioGroup);
        answerGroup.setOnCheckedChangeListener(answerListener);

        // Setup the Submit button
        submit = (Button) findViewById(R.id.submitAnswer);
        submit.setOnClickListener(submitAnswer);

        //Create mathQuestion model and generate first question
        mathQuestion = MathQuizFactory.getMathQuiz(MathQuizFactory.ADDITION_QUIZ, 100);
        generateQuestion();
    }
}
