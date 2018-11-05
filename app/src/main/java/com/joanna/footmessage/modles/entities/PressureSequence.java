package com.joanna.footmessage.modles.entities;

import java.util.List;

public class PressureSequence {
    private List<PressureData> pressureDataList;
    private int painful;

    public PressureSequence(List<PressureData> pressureDataList, int painful) {
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
}
