package com.joanna.footmessage.modles.entities;

import java.util.List;

public class Question extends Entity {
    private String question;
    private List<String> items;

    public Question(String question, List<String> items) {
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
}
