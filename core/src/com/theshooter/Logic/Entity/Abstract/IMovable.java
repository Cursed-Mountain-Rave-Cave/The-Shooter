package com.theshooter.Logic.Entity.Abstract;

public interface IMovable extends IEntity {
    void move();
    void moveAt(float dx, float dy);
    float getMovedx();
    float getMovedy();
    void setChangeRadius();
}
