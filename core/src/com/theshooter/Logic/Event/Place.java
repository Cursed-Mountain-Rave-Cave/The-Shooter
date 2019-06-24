package com.theshooter.Logic.Event;

public class Place {

    private int x;
    private int y;
    private int r;
    private String flag;
    private boolean value;

    public Place(int x, int y, int r, String flag, boolean value) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.flag = flag;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public String getFlag() {
        return flag;
    }

    public boolean getValue() {
        return value;
    }
}
