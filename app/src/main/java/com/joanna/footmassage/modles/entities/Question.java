package com.joanna.footmassage.modles.entities;

import java.util.List;

public class Question extends Entity {
    private String question;
    private List<String> items;
    private int answer;

    public Question(int id, String question, List<String> items) {
        this.id = id;
        this.question = question;
        this.items = items;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
