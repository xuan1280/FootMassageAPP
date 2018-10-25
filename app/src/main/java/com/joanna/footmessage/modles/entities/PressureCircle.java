package com.joanna.footmessage.modles.entities;

import android.graphics.RadialGradient;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class PressureCircle extends RadialGradient {
    private int id;
    private float centerX;
    private float centerY;
    private float radius;
    private int value;

    public PressureCircle(int id, float centerX, float centerY, int centerColor, int edgeColor, TileMode tileMode) {
        super(centerX, centerY, 100, centerColor, edgeColor, tileMode);
        this.id = id;
        this.centerX = centerX;
        this.centerY = centerY;
        radius = 100;
    }

    public void setValue(int value) {
        this.value = value;
        radius = 100 + value * 2;
    }

    public int getId() {
        return id;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public int getValue() {
        return value;
    }
}

