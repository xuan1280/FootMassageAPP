package com.joanna.footmassage.modles.entities;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PressureData {
    private Date date;
    private int[] data = new int[8];
    private int painful;

    public PressureData(Date date, int[] data) {
        this.date = date;
        this.data = data;
    }

    public PressureData(Date date, String data) {
        this.date = date;
        String[] tokens = data.split(",");
        for (int i = 0; i < 8; i++) {
            this.data[i] = Integer.valueOf(tokens[i]);
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public int[] getData() {
        return data;
    }

    public int getPainful() {
        return painful;
    }

    public void setPainful(int painful) {
        this.painful = painful;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str.append(dateFormat.format(date)).append(" ");
        for (int d : data) {
            str.append(String.valueOf(d)).append(", ");
        }
        return str.toString();
    }
}
