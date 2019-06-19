package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.theshooter.Logic.Map;
import com.theshooter.Screen.Depth;

public class HumanEntity extends Entity implements ILookable, IMovable, IBreakableEntity {

    private Map map;
    private int velocity;
    private float lookdx, lookdy;
    private float movedx, movedy;


    public HumanEntity(int x, int y, Depth depth, Map map){
        super(x, y, 50, 50, depth, false);
        this.velocity = 1000;
        this.map = map;
    }

    public void update(){
        int changeX = (int) (movedx * Gdx.graphics.getDeltaTime() * velocity);
        int changeY = (int) (movedy * Gdx.graphics.getDeltaTime() * velocity);
        setX(getX() + changeX);
        setY(getY() + changeY);
        if (!map.isAllowed(super.getRectangle())) {
            setX(getX() - changeX);
            setY(getY() - changeY);
        }
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public void breakDown() {

    }

    @Override
    public void lookAt(int dx, int dy) {
        float norm = (float) Math.hypot(dx, dy);
        lookdx = dx / norm;
        lookdy = dy / norm;
    }

    @Override
    public void moveAt(int dx, int dy) {
        if(dx == 0 && dy == 0){
            movedx = 0;
            movedy = 0;
        }else{
            float norm = (float) Math.hypot(dx, dy);
            movedx = dx / norm;
            movedy = dy / norm;
        }
    }

    public float getLookdx() {
        return lookdx;
    }

    public float getLookdy() {
        return lookdy;
    }

    public float getMovedx() {
        return movedx;
    }

    public float getMovedy() {
        return movedy;
    }
}
