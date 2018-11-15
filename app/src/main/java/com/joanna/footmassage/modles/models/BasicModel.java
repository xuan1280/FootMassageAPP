package com.joanna.footmassage.modles.models;

public class BasicModel {
    private String account;
    private String token;

    public BasicModel(String account, String token) {
        this.account = account;
        this.token = token;
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
}
