package com.theshooter.Logic.Entity;

public interface IMovable {

    void moveAt(int dx, int dy);
    float getMovedx();
    float getMovedy();
}
