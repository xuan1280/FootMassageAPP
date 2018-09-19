package com.joanna.footmessage.modles.entities;

import android.graphics.RadialGradient;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyCircle extends RadialGradient {

    private float centerX;
    private float centerY;
    private float radius;

    public MyCircle(float centerX, float centerY, float radius, @NonNull int[] colors, @Nullable float[] stops, @NonNull TileMode tileMode) {
        super(centerX, centerY, radius, colors, stops, tileMode);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public MyCircle(float centerX, float centerY, float radius, int centerColor, int edgeColor, TileMode tileMode) {
        super(centerX, centerY, radius, centerColor, edgeColor, tileMode);
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
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

}

