package com.example.hong.waveview;

import android.graphics.Paint;

/**
 * Created by Hong on 2015/12/17.
 */
public class Rectangle {
    private float leftX;
    private float leftY;
    private float rightX;
    private float righty;
    private Paint paint;

    public Rectangle(float leftX, float leftY, float rightX, float righty, Paint paint) {

        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.righty = righty;
        this.paint = paint;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "leftX=" + leftX +
                ", leftY=" + leftY +
                ", rightX=" + rightX +
                ", righty=" + righty +
                ", paint=" + paint +
                '}';
    }

    public float getLeftX() {
        return leftX;
    }

    public void setLeftX(float leftX) {
        this.leftX = leftX;
    }

    public float getLeftY() {
        return leftY;
    }

    public void setLeftY(float leftY) {
        this.leftY = leftY;
    }

    public float getRightX() {
        return rightX;
    }

    public void setRightX(float rightX) {
        this.rightX = rightX;
    }

    public float getRighty() {
        return righty;
    }

    public void setRighty(float righty) {
        this.righty = righty;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
