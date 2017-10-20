package com.teamtreehouse.quizappinandroid;

// Model methods called by the quiz activity.
public interface MathQuestion {
    public int getLeftSide();
    public int getRightSide();
    public String getOperator();
    public int[] getAnswers();
    public void generateQuestion();
    public int getAnswerIndex();
}
