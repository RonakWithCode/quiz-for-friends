package com.crazyostudio.quizforfriends.Model;

import java.util.ArrayList;

public class MCQModel {
    String question, correctAnswer,borderColor;
    ArrayList<String> options;

    public MCQModel(){}
    public MCQModel(String question, String correctAnswer, String borderColor, ArrayList<String> options) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.borderColor = borderColor;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
}
