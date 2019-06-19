package com.theshooter.Logic.Entity;

public interface ILookable {

    void lookAt(int dx, int dy);
    float getLookdx();
    float getLookdy();
}
