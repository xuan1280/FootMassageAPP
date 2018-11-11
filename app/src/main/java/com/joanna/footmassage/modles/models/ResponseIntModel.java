package com.joanna.footmassage.modles.models;

import android.annotation.SuppressLint;

public class ResponseIntModel {
    private int code;
    private String message;
    private int data;

    public ResponseIntModel(int code, String message, int data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getData() {
        return data;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%d %s", getCode(), getMessage());
    }
}
