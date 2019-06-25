package com.theshooter.Logic.Entity;
import com.theshooter.Logic.Entity.Abstract.IBreakable;
import com.theshooter.Screen.Depth;


public class BreakableEntity extends Entity implements IBreakable {
    protected int maxHp;
    protected int hp;
    protected boolean broken;

    public BreakableEntity(int x, int y, int w, int h, int hp, Depth depth, boolean passable) {
        super(x, y, w, h, depth, passable);
        this.hp = hp;
        maxHp = hp;
        this.broken = false;
    }

    public BreakableEntity(int x, int y, int w, int h, Depth depth, boolean passable) {
        this(x, y, w, h, 1, depth, passable);
    }


    public BreakableEntity(int x, int y, int w, int h, Depth depth) {
        this(x, y, w, h, depth, true);
    }

    public boolean isBroken() {
        return broken;
    }

    @Override
    public int getHP() {
        return hp;
    }

    public void breakDown(Damage damage) {
        if (hp > 0)
            hp -= damage.getValue();
        if (hp <= 0)
            broken = true;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(maxHp, hp);
        if(hp == 0)
            broken = true;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
