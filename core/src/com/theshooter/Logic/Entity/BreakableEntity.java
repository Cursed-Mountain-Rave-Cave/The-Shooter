package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class BreakableEntity extends Entity implements IBreakableEntity{

    protected boolean broken;

    public BreakableEntity(int x, int y, int w, int h, Depth depth, boolean passable) {
        super(x, y, w, h, depth, passable);
        this.broken = false;
    }

    public BreakableEntity(int x, int y, int w, int h, Depth depth) {
        this(x, y, w, h, depth, true);
    }

    public boolean isBroken(){
        return broken;
    }

    public void breakDown() {
        broken = true;
      //  this.setPassable(true);
    }
}
