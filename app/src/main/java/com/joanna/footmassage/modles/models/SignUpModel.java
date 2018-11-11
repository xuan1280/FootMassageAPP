package com.joanna.footmassage.modles.models;

public class SignUpModel {
    private String account;
    private String password;
    private String name;
    private int age;
    private int gender;

    public SignUpModel(String account, String password, String name, int age, int gender) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

