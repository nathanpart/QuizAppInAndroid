package com.teamtreehouse.quizappinandroid;

// This classes purpose is to instantiate the proper MathQuestion object.
public class MathQuizFactory {
    public final static int ADDITION_QUIZ = 1;
    // Other future quiz type options here

    public static MathQuestion getMathQuiz(int quizType, int range) {
        switch (quizType) {
            case ADDITION_QUIZ:
                return new AddtionQuiz(range);
            // Other future quiz types will go here
            default:
                return new AddtionQuiz(range);
        }
    }
}
