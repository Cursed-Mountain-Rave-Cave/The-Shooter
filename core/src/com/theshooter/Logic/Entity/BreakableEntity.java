package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class BreakableEntity extends Entity {

    boolean broken;

    public BreakableEntity(int x, int y, int w, int h, Depth depth) {
        super(x, y, w, h, depth);
        this.broken = false;
    }

    public boolean isBroken(){
        return broken;
    }

    public void breakDown() {
        broken = true;
    }
}
