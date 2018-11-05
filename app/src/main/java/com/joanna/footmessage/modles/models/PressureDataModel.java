package com.joanna.footmessage.modles.models;


import com.joanna.footmessage.modles.entities.PressureData;

import java.util.List;

public class PressureDataModel {
    private String account;
    private String token;
    private int rId;
    private List<PressureData> pressureDataList;
    private int painful;

    public PressureDataModel(String account, String token, int rId, List<PressureData> pressureDataList, int painful) {
        this.account = account;
        this.token = token;
        this.rId = rId;
        this.pressureDataList = pressureDataList;
        this.painful = painful;
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

    public List<PressureData> getPressureDataList() {
        return pressureDataList;
    }

    public void setPressureDataList(List<PressureData> pressureDataList) {
        this.pressureDataList = pressureDataList;
    }

    public int getPainful() {
        return painful;
    }

    public void setPainful(int painful) {
        this.painful = painful;
    }
}
