package com.joanna.footmassage.modles.models;

import android.annotation.SuppressLint;

public class ResponseModel<T> {
    private int code;
    private String message;
    private T data;

    public ResponseModel(int code, String message, T data) {
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

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%d %s", getCode(), getMessage());
    }
}
