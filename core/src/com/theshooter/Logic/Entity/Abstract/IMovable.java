package com.theshooter.Logic.Entity.Abstract;

public interface IMovable {
    void moveAt(int dx, int dy);
    float getMovedx();
    float getMovedy();
}
