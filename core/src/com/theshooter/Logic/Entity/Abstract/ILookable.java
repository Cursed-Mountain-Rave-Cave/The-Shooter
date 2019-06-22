package com.theshooter.Logic.Entity.Abstract;

public interface ILookable {
    void look();
    void lookAt(float dx, float dy);
    float getLookdx();
    float getLookdy();
}
