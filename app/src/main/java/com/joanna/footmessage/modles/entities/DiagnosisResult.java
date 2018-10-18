package com.joanna.footmessage.modles.entities;

public class DiagnosisResult {
    private int id;
    private String result;

    public DiagnosisResult(int id, String result) {
        this.id = id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
