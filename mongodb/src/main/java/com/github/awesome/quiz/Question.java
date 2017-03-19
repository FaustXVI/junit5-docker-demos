package com.github.awesome.quiz;

public class Question {

    String value;

    private Question(String value) {
        this.value = value;
    }

    public static Question question(String question) {
        return new Question(question);
    }
}
