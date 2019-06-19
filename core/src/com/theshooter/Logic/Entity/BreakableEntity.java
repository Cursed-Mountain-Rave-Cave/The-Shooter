package com.theshooter.Logic.Entity;

import com.theshooter.Screen.Depth;

public class BreakableEntity extends Entity implements IBreakableEntity{

    protected int hp;
    protected boolean broken;

    public BreakableEntity(int x, int y, int w, int h, int hp, Depth depth, boolean passable) {
        super(x, y, w, h, depth, passable);
        this.hp = hp;
        this.broken = false;
    }

    public BreakableEntity(int x, int y, int w, int h, Depth depth, boolean passable) {
        this(x, y, w, h, 1, depth, passable);
    }

    public BreakableEntity(int x, int y, int w, int h, Depth depth) {
        this(x, y, w, h, depth, true);
    }

    public boolean isBroken(){
        return broken;
    }

    @Override
    public int getHP() {
        return hp;
    }

    public void breakDown() {
        if (hp > 0)
            hp--;
        if(hp == 0)
            broken = true;
    }
}
