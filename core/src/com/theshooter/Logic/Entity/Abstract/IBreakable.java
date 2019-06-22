package com.theshooter.Logic.Entity.Abstract;

import com.theshooter.Logic.Damage;

public interface IBreakable extends IEntity{
    void breakDown(Damage damage);
    boolean isBroken();
    int getHP();
}
