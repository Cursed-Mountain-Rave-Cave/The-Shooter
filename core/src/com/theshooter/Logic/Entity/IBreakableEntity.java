package com.theshooter.Logic.Entity;

public interface IBreakableEntity extends IEntity{

    boolean isBroken();
    int getHP();

    void breakDown();
}
