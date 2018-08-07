package com.joanna.footmessage.modles.entities;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String account;
    private String password;
    private int age;
    private int gender;
    private int exerciseCycle;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getExerciseCycle() {
        return exerciseCycle;
    }

    public void setExerciseCycle(int exerciseCycle) {
        this.exerciseCycle = exerciseCycle;
    }
}