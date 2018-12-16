package com.joanna.footmassage.modles.entities;

import android.annotation.SuppressLint;

import java.util.List;

public class User extends Entity {
    private String token;
    private String name;
    private String account;
    private String password;
    private int age;
    private int gender;
    private List<Integer> questionnaireAnswers;

    public User(String token, int id, String name, String account, String password, int age, int gender) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<Integer> getQuestionnaireAnswers() {
        return questionnaireAnswers;
    }

    public void setQuestionnaireAnswers(List<Integer> questionnaireAnswers) {
        this.questionnaireAnswers = questionnaireAnswers;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%s %s %s %d", name, account, password, age);
    }
}
