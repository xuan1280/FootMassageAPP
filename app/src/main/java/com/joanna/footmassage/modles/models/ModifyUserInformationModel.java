package com.joanna.footmassage.modles.models;

public class ModifyUserInformationModel {
    private String account;
    private String token;
    private String name;
    private String password;
    private int age;
    private int gender;

    public ModifyUserInformationModel(String account, String token, String name, String password, int age, int gender) {
        this.account = account;
        this.token = token;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
