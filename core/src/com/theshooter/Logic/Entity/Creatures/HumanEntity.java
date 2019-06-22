package com.theshooter.Logic.Entity.Creatures;

import com.badlogic.gdx.math.Rectangle;
import com.theshooter.Logic.Entity.Abstract.ILookable;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Screen.Depth;

public class HumanEntity extends CreatureEntity implements ILookable{

    private float lookdx, lookdy;

    public HumanEntity(int x, int y, int w, int h, int hp, int velocity, int radius, Depth depth, boolean passable, Rectangle target){
        super(x, y, w, h, hp, velocity, radius, depth, passable, target);
    }

    public void update() {
        super.update();
        lookAt(getMovedx(), getMovedy());
    }

    public void look(){

    }

    @Override
    public void lookAt(float dx, float dy) {
        if(dx == 0 && dy == 0){
            lookdx = 0;
            lookdy = 0;
        }
        float norm = (float) Math.hypot(dx, dy);
        lookdx = dx / norm;
        lookdy = dy / norm;
    }

    public float getLookdx() {
        return lookdx;
    }

    public float getLookdy() {
        return lookdy;
    }
}
