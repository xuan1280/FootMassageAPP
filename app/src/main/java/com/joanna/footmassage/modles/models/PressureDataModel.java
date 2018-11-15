package com.joanna.footmassage.modles.models;


import com.joanna.footmassage.modles.entities.PressureData;

public class PressureDataModel {
    private String account;
    private String token;
    private int rId;
    private int[] pressureData;
    private int painful;
    private String time;

    public PressureDataModel(String account, String token, int rId, int[] pressureData, int painful, String time) {
        this.account = account;
        this.token = token;
        this.rId = rId;
        this.pressureData = pressureData;
        this.painful = painful;
        this.time = time;
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

    public int[] getPressureData() {
        return pressureData;
    }

    public void setPressureData(int[] pressureData) {
        this.pressureData = pressureData;
    }

    public int getPainful() {
        return painful;
    }

    public void setPainful(int painful) {
        this.painful = painful;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
