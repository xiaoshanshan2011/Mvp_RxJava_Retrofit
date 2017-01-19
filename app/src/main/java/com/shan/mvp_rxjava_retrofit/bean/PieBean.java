package com.shan.mvp_rxjava_retrofit.bean;

/**
 * Created by 陈俊山 on 17-1-9.
 */

public class PieBean {
    //用户关心的数据
    private String name;//名字
    private float value;//数值
    //非用户关心的数据
    private int color = 0;//颜色
    private float angle = 0;//角度

    public PieBean(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
