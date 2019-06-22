package com.theshooter.Logic.Entity.Abstract;

public interface ILookable {
    void lookAt(int dx, int dy);
    float getLookdx();
    float getLookdy();
}
