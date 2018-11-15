package com.joanna.footmassage.modles.entities;

import java.util.Date;

public class Result extends Entity{
    private String result;
    private Date date;

    public Result(int id, String result, Date date) {
        this.id = id;
        this.result = result;
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", id, result, date);
    }
}
