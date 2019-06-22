package com.theshooter.Logic.Entity;

import com.theshooter.Logic.Damage;

public interface IBreakableEntity extends IEntity{

    boolean isBroken();
    int getHP();

    void breakDown(Damage damage);
}
