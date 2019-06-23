package com.theshooter.Logic.Entity.LiftableEntities;

import com.theshooter.Logic.Entity.Entity;
import com.theshooter.Screen.Depth;

abstract public class LiftableEntity extends Entity {
    public LiftableEntity(int x, int y) {
        super(x, y, 30 ,30,  Depth.THINGS, true);
    }

    abstract public void use();
}
