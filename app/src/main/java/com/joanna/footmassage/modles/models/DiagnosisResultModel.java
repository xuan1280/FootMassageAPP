package com.joanna.footmassage.modles.models;

public class DiagnosisResultModel {
    private String account;
    private String token;
    private int rId;

    public DiagnosisResultModel(String account, String token, int rId) {
        this.account = account;
        this.token = token;
        this.rId = rId;
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

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }
}
