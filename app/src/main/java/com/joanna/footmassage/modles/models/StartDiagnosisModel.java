package com.joanna.footmassage.modles.models;

public class StartDiagnosisModel {
    private int id;
    private String account;
    private String token;

    public StartDiagnosisModel(int id, String account, String token) {
        this.id = id;
        this.account = account;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
