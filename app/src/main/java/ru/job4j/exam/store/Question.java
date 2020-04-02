package ru.job4j.exam.store;

import java.util.List;

// сам вопрос с вариантами ответов и правильным ответом
public class Question {
    private int id;
    private String text;
    private List<Option> options;
    private int rightAnswer;
    private int userAnswer;

    public Question(int id, String text, List<Option> options, int rightAnswer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.rightAnswer = rightAnswer;
        this.userAnswer = -1;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
}
