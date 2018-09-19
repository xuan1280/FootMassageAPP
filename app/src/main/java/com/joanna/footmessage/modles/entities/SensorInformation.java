package com.joanna.footmessage.modles.entities;

import java.util.Date;

public class SensorInformation {
    private Date date;
    private String data;

    public SensorInformation(Date date, String data) {
        this.date = date;
        this.data = data;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public String getData() {
        return data;
    }
}
