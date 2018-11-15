package com.joanna.footmassage.modles.models;

import com.joanna.footmassage.modles.entities.Question;

import java.util.List;

public class QuestionnaireAnswerModel {
    private String account;
    private String token;
    private Question[] questions;

    public QuestionnaireAnswerModel(String account, String token, Question[] questions) {
        this.account = account;
        this.token = token;
        this.questions = questions;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
