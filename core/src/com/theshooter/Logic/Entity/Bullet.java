package com.theshooter.Logic.Entity;

import com.badlogic.gdx.Gdx;
import com.theshooter.Screen.Depth;

public class Bullet extends Entity{

    float dx;
    float dy;

    static final int VELOCITY = 2000;

    public Bullet(int x, int y, float dx, float dy){
        super(x, y, 10, 10, Depth.EFFECTS);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void update() {
        super.update();
        setX((int) (getX() + dx * Gdx.graphics.getDeltaTime() * VELOCITY));
        setY((int) (getY() + dy * Gdx.graphics.getDeltaTime() * VELOCITY));
    }

    public float getDx() { return dx; }
    public float getDy() { return dy; }
}
