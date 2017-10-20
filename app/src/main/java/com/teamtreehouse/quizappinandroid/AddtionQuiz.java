package com.teamtreehouse.quizappinandroid;


import android.content.res.Resources;

import java.util.Random;

class AddtionQuiz implements MathQuestion {
    // Random number generator
    private Random numGenerator = new Random();

    // holds current math problem
    private int leftSide;
    private int rightSide;
    private int [] answers;
    private int numberRange;
    private int answerIndex;
    private Resources resources;   // Access to string resources


    public AddtionQuiz(int range, Resources resources) {
        answers = new int[3];
        numberRange = range;
        this.resources = resources;
        generateQuestion();
    }

    @Override
    public int getLeftSide() {
        return leftSide;
    }

    @Override
    public int getRightSide() {
        return rightSide;
    }

    @Override
    public String getOperator() {
        return resources.getString(R.string.addition_quiz_operator);
    }

    @Override
    public int[] getAnswers() {
        return answers;
    }

    @Override
    public void generateQuestion() {

        // Get the two numbers to be added
        leftSide = numGenerator.nextInt(numberRange);
        rightSide = numGenerator.nextInt(numberRange);

        // Which slot in  answer array has the correct answer
        answerIndex = numGenerator.nextInt(3);

        // Compute correct answer
        answers[answerIndex] = rightSide + leftSide;

        // Obtain the two decoy answers
        int decoyIndex = answerIndex;
        for (int i = 0; i < 2; i++) {
            // Andvance to next decoy slot in answer array
            decoyIndex++;
            if (decoyIndex > 2) {
                decoyIndex = 0;
            }
            answers[decoyIndex] = getDecoyAnswer(answers[answerIndex]);
        }
    }

    @Override
    public int getAnswerIndex() {
        return answerIndex;
    }

    private int getDecoyAnswer(int correctAnswer) {
        // Return a random number with in 50% deviation of correct answer
        // Algorithm is fairly simple.  Get a number from 0 to correct answer
        // and scale it up by 1/2 of the correct answer.

        int decoyRangeBottom = correctAnswer >>> 1;  // 1/2 of correct answer
        int decoy;
        do {
            decoy = decoyRangeBottom + numGenerator.nextInt(correctAnswer);
        } while (decoy == correctAnswer);
        return decoy;
    }
}
