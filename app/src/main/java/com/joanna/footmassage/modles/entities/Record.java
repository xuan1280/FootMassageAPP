package com.joanna.footmassage.modles.entities;

import java.util.Date;
import java.util.List;

public class Record extends Entity {
    private List<PressureData> pressureDataList;
    private int painful;
    private Date startDate;
    private Date endDate;
    private String result;

    public Record(List<PressureData> pressureDataList, int painful) {
        this.pressureDataList = pressureDataList;
        this.painful = painful;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
